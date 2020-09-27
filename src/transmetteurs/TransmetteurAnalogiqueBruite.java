package transmetteurs;

import destinations.DestinationInterface;

import java.lang.annotation.IncompleteAnnotationException;
import java.util.Random;
import information.Information;
import information.InformationNonConforme;

/**
 * Classe TransmetteurAnalogiqueParfait herite de la classe Transmetteur
 *
 * @author c.legruiec
 * @author e.leduc
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 * 
 * @version R1.0 - Sept 2020
 */
public class TransmetteurAnalogiqueBruite extends Transmetteur<Float, Float>{
	
	/**
	* Attribut d'instance : 'snr' le rapport signal/bruit
	*/
	float snr;
	/**
	* Attribut d'instance : 'seed' la graine de génération aléatoire. Valeur par default NULL.
	*/
	Integer seed=null;
	/**
	* Attribut d'instance : 'informationConverti' information recue avec ajout de bruit. 
	*/
	private Information<Float> informationConverti=new Information<>();
	/**
	* Attribut d'instance : 'nbEchantillon'  nombre d'echantillon par bit. Valeur par default 30.
	*/
	int nbEchantillon=30;
	
	
	/**
	* Constructeur à 3 paramètres de la classe.
	* @param seed : la graine de génération aléatoire
	* @param snr : le rapport signal/bruit
	* @param nbEchantillon : nombre d'echantillon par bit
	*/
	public TransmetteurAnalogiqueBruite(int seed, float snr, int nbEchantillon) {
		super();
		this.seed=seed;
		this.snr=snr;
		this.nbEchantillon=nbEchantillon;
	}
	
	/**
	* Constructeur à 2 paramètres de la classe.
	* @param snr : le rapport signal/bruit
	* @param nbEchantillon : nombre d'echantillon par bit
	*/
	public TransmetteurAnalogiqueBruite(float snr, int nbEchantillon) {
		super();
		this.snr=snr;
		this.nbEchantillon=nbEchantillon;

	}
	
	/**
	* Constructeur à 2 paramètres de la classe.
	* @param snr : le rapport signal/bruit
	*/
	public TransmetteurAnalogiqueBruite(float snr ) {
		super();
		this.snr=snr;
	}
	
	/**
     * canal Rx Information (abstract dans la classe mere)
     * 
     */
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
        ajoutBruit();
        emettre();//envoie l'information

    }

    /**
     * canal Tx Information (abstract dans la classe mere)
     * 
     */
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;//transmetteur parfait src=dest

    }
    
    /**
     * Permet d'ajouter le bruit sur le signal recue. Le signal bruite est stocke dans informationConverti.
     * 
     */
    private void ajoutBruit() {
    	float sigma=calculSigma();
    	Random rand1;
    	Random rand2;
    	if (seed!=null) {
			rand1=new Random(seed);
			rand2=new Random(seed);
		} else {
			rand1=new Random();
			rand2=new Random();
		}
    	float bruit=0f;
    	for (float info : informationRecue) {
    		//sigma=calculSigma(i);
        	bruit=(float) ((float) sigma*(Math.sqrt(-2*Math.log(1-rand1.nextFloat())))*(Math.cos(2*Math.PI*rand2.nextFloat())));
        	informationConverti.add(info+bruit);
    	}
    	
    }
    
    
    /**
     * Permet de calculer la racine carré de la puissance du bruit (sigma)
     * 
     */
    private float calculSigma() {
    	float Ps=0f;
    	float Sigma=0f;
  
        for (float info : informationRecue) {
        	Ps+=Math.pow(info, 2);
		}
        //on a enlevé le nombre d'echantillon par bit dans les calculs suite à une simplication.
        //il ne reste que le calcul de l'esperance des Ak².
        Ps=Ps/informationRecue.nbElements(); 
        //calcul de sigmaCarre
        Sigma= (float) ((float) (Ps)/(2*Math.pow(10,snr/10)));
        Sigma=(float) Math.sqrt(Sigma);
    	
    	//System.out.println("sigma:"+Sigma); //debug
    	return Sigma;
    }
    
    

}
