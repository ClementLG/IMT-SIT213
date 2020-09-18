package transmetteurs;

import destinations.DestinationInterface;

import java.lang.annotation.IncompleteAnnotationException;
import java.util.Random;
import information.Information;
import information.InformationNonConforme;

/**
 * Classe TransmetteurAnalogiqueParfait hérité de la classe Transmetteur
 *
 * @author c.legruiec
 * @author e.leduc
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 */
public class TransmetteurAnalogiqueBruitee extends Transmetteur<Float, Float>{
	float snr=10;
	Integer seed=null;
	private Information<Float> informationConverti;
	
	
	public TransmetteurAnalogiqueBruitee(int seed, float snr) {
		this.seed=seed;
		this.snr=snr;
		informationConverti =new Information<>();

	}
	
	public TransmetteurAnalogiqueBruitee() {
		super();
		informationConverti =new Information<>();

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
    
    private void ajoutBruit() {
    	float sigma=calculSigma();
    	Random rand1=new Random();
    	Random rand2=new Random();
    	float bruit=0f;
    	for (Float infoR : informationRecue) {
    		bruit=(float) ((float) sigma*(Math.sqrt(-2*Math.log(1-rand1.nextFloat())))*(Math.cos(2*Math.PI*rand2.nextFloat())));
    		informationConverti.add(infoR+bruit);
		}
    }
    
    private float calculSigma() {
    	float Ps=0f;
    	float Sigma=0f;
  
        for (Float infoR : informationRecue) {
    		Ps+=Math.pow(infoR, 2);
    	}
        	
        //calcul de sigmaCarre
        Sigma= (float) (Ps/(2*Math.pow(10,snr/10)));
        Sigma=(float) Math.sqrt(Sigma);
    	
    	//calcul de la puissance moyenne
    	
    	return Sigma;
    }

}
