package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

<<<<<<< HEAD

=======
>>>>>>> clg
/**
 * Classe TransmetteurParfait herite d'un composant transmetteur d'informations
 *
 * @author c.legruiec
 * @author e.leduc
<<<<<<< HEAD
 * @author b.demoulin
=======
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 * @version R1.0 - Sept 2020
>>>>>>> clg
 */

public class CodageEmission extends Transmetteur<Boolean, Boolean> {

<<<<<<< HEAD
    //canal Rx Information (abstract dans la classe mere)
    public void recevoir(Information<Boolean> information) throws InformationNonConforme {
        informationRecue = new Information<>();
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

=======
    /**
     * Constructeur par defaut de CodageReception sans parametre
     */
    public CodageEmission() {
        super();
    }

    /**
     * Attribut d'instance : 'informationConverti'.
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
        emettre();//envoie l'information
>>>>>>> clg
    }

    //canal Tx Information (abstract dans la classe mere)
    public void emettre() throws InformationNonConforme {
<<<<<<< HEAD
        for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationRecue);
        }
        informationEmise = informationRecue;//transmetteur parfait src=dest

    }


}
	


=======
        for ( DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
            destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;
    }
}
>>>>>>> clg
