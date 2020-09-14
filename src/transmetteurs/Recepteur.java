package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

public class Recepteur extends Transmetteur<Float, Boolean>{
	private final float Amax;
	private final float Amin;
	private final int nbEchantillon;
	private final String decodeType;
	private final Information<Boolean> informationConverti;
	
	public Recepteur(float Amax, float Amin, int nbEchantillon, String decodeType) {
		this.Amax=Amax;
		this.Amin=Amin;
		this.nbEchantillon=nbEchantillon;
		this.decodeType=decodeType;
		informationConverti =new Information<>();
	}
	
	//canal Rx Information (abstract dans la classe mere)
    public void recevoir(Information<Float> information) throws InformationNonConforme {
        informationRecue = information;
        CAN();
        emettre();

    }
    
    //canl Tx Information (abstract dans la classe mere)
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Boolean> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;//transmetteur parfait src=dest

    }
    
    private void CAN() throws InformationNonConforme {
    	switch (decodeType) {
		case "NRZ":
			toAna(Amax/3);
			break;
			
		case "NRZT":
			toAna(Amax/3);
			break;
			
		case "RZ":
			toAna(Amax/6);
			break;

		default:
			System.out.println("Aucun type d'encodage ne correspond à l'entree saisie");
			throw new InformationNonConforme();
		}
    }
    
    private void toAna(float seuil) {
    	int k=1;
    	float moy=Amax-Amin;
    	
    	for (int i = 0; i < informationRecue.nbElements(); i+=30) {
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
    	
    	
    }
    
    
    private void RZtoAna() {
    	
    }
	
	

}
