package sources;

import java.util.Random;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * Classe SourceAleatoire herite dun composant source dinformations
 *
 * @author c.legruiec
 * @author e.leduc
 * @author b.demoulin
 * 
 * @version R1.0 - Sept 2020
 */

public class SourceAleatoire extends Source<Boolean> {
	
    /**
     * Constructeur.
     * Par defaut le simulateur doit generer et transmettre un message de longueur 100.
     * @param seed : la graine de génération aléatoire à utiliser
     */
    public SourceAleatoire(Integer seed) {
        super();//Appels des attributs de la classe mere
        informationGeneree = new Information<Boolean>();
        //generation dune suite de 100 Boolean (<=>bits) aleatoires
        Random rand;
        if (seed !=null) rand = new Random(seed);
        else rand = new Random();
        for (int i = 0; i < 100; i++) {
            informationGeneree.add(rand.nextBoolean());
        }

    }

    /**
     * Constructeur.
     * Si m comporte au plus 6 chiffres decimaux 
     * et correspond a la representation en base 10 
     * dun entier, cet entier est la longueur du message 
     * que le simulateur doit generer et transmettre.
     *
     * @param nbBits : la taille du message à générer en bit
     * @param seed : la graine de génération aléatoire à utiliser
     */
    public SourceAleatoire(int nbBits, Integer seed) {
        super();//Appels des attributs de la classe mere
        informationGeneree = new Information<Boolean>();
        //generation dune suite de nbBits Boolean (<=>bits) aleatoires
        Random rand;
        if (seed !=null) rand = new Random(seed);
        else rand = new Random();
        
        for (int i = 0; i < nbBits; i++) {
            informationGeneree.add(rand.nextBoolean());

        }

    }


}




