import sources.*;
import destinations.*;
import transmetteurs.*;

import information.*;

import visualisations.*;

import java.util.regex.*;
import java.util.*;
import java.lang.Math;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * La classe Simulateur permet de construire et simuler une chaine de
 * transmission composee d'une Source, d'un nombre variable de
 * Transmetteur(s) et d'une Destination.
 *
 * @author cousin
 * @author prou
 */
public class Simulateur {

    /**
     * indique si le Simulateur utilise des sondes d'affichage
     */
    private boolean affichage = false;
    /**
     * indique si le Simulateur utilise un message genere de maniere aleatoire
     */
    private boolean messageAleatoire = true;
    /**
     * indique si le Simulateur utilise un germe pour initialiser les generateurs aleatoires
     */
    private boolean aleatoireAvecGerme = false;
    /**
     * la valeur de la semence utilisee pour les generateurs aleatoires
     */
    private Integer seed = null;
    /**
     * la longueur du message aleatoire a transmettre si un message n'est pas impose
     */
    private int nbBitsMess = 100;
    /**
     * la chaine de caracteres correspondant a m dans l'argument -mess m
     */
    private String messageString = "100";


    /**
     * le  composant Source de la chaine de transmission
     */
    private Source<Boolean> source = null;
    /**
     * le  composant Transmetteur parfait logique de la chaine de transmission
     */
    private Transmetteur<Boolean, Boolean> transmetteurLogique = null;
    /**
     * le  composant Destination de la chaine de transmission
     */
    private Destination<Boolean> destination = null;


    /**
     * Le constructeur de Simulateur construit une chaine de
     * transmission composee d'une Source <Boolean>, d'une Destination
     * <Boolean> et de Transmetteur(s) [voir la methode
     * analyseArguments]...  <br> Les differents composants de la
     * chaine de transmission (Source, Transmetteur(s), Destination,
     * Sonde(s) de visualisation) sont crees et connectes.
     *
     * @param args le tableau des differents arguments.
     * @throws ArgumentsException si un des arguments est incorrect
     */
    public Simulateur(String[] args) throws ArgumentsException {
    	
    	//Analyse des arguments
        analyseArguments(args);
        
        //Instanciations des differents blocs de traitement
        if (messageAleatoire) {
        	source=new SourceAleatoire(nbBitsMess);
        } else {
        	source=new SourceFixe(messageString);
        }
        
        transmetteurLogique = new TransmetteurParfait();
        destination = new DestinationFinale();
        
        //Instanciations des differentes sondes
        SondeLogique viewSrc = new SondeLogique("ViewSrc", 720);
        SondeLogique viewTransmit = new SondeLogique("ViewTransmit", 720);
        
        
        //connexion des blocs ensembles
        source.connecter(transmetteurLogique);
        if(affichage) source.connecter(viewSrc);
        transmetteurLogique.connecter(destination);
        if(affichage) transmetteurLogique.connecter(viewTransmit);
        
        
        

    }


    /**
     * La methode analyseArguments extrait d'un tableau de chaines de
     * caracteres les differentes options de la simulation.  Elle met
     * a jour les attributs du Simulateur.
     *
     * @param args le tableau des differents arguments.
     *             <br>
     *             <br>Les arguments autorises sont :
     *             <br>
     *             <dl>
     *             <dt> -mess m  </dt><dd> m (String) constitue de 7 ou plus digits a 0 | 1, le message a transmettre</dd>
     *             <dt> -mess m  </dt><dd> m (int) constitue de 1 a 6 digits, le nombre de bits du message "aleatoire" a  transmettre</dd>
     *             <dt> -s </dt><dd> utilisation des sondes d'affichage</dd>
     *             <dt> -seed v </dt><dd> v (int) d'initialisation pour les generateurs aleatoires</dd>
     *             </dl>
     * @throws ArgumentsException si un des arguments est incorrect.
     */
    public void analyseArguments(String[] args) throws ArgumentsException {

        for (int i = 0; i < args.length; i++) {

            if (args[i].matches("-s")) {
                affichage = true;
            } else if (args[i].matches("-seed")) {
                aleatoireAvecGerme = true;
                i++;
                // traiter la valeur associee
                try {
                    seed = Integer.valueOf(args[i]);
                } catch (Exception e) {
                    throw new ArgumentsException("Valeur du parametre -seed  invalide :" + args[i]);
                }
            } else if (args[i].matches("-mess")) {
                i++;
                // traiter la valeur associee
                messageString = args[i];
                if (args[i].matches("[0,1]{7,}")) {
                    messageAleatoire = false;
                    nbBitsMess = args[i].length();
                } else if (args[i].matches("[0-9]{1,6}")) {
                    messageAleatoire = true;
                    nbBitsMess = Integer.valueOf(args[i]);
                    if (nbBitsMess < 1)
                        throw new ArgumentsException("Valeur du parametre -mess invalide : " + nbBitsMess);
                } else
                    throw new ArgumentsException("Valeur du parametre -mess invalide : " + args[i]);
            } else throw new ArgumentsException("Option invalide :" + args[i]);
        }

    }


    /**
     * La methode execute effectue un envoi de message par la source
     * de la chaine de transmission du Simulateur.
     *
     * @throws Exception si un probleme survient lors de l'execution
     */
    public void execute() throws Exception {
    	source.emettre();
              
    }


    /**
     * La methode qui calcule le taux d'erreur binaire en comparant
     * les bits du message emis avec ceux du message recu.
     *
     * @return La valeur du Taux dErreur Binaire.
     */
    public float calculTauxErreurBinaire() {
    	
    	//Attention si tailles des tableaux differentes ?? --> demander si possible
    	
    	int nbErr=0;
    	float TEB=0.0f;
    	for (int i = 0; i < destination.getInformationRecue().nbElements(); i++) {
			if(destination.getInformationRecue().iemeElement(i)!=source.getInformationEmise().iemeElement(i)) nbErr++;
		}
    	TEB=(nbErr*1.0f)/(source.getInformationEmise().nbElements());
    	
        return TEB;
    }


    /**
     * La fonction main instancie un Simulateur a l'aide des
     * arguments parametres et affiche le resultat de l'execution
     * d'une transmission.
     *
     * @param args les differents arguments qui serviront a l'instanciation du Simulateur.
     */
    public static void main(String[] args) {

        Simulateur simulateur = null;

        try {
            simulateur = new Simulateur(args);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(-1);
        }

        try {
            simulateur.execute();
            float tauxErreurBinaire = simulateur.calculTauxErreurBinaire();
            String s = "java  Simulateur  ";
            for (int i = 0; i < args.length; i++) {
                s += args[i] + "  ";
            }
            System.out.println(s + "  =>   TEB : " + tauxErreurBinaire);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.exit(-2);
        }
    }
}

