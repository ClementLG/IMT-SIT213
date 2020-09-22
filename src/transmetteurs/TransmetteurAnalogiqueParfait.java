package transmetteurs;

import destinations.DestinationInterface;
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
public class TransmetteurAnalogiqueParfait extends Transmetteur<Float, Float>{
<<<<<<< HEAD
	
	/**
     * canal Rx Information (abstract dans la classe mere)
     * 
=======

	/**
     * canal Rx Information (abstract dans la classe mere)
     *
>>>>>>> origin/Gui
     */
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
        emettre();//envoie l'information

    }

    /**
     * canal Tx Information (abstract dans la classe mere)
<<<<<<< HEAD
     * 
=======
     *
>>>>>>> origin/Gui
     */
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationRecue);
        }
        informationEmise = informationRecue;//transmetteur parfait src=dest

    }

}
