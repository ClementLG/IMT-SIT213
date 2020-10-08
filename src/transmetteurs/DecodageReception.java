package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * Classe Recepteur herite de la classe Transmetteur
 *
 * @author c.legruiec
 * @author e.leduc
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 * @version R1.0 - Sept 2020
 */

public class DecodageReception extends Transmetteur<Boolean, Boolean> {


    /**
     * Attribut d instance :  informationConverti .
     */
    private Information<Boolean> informationConverti = new Information<>();

    /**
     * Constructeur par defaut de DecodageReception sans parametre
     */
    public DecodageReception() {
        super();

    }


    /**
     * canal Rx Information (abstract dans la classe mere)
     */
    public void recevoir(Information<Boolean> information) throws InformationNonConforme {
        informationRecue = information;
        decode();
        emettre();

    }

    /**
     * canal Tx Information (abstract dans la classe mere)
     */
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
            destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;
    }

    private void decode() throws InformationNonConforme {
        int nb = 0;

        if (informationRecue.nbElements() % 3 != 0) {
            throw new InformationNonConforme("Message recu non divisible par 3");
        } else {
            nb = informationRecue.nbElements();
            for (int i = 0; i < nb; i += 3) {
                String temp = "";
                temp = temp + informationRecue.iemeElement(i);
                temp = temp + informationRecue.iemeElement(i + 1);
                temp = temp + informationRecue.iemeElement(i + 2);
                switch (temp) {

                    case ("falsefalsefalse"): //000
                    case ("falsetruefalse"): //010
                    case ("truetruefalse"): //110
                    case ("falsetruetrue"): //011
                        informationConverti.add(false);
                        break;
                    case ("truetruetrue"): //111
                    case ("truefalsetrue"): //101
                    case ("falsefalsetrue"): //001
                    case ("truefalsefalse"): //100
                        informationConverti.add(true);
                        break;

                }
            }

        }

    }
}

