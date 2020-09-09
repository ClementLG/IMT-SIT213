package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;


/**
 * Classe Recepteur herite d'un composant transmetteur d'informations
 *
 * @author c.legruiec
 * @author e.leduc
 * @author b.demoulin
 */

public class Recepteur extends Transmetteur<Integer, Boolean> {

    private Information <Boolean> informationNumerique;

    //canal Rx Information (abstract dans la classe mere)
    public void recevoir(Information<Integer> information) throws InformationNonConforme {
        informationRecue = information;
        emettre();//envoie l'information

    }

    //canl Tx Information (abstract dans la classe mere)
    public void emettre() throws InformationNonConforme {


    }


}



