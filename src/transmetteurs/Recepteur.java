package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * Classe Recepteur hérité de la classe Transmetteur
 *
 * @author c.legruiec
 * @author e.leduc
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 */
public class Recepteur extends Transmetteur<Float, Boolean>{
	private float Amax;
	private float Amin;
	private int nbEchantillon;
	private String decodeType;
	private Information<Boolean> informationConverti;
<<<<<<< HEAD
	
=======

>>>>>>> origin/Gui
	/**
     * Constructeur de recepteur à parametrer avec des infos de base
     * @param Amax : Amplitude Max
     * @param Amin : Amplitude Min
     * @param nbEchantillon : Nombre d'echantillon par symbole
     * @param decodeType : le type de conversion analogique (NRZ,NRZT,RZ)
     */
<<<<<<< HEAD
	public Recepteur(float Amax, float Amin, int nbEchantillon, String decodeType) {
		this.Amax=Amax;
		this.Amin=Amin;
		this.nbEchantillon=nbEchantillon;
		this.decodeType=decodeType;
		informationConverti =new Information<>();
	}
	
	/**
     * canal Rx Information (abstract dans la classe mere)
     * 
=======
    public Recepteur(float Amax, float Amin, int nbEchantillon, String decodeType) {
        this.Amax = Amax;
        this.Amin = Amin;
        this.nbEchantillon = nbEchantillon;
        this.decodeType = decodeType;
        informationConverti = new Information<>();
    }

    /**
     * canal Rx Information (abstract dans la classe mere)
     *
>>>>>>> origin/Gui
     */
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
        CAN();
        emettre();

    }
<<<<<<< HEAD
    
    /**
     * canal Tx Information (abstract dans la classe mere)
     * 
=======

    /**
     * canal Tx Information (abstract dans la classe mere)
     *
>>>>>>> origin/Gui
     */
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;//transmetteur parfait src=dest

    }
<<<<<<< HEAD
    
    
=======


>>>>>>> origin/Gui
    /**
     * Permet de selectionner le type de conversion a effectuer
     * Permettra d'effectuer des operations personaliser si besoin
     */
    private void CAN() throws InformationNonConforme {
    	switch (decodeType) {
		case "NRZ":
			toLogique(Amax/2);
			break;
<<<<<<< HEAD
			
		case "NRZT":
			toLogique(Amax/2);
			break;
			
		case "RZ":
			toLogique(Amax/3);
			informationConverti.toString();
=======

		case "NRZT":
			toLogique(Amax/2);
			break;

		case "RZ":
			toLogique(Amax/9);
>>>>>>> origin/Gui
			break;

		default:
			System.out.println("Aucun type d'encodage ne correspond à l'entree saisie");
			throw new InformationNonConforme();
		}
    }
<<<<<<< HEAD
    
=======

>>>>>>> origin/Gui
    /**
     * Converti le signal en logique qu'ils soient de type NRZ,NRZT ou RZ
     */
    private void toLogique(float seuil) {
    	int k=1;
    	float moy=Amax-Amin;
<<<<<<< HEAD
    	
=======

>>>>>>> origin/Gui
    	for (int i = 0; i < informationRecue.nbElements(); i+=nbEchantillon) {
    		for (int j = ((k-1)*nbEchantillon); j < k*nbEchantillon; j++) {
				moy+=informationRecue.iemeElement(j);
			}
    		moy=moy/nbEchantillon;
    		k++;
    		//le signal pourrait etre deformé (si c'est pas un 1 on met 0 par défault
    		if(moy>seuil) {
    			informationConverti.add(true);
    		}
    		else {
    			informationConverti.add(false);
    		}
    		moy=Amax-Amin;
		}
<<<<<<< HEAD
    	
    	
    }
    

	
	

=======
    }
>>>>>>> origin/Gui
}
