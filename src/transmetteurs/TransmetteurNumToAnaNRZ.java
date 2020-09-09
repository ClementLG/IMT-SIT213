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

public class TransmetteurNumToAnaNRZ extends Transmetteur<Boolean, Boolean> {

    //canal Rx Information (abstract dans la classe mere)
    public void recevoir(Information<Boolean> information) throws InformationNonConforme {

        informationRecue = information;
        Information informationAnalogique= new Information();
        int pointParPeriode=30;
        for (int i=0; i < informationRecue.nbElements(); i++) {
            if (informationRecue.iemeElement(i) == true)
            {
                for (int n=0; n < pointParPeriode; i++) {
                    informationAnalogique.add(1);
                }
            }
            else {
                for (int n=0; n < pointParPeriode; i++) {
                    informationAnalogique.add(1);
                }
            }

        }

        emettre();//envoie l'information

    }

    //canl Tx Information (abstract dans la classe mere)
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationRecue);
        }
        informationEmise = informationRecue;//transmetteur parfait src=dest

    }


}
	


