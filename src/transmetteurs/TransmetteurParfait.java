package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;


/**
 * Classe TransmetteurParfait herite d un composant transmetteur d informations
 *
 * @author c.legruiec
 * @author e.leduc
 * @author b.demoulin
 * 
 * @version R1.0 - Sept 2020
 */

public class TransmetteurParfait extends Transmetteur<Boolean, Boolean> {

    //canal Rx Information (abstract dans la classe mere)
    public void recevoir(Information<Boolean> information) throws InformationNonConforme {
        informationRecue = information;
        emettre();//envoie l information

    }

    //canl Tx Information (abstract dans la classe mere)
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationRecue);
        }
        informationEmise = informationRecue;//transmetteur parfait src=dest

    }


}
	


