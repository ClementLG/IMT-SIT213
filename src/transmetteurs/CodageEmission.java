package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;


/**
 * Classe TransmetteurParfait herite d'un composant transmetteur d'informations
 *
 * @author c.legruiec
 * @author e.leduc
 * @author b.demoulin
 */

public class CodageEmission extends Transmetteur<Boolean, Boolean> {

    //canal Rx Information (abstract dans la classe mere)
    public void recevoir(Information<Boolean> information) throws InformationNonConforme {
        for (boolean info : information) {
            if (info) {
                informationRecue.add(true);
                informationRecue.add(false);
                informationRecue.add(true);
            }
            else {
                informationRecue.add(false);
                informationRecue.add(true);
                informationRecue.add(false);
            }
        }

        emettre();//envoie l'information

    }

    //canal Tx Information (abstract dans la classe mere)
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationRecue);
        }
        informationEmise = informationRecue;//transmetteur parfait src=dest

    }


}
	


