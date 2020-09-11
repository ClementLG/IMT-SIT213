package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

public class TransmetteurAnalogiqueParfait extends Transmetteur<Float, Float>{
	//canal Rx Information (abstract dans la classe mere)
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
        emettre();//envoie l'information

    }

    //canl Tx Information (abstract dans la classe mere)
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationRecue);
        }
        informationEmise = informationRecue;//transmetteur parfait src=dest

    }

}
