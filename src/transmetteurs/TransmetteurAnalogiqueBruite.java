package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;
import java.util.Random;
import java.lang.Math;
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
	
	/**
     * canal Rx Information (abstract dans la classe mere)
     * 
     */
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
        Random myRandom = new Random();
        for (int i=0; i<informationRecue.nbElements(); i+=1) {
            float teta=1;
            float a1 = myRandom.nextFloat();
            float a2 = myRandom.nextFloat();
            float bn = teta*(float)Math.sqrt(-2*Math.log(1-a1))*(float)Math.cos(2*Math.PI*a2)/2;
            informationRecue.setIemeElement(i, informationRecue.iemeElement(i)+bn);
        }
        emettre();//envoie l'information

    }

    /**
     * canal Tx Information (abstract dans la classe mere)
     * 
     */
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationRecue);
        }
        informationEmise = informationRecue;//transmetteur parfait src=dest

    }

}
