package sources;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;


public class SourceFixe extends Source<Boolean> {


    /**
     * Constructeur de la classe SourceFixe genere une chaine boolean fixee par le string passe en parametre
     * Si on a un 0 (dans le string passe en parametre), alors on on ajoutera un false (boolean) a la chaine binaire
     * Au contraire, si on a un 1, le boolean sera true.
     *
     * @param s
     */
    public SourceFixe(String s) {
        super();
        informationGeneree = new Information<Boolean>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0')
                informationGeneree.add(false);
            else
                informationGeneree.add(true);
        }
    }

    /**
     * Emettre une information binaire fixee par l'utilisateur
     * Leve l'exception InformationNonConforme
     */
    public void emettre() throws InformationNonConforme {

        for (DestinationInterface<Boolean> d : destinationsConnectees)
            d.recevoir(informationGeneree);

        informationEmise = informationGeneree;
    }


}
