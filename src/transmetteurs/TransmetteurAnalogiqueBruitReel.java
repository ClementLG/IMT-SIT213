package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Classe TransmetteurAnalogiqueParfait hï¿½rite de la classe Transmetteur
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
	private Information<Float> informationConverti=new Information<>();
	private Information<Float> informationDecale=new Information<>();
	private ArrayList<Float> parametres=new ArrayList<Float>();
	int nbEchantillon=30;

	public TransmetteurAnalogiqueBruitReel(int seed, float snr, int nbEchantillon, ArrayList<Float> parametres) {
		super();
		this.seed=seed;
		this.snr=snr;
		this.nbEchantillon=nbEchantillon;
		this.parametres=parametres;
	}

	public TransmetteurAnalogiqueBruitReel(float snr, int nbEchantillon, ArrayList<Float> parametres) {
		super();
		this.snr=snr;
		this.nbEchantillon=nbEchantillon;
		this.parametres=parametres;

	}

	public TransmetteurAnalogiqueBruitReel(float snr, ArrayList<Float> parametres ) {
		super();
		this.snr=snr;
		this.parametres=parametres;
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
		ArrayList<Information<Float>> listeInformationsDecalees=new ArrayList<Information<Float>>();
		
		//System.out.println(parametres[0].length);
		
		
		for (int i = 0; i < parametres.size(); i+=2) {
			//vidange de la l'info de base
			informationDecale=new Information<Float>();
			//copie du signal normal
			for (float info : informationRecue) {
				//System.out.println(i+1);
				informationDecale.add(parametres.get(i+1)*info);
			}
			
			if (parametres.get(i)>0f) {
				informationDecale.addBefore(new ArrayList<Float>(Collections.nCopies(Math.abs(parametres.get(i).intValue()), 0f)));
				informationDecale.cut(0, informationRecue.nbElements());
			} else {
				informationDecale.cut(Math.abs(parametres.get(i).intValue()), informationDecale.nbElements());
				informationDecale.add(new ArrayList<Float>(Collections.nCopies(Math.abs(parametres.get(i).intValue()), 0f)));
			}
			
			
			Information<Float> test = new Information<Float>();
			for (float essai : informationDecale) {
				test.add(essai);
			}
			listeInformationsDecalees.add(test);
			test=null;
		}
		sommerArray(informationDecale, listeInformationsDecalees);
		
		
	}
	
	private void sommerArray(Information<Float> destResultat, ArrayList<Information<Float>> arraysofArrays){
		if(!arraysofArrays.isEmpty()) {
			destResultat=arraysofArrays.get(0);
			for (int i = 1; i < arraysofArrays.size(); i++) {
				for (int j = 0; j < arraysofArrays.get(i).nbElements(); j++) {
					destResultat.setIemeElement(j, destResultat.iemeElement(j)+arraysofArrays.get(i).iemeElement(j));
				}
			}
			informationDecale=arraysofArrays.get(0);
		}
		
		
		
		
		
	}
	
	private void ajoutBruit() {
    	float sigma=calculSigma();
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
    	for (int i = 0; i < informationRecue.nbElements(); i++) {
    		bruit=(float) ((float) sigma*(Math.sqrt(-2*Math.log(1-rand1.nextFloat())))*(Math.cos(2*Math.PI*rand2.nextFloat())));
    		if(informationDecale.nbElements()==0) informationConverti.add(informationRecue.iemeElement(i)+bruit);
    		else informationConverti.add(informationRecue.iemeElement(i)+informationDecale.iemeElement(i)+bruit);
    	}
    	
    	
    }
    
    private float calculSigma() {
    	float Ps=0f;
    	float Sigma=0f;
  
        for (float info : informationRecue) {
        	Ps+=Math.pow(info, 2);
		}
        //on a enlevé le nombre d'echantillon par bit dans les calculs suite à une simplication.
        //il ne reste que le calcul de l'esperance des Ak².
        Ps=Ps/informationRecue.nbElements(); 
        //calcul de sigmaCarre
        Sigma= (float) ((float) (Ps)/(2*Math.pow(10,snr/10)));
        Sigma=(float) Math.sqrt(Sigma);
    	
    	//calcul de la puissance moyenne
    	//System.out.println("sigma:"+Sigma);
    	return Sigma;
    }

}