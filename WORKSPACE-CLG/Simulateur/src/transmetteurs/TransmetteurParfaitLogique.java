package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

public class TransmetteurParfaitLogique extends Transmetteur<Boolean, Boolean> {

    public TransmetteurParfaitLogique() {
    }

    // Reception de l'information
    public void recevoir(Information<Boolean> information) throws InformationNonConforme {
        informationRecue = information;
        emettre();

    }

    // Emission de l'information
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Boolean> d : destinationsConnectees)
            d.recevoir(informationRecue);
        informationEmise = informationRecue;

    }


}
	


