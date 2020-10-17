package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

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
public class TransmetteurAnalogiqueBruitReel extends Transmetteur<Float, Float>{
	
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
	* Attribut dinstance : informationDecale Somme de signaux decale et modifie en amplitude selon un coeff. 
	*/
	private Information<Float> informationDecale=new Information<>();
	/**
	* Attribut dinstance : parametres Decalage(s) et coefficient(s) des multiTrajets. 
	*/
	private ArrayList<Float> parametres=new ArrayList<Float>();
	/**
	* Attribut dinstance : nbEchantillon nombre dechantillon par bit. 
	*/
	int nbEchantillon=30;

	/**
     * Constructeur de TransmetteurAnalogiqueBruitReel avec 4 parametres
     * @param seed : graine de generation aleatoire
     * @param snr : rapport signal sur bruit
     * @param nbEchantillon : Nombre dechantillon par symbole
     * @param parametres : paramettre du/des multi-trajet(s)
     */
	public TransmetteurAnalogiqueBruitReel(int seed, float snr, int nbEchantillon, ArrayList<Float> parametres) {
		super();
		this.seed=seed;
		this.snrpb=snr;
		this.nbEchantillon=nbEchantillon;
		this.parametres=parametres;
	}

	/**
     * Constructeur de TransmetteurAnalogiqueBruitReel avec 3 parametres
     * @param snr : rapport signal sur bruit
     * @param nbEchantillon : Nombre dechantillon par symbole
     * @param parametres : paramettre du/des multi-trajet(s)
     */
	public TransmetteurAnalogiqueBruitReel(float snr, int nbEchantillon, ArrayList<Float> parametres) {
		super();
		this.snrpb=snr;
		this.nbEchantillon=nbEchantillon;
		this.parametres=parametres;

	}
	
	/**
     * Constructeur de TransmetteurAnalogiqueBruitReel avec 2 parametres
     * @param snr : rapport signal sur bruit
     * @param parametres : paramettre du/des multi-trajet(s)
     */
	public TransmetteurAnalogiqueBruitReel(float snr, ArrayList<Float> parametres ) {
		super();
		this.snrpb=snr;
		this.parametres=parametres;
	}

	/**
     * canal Rx Information (abstract dans la classe mere)
     *
     */
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
        ajoutDecalage();
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
     * Calcul les multitrajets et les ajoute dans informationDecale sous la forme dune somme des signaux decales.
     *
     */
	private void ajoutDecalage() {
		ArrayList<Information<Float>> listeInformationsDecalees=new ArrayList<Information<Float>>();
		
		//System.out.println(parametres[0].length);
		
		
		for (int i = 0; i < parametres.size(); i+=2) {
			//vidange de la linfo de base
			informationDecale=new Information<Float>();
			//copie du signal normal
			for (float info : informationRecue) {
				//System.out.println(i+1);
				informationDecale.add(parametres.get(i+1)*info);
			}
			
			if (parametres.get(i)>0f) {
				informationDecale.addBefore(new ArrayList<Float>(Collections.nCopies(Math.abs(parametres.get(i).intValue()), 0f)));
				informationDecale.cut(0, informationRecue.nbElements());
			} else {
				informationDecale.cut(Math.abs(parametres.get(i).intValue()), informationDecale.nbElements());
				informationDecale.add(new ArrayList<Float>(Collections.nCopies(Math.abs(parametres.get(i).intValue()), 0f)));
			}
			
			
			listeInformationsDecalees.add(informationDecale);
			
		}
		sommerArray(informationDecale, listeInformationsDecalees);
		
		
	}
	
	/**
     * Somme des arrayList de float
     * @param destResultat : destination de la somme
     * @param arraysofArrays : Liste des ArraysList à sommer
     *
     */
	private void sommerArray(Information<Float> destResultat, ArrayList<Information<Float>> arraysofArrays){
		if(!arraysofArrays.isEmpty()) {
			destResultat=arraysofArrays.get(0);
			for (int i = 1; i < arraysofArrays.size(); i++) {
				for (int j = 0; j < arraysofArrays.get(i).nbElements(); j++) {
					destResultat.setIemeElement(j, destResultat.iemeElement(j)+arraysofArrays.get(i).iemeElement(j));
				}
			}
			informationDecale=destResultat;
		}

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
    	for (int i = 0; i < informationRecue.nbElements(); i++) {
    		bruit=(float) ((float) sigma*(Math.sqrt(-2*Math.log(1-rand1.nextFloat())))*(Math.cos(2*Math.PI*rand2.nextFloat())));
    		if(informationDecale.nbElements()==0) informationConverti.add(informationRecue.iemeElement(i)+bruit);
    		else informationConverti.add(informationRecue.iemeElement(i)+informationDecale.iemeElement(i)+bruit);
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
        snr = snrpb - 10 * (float) (Math.log10(nbEchantillon) - Math.log10(2));
        sigma =  Ps  / (float) Math.pow(10, (snr / 10));
        //Calcul de sigma
        sigma = (float) Math.sqrt(sigma);      
        //cf: https://en.wikipedia.org/wiki/Signal-to-noise_ratio#:~:text=SNR%20is%20defined%20as%20the,by%20the%20Shannon%E2%80%93Hartley%20theorem.
        //System.out.println("Ps: "+Ps);
    	//System.out.println("sigma:"+sigma);
    	//System.out.println("Eb/n0:"+snrpb);
    	//System.out.println("SNR:"+snr);
    	//System.out.println("ne : "+nbEchantillon);
    	return sigma;
    }

}