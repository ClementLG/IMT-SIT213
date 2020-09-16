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
	int var=1;
	Integer seed=null;
	private Information<Float> informationConverti;
	
	
	public TransmetteurAnalogiqueBruitee(int seed, int var) {
		this.seed=seed;
		this.var=var;
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
    	Random rand1=new Random();
    	Random rand2=new Random();
    	float bruit=0f;
    	for (int i = 0; i < informationRecue.nbElements(); i++) {
    		bruit=(float) ((float) var*(Math.sqrt(-2*Math.log(1-rand1.nextFloat())))*(Math.cos(2*Math.PI*rand2.nextFloat())));
    		informationConverti.add(informationRecue.iemeElement(i)+bruit);
		}
    }

}
