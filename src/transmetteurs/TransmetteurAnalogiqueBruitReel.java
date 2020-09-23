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
 */
public class TransmetteurAnalogiqueBruitReel extends Transmetteur<Float, Float>{
	float snr=0;
	Integer seed=null;
	private Information<Float> informationConverti;
	private Information<Float> informationDecale;
	int nbEchantillon=30;

	public TransmetteurAnalogiqueBruitReel(int seed, float snr, int nbEchantillon) {
		this.seed=seed;
		this.snr=snr;
		this.nbEchantillon=nbEchantillon;
		informationDecale =new Information<>();
		informationConverti =new Information<>();

	}

	public TransmetteurAnalogiqueBruitReel(float snr, int nbEchantillon) {
		super();
		this.snr=snr;
		this.nbEchantillon=nbEchantillon;
		informationDecale =new Information<>();
		informationConverti =new Information<>();

	}

	public TransmetteurAnalogiqueBruitReel(float snr ) {
		super();
		this.snr=snr;
		this.nbEchantillon=nbEchantillon;
		informationDecale =new Information<>();
		informationConverti =new Information<>();
		nbEchantillon=30;

	}

	/**
     * canal Rx Information (abstract dans la classe mere)
     *
     */
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
        ajoutDecalage();
        ajoutBruit();
        emettre();//envoie l'information

    }

    /**
     * canal Tx Information (abstract dans la classe mere)
     *
     */
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;//transmetteur parfait src=dest

    }
	private void ajoutDecalage() {
		int k=1;
		float coef=0.2f;
		int decallage=40;
		for (int i = 0; i < informationRecue.nbElements(); i+=nbEchantillon) {
			for (int j = ((k-1)*nbEchantillon); j < k*nbEchantillon; j++) {
				if (j-decallage >= 0) {
					informationDecale.add(informationRecue.iemeElement(j)+coef*informationRecue.iemeElement(j-decallage));
				}
				else {
					informationDecale.add(informationRecue.iemeElement(j));
				}
			}
			k++;
		}
	}
    private void ajoutBruit() {
    	float sigma=1;
    	int k=1;
    	Random rand1;
    	Random rand2;
    	if (seed!=null) {
			rand1=new Random(seed);
			rand2=new Random(seed);
		} else {
			rand1=new Random();
			rand2=new Random();
		}
    	float bruit=0f;
    	for (int i = 0; i < informationDecale.nbElements(); i+=nbEchantillon) {
    		sigma=calculSigma(i);
    		for (int j = ((k-1)*nbEchantillon); j < k*nbEchantillon; j++) {
        		bruit=(float) ((float) sigma*(Math.sqrt(-2*Math.log(1-rand1.nextFloat())))*(Math.cos(2*Math.PI*rand2.nextFloat())));
        		System.out.println(bruit);
        		//informationConverti.add(informationRecue.iemeElement(j)+bruit);
        		informationConverti.add(informationDecale.iemeElement(j)+bruit);
    		}
    		k++;
		}

    }

    private float calculSigma(int indexDepart) {
    	float Ps=0f;
    	float Sigma=0f;

        for (int i = indexDepart; i < indexDepart+nbEchantillon; i++) {
        	Ps+=Math.pow(informationRecue.iemeElement(i), 2);
		}
        Ps=Ps/nbEchantillon;
        //calcul de sigmaCarre
        Sigma= (float) (Ps/(2*Math.pow(10,snr/10)));
        Sigma=(float) Math.sqrt(Sigma);

    	//calcul de la puissance moyenne

    	return Sigma;
    }

}