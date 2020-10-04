package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

import java.util.Random;

/**
 * Classe TransmetteurAnalogiqueParfait h�rite de la classe Transmetteur
 *
 * @author c.legruiec
 * @author e.leduc
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 * @version R1.0 - Sept 2020
 */
public class TransmetteurAnalogiqueBruite extends Transmetteur<Float, Float> {
    /**
     * Attribut d'instance : 'snr' le rapport signal/bruit
     */
    float snr;
    /**
     * Attribut d'instance : 'seed' la graine de g�n�ration al�atoire. Valeur par default NULL.
     */
    Integer seed = null;
    /**
     * Attribut d'instance : 'informationConverti' information recue avec ajout de bruit.
     */
    private Information<Float> informationConverti = new Information<>();
    /**
     * Attribut d'instance : 'nbEchantillon'  nombre d'echantillon par bit. Valeur par default 30.
     */
    int nbEchantillon = 30;

    /**
     * Constructeur � 3 param�tres de la classe.
     *
     * @param seed          : la graine de g�n�ration al�atoire
     * @param snr           : le rapport signal/bruit
     * @param nbEchantillon : nombre d'echantillon par bit
     */
    public TransmetteurAnalogiqueBruite(int seed, float snr, int nbEchantillon) {
        super();
        this.seed = seed;
        this.snr = snr;
        this.nbEchantillon = nbEchantillon;

    }

    /**
     * Constructeur � 2 param�tres de la classe.
     *
     * @param snr           : le rapport signal/bruit
     * @param nbEchantillon : nombre d'echantillon par bit
     */
    public TransmetteurAnalogiqueBruite(float snr, int nbEchantillon) {
        super();
        this.snr = snr;
        this.nbEchantillon = nbEchantillon;

    }

    /**
     * Constructeur � 2 param�tres de la classe.
     *
     * @param snr : le rapport signal/bruit
     */
    public TransmetteurAnalogiqueBruite(float snr) {
        super();
        this.snr = snr;

    }

    /**
     * canal Rx Information (abstract dans la classe mere)
     */
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
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
        for (float info : informationRecue) {
            //sigma=calculSigma(i);
            bruit = (float) ((float) sigma * (Math.sqrt(-2 * Math.log(1 - rand1.nextFloat()))) * (Math.cos(2 * Math.PI * rand2.nextFloat())));
            informationConverti.add(info + bruit);
        }

    }

    /**
     * Permet de calculer la racine carr� de la puissance du bruit (sigma)
     */
    private float calculSigma() {
        float Ps = 0f;
        float Sigma = 0f;

        for (float info : informationRecue) {
            Ps += Math.pow(info, 2);
        }
        //on a enleve le nombre d'echantillon par bit dans les calculs suite a une simplication.
        //il ne reste que le calcul de l'esperance des Ak.
        Ps = Ps / (float) informationRecue.nbElements();
        //calcul de sigmaCarre
        //Trop bon par rapport au theorique
        //Sigma= (float) ((float) (Ps)/(2f*Math.pow(10,snr/10)));
        //Sigma=(float) Math.sqrt(Sigma);

        //Plus realiste et plus simple
        //cf: https://en.wikipedia.org/wiki/Signal-to-noise_ratio#:~:text=SNR%20is%20defined%20as%20the,by%20the%20Shannon%E2%80%93Hartley%20theorem.
        Sigma = (float) Math.pow(10, (Math.log10(Ps) - Math.log10(Math.pow(10, snr / 10))));
        Sigma = (float) Math.sqrt(Sigma);

        // DEBUG : // System.out.println("sigma:"+Sigma);
        return Sigma;
    }
}