package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * Classe TransmetteurParfait herite d un composant transmetteur d informations
 *
 * @author c.legruiec
 * @author e.leduc
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 * @version R1.0 - Sept 2020
 */

public class CodageEmission extends Transmetteur<Boolean, Boolean> {


    /**
     * Attribut d instance :  informationConverti .
     */
    private Information<Boolean> informationConverti = new Information<>();

    //canal Rx Information (abstract dans la classe mere)
    public void recevoir(Information<Boolean> information) throws InformationNonConforme{

        for (boolean info : information) {
            if (info) {
                informationConverti.add(true);
                informationConverti.add(false);
                informationConverti.add(true);
            } else {
                informationConverti.add(false);
                informationConverti.add(true);
                informationConverti.add(false);
            }
        }
        emettre();//envoie l information
    }

    //canal Tx Information (abstract dans la classe mere)
    public void emettre() throws InformationNonConforme {
        for ( DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
            destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;
    }
}