package transmetteurs;

import destinations.DestinationInterface;
<<<<<<< HEAD
import information.Information;
import information.InformationNonConforme;
import java.util.Random;
import java.lang.Math;
=======

import java.lang.annotation.IncompleteAnnotationException;
import java.util.Random;
import information.Information;
import information.InformationNonConforme;

>>>>>>> clg
/**
 * Classe TransmetteurAnalogiqueParfait hérité de la classe Transmetteur
 *
 * @author c.legruiec
 * @author e.leduc
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 */
public class TransmetteurAnalogiqueBruite extends Transmetteur<Float, Float>{
<<<<<<< HEAD
=======
	float snr=0;
	Integer seed=null;
	private Information<Float> informationConverti;
	int nbEchantillon=30;
	
	public TransmetteurAnalogiqueBruite(int seed, float snr, int nbEchantillon) {
		this.seed=seed;
		this.snr=snr;
		this.nbEchantillon=nbEchantillon;
		informationConverti =new Information<>();

	}
	
	public TransmetteurAnalogiqueBruite(float snr, int nbEchantillon) {
		super();
		this.snr=snr;
		this.nbEchantillon=nbEchantillon;
		informationConverti =new Information<>();

	}
	
	public TransmetteurAnalogiqueBruite(float snr ) {
		super();
		this.snr=snr;
		this.nbEchantillon=nbEchantillon;
		informationConverti =new Information<>();
		nbEchantillon=30;

	}
>>>>>>> clg
	
	/**
     * canal Rx Information (abstract dans la classe mere)
     * 
     */
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
<<<<<<< HEAD
        Random myRandom = new Random();
        for (int i=0; i<informationRecue.nbElements(); i+=1) {
            float teta=1;
            float a1 = myRandom.nextFloat();
            float a2 = myRandom.nextFloat();
            float bn = teta*(float)Math.sqrt(-2*Math.log(1-a1))*(float)Math.cos(2*Math.PI*a2)/2;
            informationRecue.setIemeElement(i, informationRecue.iemeElement(i)+bn);
        }
=======
        ajoutBruit();
>>>>>>> clg
        emettre();//envoie l'information

    }

    /**
     * canal Tx Information (abstract dans la classe mere)
     * 
     */
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
<<<<<<< HEAD
        	destinationConnectee.recevoir(informationRecue);
        }
        informationEmise = informationRecue;//transmetteur parfait src=dest

    }
=======
        	destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;//transmetteur parfait src=dest

    }
    
    private void ajoutBruit() {
    	float sigma=calculSigma();
    	int k=1;
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
    	for (int i = 0; i < informationRecue.nbElements(); i+=nbEchantillon) {
    		//sigma=calculSigma(i);
    		for (int j = ((k-1)*nbEchantillon); j < k*nbEchantillon; j++) {
        		bruit=(float) ((float) sigma*(Math.sqrt(-2*Math.log(1-rand1.nextFloat())))*(Math.cos(2*Math.PI*rand2.nextFloat())));
        		informationConverti.add(informationRecue.iemeElement(j)+bruit);
    		}
    		k++;
		}
    	
    }
    
    private float calculSigma() {
    	float Ps=0f;
    	float Sigma=0f;
  
        for (float info : informationRecue) {
        	Ps+=Math.pow(info, 2);
		}
        Ps=Ps/informationRecue.nbElements();
        //calcul de sigmaCarre
        Sigma= (float) ((float) (Ps*nbEchantillon)/(2*Math.pow(10,snr/10)));
        Sigma=(float) Math.sqrt(Sigma);
    	
    	//calcul de la puissance moyenne
    	
    	return Sigma;
    }
    
    
>>>>>>> clg

}
