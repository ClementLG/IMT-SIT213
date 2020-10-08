package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Classe TransmetteurAnalogiqueParfait herite de la classe Transmetteur
 *
 * @author c.legruiec
 * @author e.leduc
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 * @version R1.0 - Sept 2020
 */
public class TransmetteurAnalogiqueBruitReel extends Transmetteur<Float, Float> {
    /**
     * Attribut d'instance : 'snrpb' le rapport signal/bruit
     */
    float snrpb;
    /**
     * Attribut d'instance : 'seed' la graine de generation aleatoire. Valeur par default NULL.
     */
    Integer seed = null;
    /**
     * Attribut d'instance : 'informationConverti' information recue avec ajout de bruit.
     */
    private Information<Float> informationConverti = new Information<>();
    /**
     * Attribut d'instance : 'informationDecale' Somme de signaux decale et modifie en amplitude selon un coeff.
     */
    private Information<Float> informationDecale = new Information<>();
    /**
     * Attribut d'instance : 'parametres' Decalage(s) et coefficient(s) des multiTrajets.
     */
    private ArrayList<Float> parametres = new ArrayList<Float>();
    /**
     * Attribut d'instance : 'nbEchantillon' nombre d'echantillon par bit.
     */
    int nbEchantillon = 30;

    /**
     * Constructeur de TransmetteurAnalogiqueBruitReel avec 4 parametres
     *
     * @param seed          : graine de generation aleatoire
     * @param snrpb         : rapport signal sur bruit
     * @param nbEchantillon : Nombre d'echantillon par symbole
     * @param parametres    : paramettre du/des multi-trajet(s)
     */
    public TransmetteurAnalogiqueBruitReel(int seed, float snrpb, int nbEchantillon, ArrayList<Float> parametres) {
        super();
        this.seed = seed;
        this.snrpb = snrpb;
        this.nbEchantillon = nbEchantillon;
        this.parametres = parametres;
    }

    /**
     * Constructeur de TransmetteurAnalogiqueBruitReel avec 3 parametres
     *
     * @param snrpb         : rapport signal sur bruit
     * @param nbEchantillon : Nombre d'echantillon par symbole
     * @param parametres    : paramettre du/des multi-trajet(s)
     */
    public TransmetteurAnalogiqueBruitReel(float snrpb, int nbEchantillon, ArrayList<Float> parametres) {
        super();
        this.snrpb = snrpb;
        this.nbEchantillon = nbEchantillon;
        this.parametres = parametres;

    }

    /**
     * Constructeur de TransmetteurAnalogiqueBruitReel avec 2 parametres
     *
     * @param snrpb      : rapport signal sur bruit
     * @param parametres : paramettre du/des multi-trajet(s)
     */
    public TransmetteurAnalogiqueBruitReel(float snrpb, ArrayList<Float> parametres) {
        super();
        this.snrpb = snrpb;
        this.parametres = parametres;
    }

    /**
     * canal Rx Information (abstract dans la classe mere)
     */
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
        ajoutDecalage();
        ajoutBruit();
        emettre();//envoie l'information

    }

    /**
     * canal Tx Information (abstract dans la classe mere)
     */
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
            destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;

    }

    /**
     * Calcul les multitrajets et les ajoute dans informationDecale sous la forme d'une somme des signaux decales.
     */
    private void ajoutDecalage() {
        ArrayList<Information<Float>> listeInformationsDecalees = new ArrayList<Information<Float>>();

        for (int i = 0; i < parametres.size(); i += 2) {
            informationDecale = new Information<Float>();
            //copie du signal normal
            for (float info : informationRecue) {
                //Copie du signal avec le coef choisi en parametre
                informationDecale.add(parametres.get(i + 1) * info);
            }

            if (parametres.get(i) > 0f) {
                //Si decalage superieur à zero decaller le signal de X echantillons
                //Ajoute X zero au debut du signal
                informationDecale.addBefore(new ArrayList<Float>(Collections.nCopies(Math.abs(parametres.get(i).intValue()), 0f)));
                //Enleve X points à la fin du signal
                informationDecale.cut(0, informationRecue.nbElements());
            } else {
                //Si decalage inferieur à zero decaller le signal de -X echantillons
                informationDecale.cut(Math.abs(parametres.get(i).intValue()), informationDecale.nbElements());
                informationDecale.add(new ArrayList<Float>(Collections.nCopies(Math.abs(parametres.get(i).intValue()), 0f)));
            }
            //Liste de tous les signals decales
            listeInformationsDecalees.add(informationDecale);
        }
        sommerArray(informationDecale, listeInformationsDecalees);

    }

    /**
     * Somme des arrayList de float
     *
     * @param destResultat   : destination de la somme
     * @param arraysofArrays : Liste des ArraysList a sommer
     */
    private void sommerArray(Information<Float> destResultat, ArrayList<Information<Float>> arraysofArrays) {
        if (!arraysofArrays.isEmpty()) {
            destResultat = arraysofArrays.get(0);
            for (int i = 1; i < arraysofArrays.size(); i++) {
                for (int j = 0; j < arraysofArrays.get(i).nbElements(); j++) {
                    destResultat.setIemeElement(j, destResultat.iemeElement(j) + arraysofArrays.get(i).iemeElement(j));
                }
            }
            informationDecale = destResultat;
        }


    }

    /**
     * Permet d'ajouter le bruit sur le signal recue. Le signal bruite est stocke dans informationConverti.
     */
    private void ajoutBruit() {
        float sigma = calculSigma();
        Random rand1;
        Random rand2;
        if (seed != null) {
            rand1 = new Random(seed);
            rand2 = new Random(seed);
        } else {
            rand1 = new Random();
            rand2 = new Random();
        }
        float bruit = 0f;
        for (int i = 0; i < informationRecue.nbElements(); i++) {
            bruit = (float) ((float) sigma * (Math.sqrt(-2 * Math.log(1 - rand1.nextFloat()))) * (Math.cos(2 * Math.PI * rand2.nextFloat())));
            if (informationDecale.nbElements() == 0) informationConverti.add(informationRecue.iemeElement(i) + bruit);
            else informationConverti.add(informationRecue.iemeElement(i) + informationDecale.iemeElement(i) + bruit);
        }


    }

    /**
     * Permet de calculer la racine carre de la puissance du bruit (sigma)
     */
    private float calculSigma() {
        float Ps = 0f;
        float sigma;
        float snr;
        //Calcul de la puissance totale du signal
        for (float info : informationRecue) {
            Ps += Math.pow(info, 2);
        }
        //Calcul de la puissance par echantillon
        Ps = Ps / (float) informationRecue.nbElements();
        //Calcul du snr en fonction du nombre d'echantillon et du snr par bit en dB
        snr = snrpb - 10 * (float) Math.log10(nbEchantillon / 2);
        //System.out.println("snr = " + snr); //DEBUG
        //System.out.println("snrpb =" + snrpb);//DEBUG
        //Calcul de sigma au carre
        sigma =  Ps  / (float) Math.pow(10, (snr / 10));
        //Calcul de sigma
        sigma = (float) Math.sqrt(sigma);
        //System.out.println("sigma =" + sigma);//DEBUG
        return sigma;
    }

}