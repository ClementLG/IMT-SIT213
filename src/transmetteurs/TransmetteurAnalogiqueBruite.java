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
	* Attribut dinstance : snr le rapport signal/bruit
	*/
	float snrpb;
	/**
	* Attribut dinstance : seed la graine de génération aléatoire. Valeur par default NULL.
	*/
	Integer seed=null;
	/**
	* Attribut dinstance : informationConverti information recue avec ajout de bruit. 
	*/
	private Information<Float> informationConverti=new Information<>();
	/**
	* Attribut dinstance : nbEchantillon  nombre dechantillon par bit. Valeur par default 30.
	*/
	int nbEchantillon=30;
	
	
	/**
	* Constructeur à 3 paramètres de la classe.
	* @param seed : la graine de génération aléatoire
	* @param snr : le rapport signal/bruit
	* @param nbEchantillon : nombre dechantillon par bit
	*/
	public TransmetteurAnalogiqueBruite(int seed, float snr, int nbEchantillon) {
		super();
		this.seed=seed;
		this.snrpb=snr;
		this.nbEchantillon=nbEchantillon;
	}
	
	/**
	* Constructeur à 2 paramètres de la classe.
	* @param snr : le rapport signal/bruit
	* @param nbEchantillon : nombre dechantillon par bit
	*/
	public TransmetteurAnalogiqueBruite(float snr, int nbEchantillon) {
		super();
		this.snrpb=snr;
		this.nbEchantillon=nbEchantillon;

	}
	
	/**
	* Constructeur à 2 paramètres de la classe.
	* @param snr : le rapport signal/bruit
	*/
	public TransmetteurAnalogiqueBruite(float snr ) {
		super();
		this.snrpb=snr;
	}
	
	/**
     * canal Rx Information (abstract dans la classe mere)
     * 
     */
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
        ajoutBruit();
        emettre();//envoie linformation

    }

    /**
     * canal Tx Information (abstract dans la classe mere)
     * 
     */
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;

    }
    
    /**
     * Permet dajouter le bruit sur le signal recue. Le signal bruite est stocke dans informationConverti.
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
    	float Ps = 0f;
        float sigma;
        float snr;
  
        //Calcul de la puissance totale du signal
        for (float info : informationRecue) {
            Ps += Math.pow(info, 2);
        }
        //Calcul de la puissance par echantillon
        Ps = Ps / (float) informationRecue.nbElements();

        //Calcul du snr en fonction du nombre dechantillon et du snr par bit en dB
        snr = snrpb - 10 * (float) Math.log10(nbEchantillon / 2);
        
        sigma =  Ps  / (float) Math.pow(10, (snr / 10));
        //Calcul de sigma
        sigma = (float) Math.sqrt(sigma);      
        //cf: https://en.wikipedia.org/wiki/Signal-to-noise_ratio#:~:text=SNR%20is%20defined%20as%20the,by%20the%20Shannon%E2%80%93Hartley%20theorem.
        //System.out.println("Ps: "+Ps);
    	//System.out.println("sigma:"+sigma);
    	//System.out.println("Eb/n0:"+snrpb);
    	//System.out.println("SNR:"+snr);
    	return sigma;
    }
    
    

}
