package sources;

import java.util.Random;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;


public class SourceAleatoire extends Source<Boolean> {
    /**
     * Le constructeur sans paramètre générera une chaine de boolean de 100 bits.
     */
    public SourceAleatoire() {
        //appel du constructeur mère Source pour l'initialisation des attributs
        super();

        informationGeneree = new Information<Boolean>();
        //generation d'une suite de bits aleatoires de taille 100
        Random rand = new Random();
        for (int i = 0; i < 99; i++) {
            informationGeneree.add(rand.nextBoolean());
        }

    }

    /**
     * Le constructeur génére une chaine de boolean de taille nbBits.
     *
     * @param nbBits
     */
    public SourceAleatoire(int nbBits) {
        //appel du constructeur mère Source pour l'initialisation des attributs
        super();
        informationGeneree = new Information<Boolean>();
        //generation d'une suite de bits aleatoires de taille nbBits
        Random rand = new Random();
        for (int i = 0; i < nbBits; i++) {
            informationGeneree.add(rand.nextBoolean());

        }

    }

    /**
     * Emettre une information binaire générée aléatoirement
     * Lève l'exception InformationNonConforme
     */

    @Override
    public void emettre() throws InformationNonConforme {

        for (DestinationInterface<Boolean> d : destinationsConnectees) {
            d.recevoir(informationGeneree);
        }
        informationEmise = informationGeneree;
    }


}




