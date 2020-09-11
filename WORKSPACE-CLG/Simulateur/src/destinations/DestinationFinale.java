package destinations;

import information.Information;
import information.InformationNonConforme;

public class DestinationFinale extends Destination<Boolean> {

    public DestinationFinale() {

    }

    /**
     * Recevoir une information binaire
     */
    public void recevoir(Information<Boolean> information) throws InformationNonConforme {

        this.informationRecue = information;
    }

}
