package sources;

import information.Information;

/**
 * Classe SourceFixe herite d un composant source d informations
 *
 * @author c.legruiec
 * @author e.leduc
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 *
 * @version R1.0 - Sept 2020
 */

public class SourceFixe extends Source<Boolean> {


    /**
     * Si m est une suite de 0 et de 1 de longueur au moins egale à 7
     * m est le message à emettre
     *
     * @param  m  : le message a transmettre
     */
    public SourceFixe(String m) {
        super();//Appels des attributs de la classe mere
        informationGeneree = new Information<Boolean>();
        for (int i = 0; i < m.length(); i++) {
        	//si le caractere vaut 1, le boolean vaut True
            if (m.charAt(i) == '1') informationGeneree.add(true);
            //Sinon le boolean vaut False. Rq: le message m est conforme, verifié avant dans le main
            else informationGeneree.add(false);
        }
    }


}
