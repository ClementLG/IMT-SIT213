package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

public abstract class Emetteur extends Transmetteur<Boolean, Float>{
	private float Amax;
	private float Amin;
	private int nbEchantillon;
	private Information<Float> informationConverti;
	
	public Emetteur() {
		Amax=5;
		Amin=-5;
		
	}
	
	public Emetteur(float Amax, float Amin, int nbEchantillon) {
		this.Amax=Amax;
		this.Amin=Amax;
		this.nbEchantillon=nbEchantillon;
	}
	
	//canal Rx Information (abstract dans la classe mere)
    public void recevoir(Information<Boolean> information) throws InformationNonConforme {
        informationRecue = information;
        emettre();//envoie l'information

    }
    
    //canl Tx Information (abstract dans la classe mere)
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;//transmetteur parfait src=dest

    }
    
	public void CNA(String encodeType) {
		switch (encodeType) {
		case "NRZ":
			ConvertToNRZ();
			
			
			break;
		case "NRZT":
			
			break;
		case "RZ":
			
			break;

		default:
			System.out.println("Aucun type d'encodage ne correspond à l'entree saisie");
			break;
		}
	}
	
	private void ConvertToNRZ() {
		for (int i = 0; i < informationRecue.nbElements(); i++) {
			if(informationRecue.iemeElement(i).equals(true)) {
				for (int j = 0; j < nbEchantillon; j++) {
					informationConverti.add(Amax);
				}
				
			}
			if(informationRecue.iemeElement(i).equals(false)) {
				for (int j = 0; j < nbEchantillon; j++) {
					informationConverti.add(Amin);
				}
				
			}
		}
		
	}

}
