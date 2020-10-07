package destinations;

import information.Information;
import information.InformationNonConforme;

/**
 * Classe DestinationFinale herite d'un composant destination
 *
 * @author c.legruiec
 * @author e.leduc
 * @author b.demoulin
 */

public class DestinationFinale extends Destination<Boolean> {

    public DestinationFinale() {
    	super();
    }

    
    //on definie notre methode (abstract dans la classe mere)
    public void recevoir(Information<Boolean> information) throws InformationNonConforme {
        informationRecue = information;
    }

}
