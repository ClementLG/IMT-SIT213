package sources;

import java.util.Random;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * Classe SourceAleatoire h�rit� d'un composant source d'informations
 *
 * @author c.legruiec
 * @author e.leduc
 * @author b.demoulin
 */

public class SourceAleatoire extends Source<Boolean> {
	
    /**
     * Par defaut le simulateur doit generer et transmettre un message de longueur 100.
     */
    public SourceAleatoire() {
        super();//Appels des attributs de la classe mere
        informationGeneree = new Information<Boolean>();
        //generation d'une suite de 100 Boolean (<=>bits) aleatoires
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            informationGeneree.add(rand.nextBoolean());
        }

    }

    /**
     * Si m comporte au plus 6 chiffres decimaux 
     * et correspond � la representation en base 10 
     * d'un entier, cet entier est la longueur du message 
     * que le simulateur doit generer et transmettre.
     *
     * @param nbBits
     */
    public SourceAleatoire(int nbBits) {
        super();//Appels des attributs de la classe mere
        informationGeneree = new Information<Boolean>();
        //generation d'une suite de 100 Boolean (<=>bits) aleatoires
        Random rand = new Random();
        for (int i = 0; i < nbBits; i++) {
            informationGeneree.add(rand.nextBoolean());

        }

    }


}




