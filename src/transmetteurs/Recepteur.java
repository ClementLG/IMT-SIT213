package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * Classe Recepteur herite de la classe Transmetteur
 *
 * @author c.legruiec
 * @author e.leduc
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 * 
 * @version R1.0 - Sept 2020
 */
public class Recepteur extends Transmetteur<Float, Boolean>{
	/**
	* Attribut d'instance : 'Amax' amplitude maximum du signal. Valeur par default 5V.
	*/
	private float Amax=5;
	/**
	* Attribut d'instance : 'Amin' amplitude minimale du signal. Valeur par default 0V.
	*/
	private float Amin=0;
	/**
	* Attribut d'instance : 'nbEchantillon' le nombre d'echantillon par bit. Valeur par default 30.
	*/
	private int nbEchantillon=30;
	/**
	* Attribut d'instance : 'decodeType' la forme du signal. Valeur par default RZ.
	*/
	private String decodeType="RZ";
	/**
	* Attribut d'instance : 'informationConverti' information recue avec ajout de bruit. 
	*/
	private Information<Boolean> informationConverti=new Information<>();
	
	/**
	 * Constructeur par defaut de Recepteur sans parametre
	 */
	public Recepteur() {
		super();

	}
	
	/**
     * Constructeur de recepteur a  parametrer avec des infos de base
     * @param Amax : Amplitude Max
     * @param Amin : Amplitude Min
     * @param nbEchantillon : Nombre d'echantillon par symbole
     * @param decodeType : le type de conversion analogique (NRZ,NRZT,RZ)
     */
	public Recepteur(float Amax, float Amin, int nbEchantillon, String decodeType) {
		super();
		this.Amax=Amax;
		this.Amin=Amin;
		this.nbEchantillon=nbEchantillon;
		this.decodeType=decodeType;
	}
	
	/**
     * canal Rx Information (abstract dans la classe mere)
     * 
     */
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
        CAN();
        emettre();

    }
    
    /**
     * canal Tx Information (abstract dans la classe mere)
     * 
     */
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;//transmetteur parfait src=dest

    }
    
    
    /**
     * Permet de selectionner le type de conversion a effectuer.
     * Permettra d'effectuer des operations personaliser si besoin.
     */
    private void CAN() throws InformationNonConforme {
    	switch (decodeType) {
		case "NRZ":
			toLogique((Amax+Amin)/2);
			break;
			
		case "NRZT":
			toLogique((Amax+Amin)/2);
			break;
			
		case "RZ":
			toLogique((Amax)/4);
			break;

		default:
			System.out.println("Aucun type d'encodage ne correspond a  l'entree saisie");
			throw new InformationNonConforme();
		}
    }
    
    /**
     * Converti le signal en logique qu'ils soient de type NRZ,NRZT ou RZ
     */
    private void toLogique(float seuil) {
    	int k=1;
    	float moy=Amax-Amin;
    	
    	for (int i = 0; i < informationRecue.nbElements(); i+=nbEchantillon) {
    		for (int j = ((k-1)*nbEchantillon); j < k*nbEchantillon; j++) {
				moy+=informationRecue.iemeElement(j);
			}
    		moy=moy/nbEchantillon;
    		k++;
    		//le signal pourrait etre deforme (si c'est pas un 1 on met 0 par default
    		if(moy>seuil) {
    			informationConverti.add(true);
    		}
    		else {
    			informationConverti.add(false);
    		}
    		moy=Amax-Amin;
		}
    	
    	
    }
    

	
	

}
