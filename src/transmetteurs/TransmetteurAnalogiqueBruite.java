package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

import java.util.Random;

public class TransmetteurAnalogiqueBruite extends Transmetteur<Float, Float> {
    //canal Rx Information (abstract dans la classe mere)
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
        emettre();//envoie l'information

    }

    //canl Tx Information (abstract dans la classe mere)
    public void emettre() throws InformationNonConforme {
        bruitage(10f);
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
            destinationConnectee.recevoir(informationEmise);
        }


    }

    public void bruitage(float sigma) {
        Random rBruit = new Random();
        informationEmise = new Information();
        for (int i = 0; i < informationRecue.nbElements(); i += 1) {
            float bruit1 = rBruit.nextFloat();
            float bruit2 = rBruit.nextFloat();
            float bruit = (float) (sigma * Math.sqrt(-2 * Math.log(1 - bruit1)) * Math.cos(2 * Math.PI * bruit2));
            informationEmise.add(bruit + informationRecue.iemeElement(i));
        }

    }

}