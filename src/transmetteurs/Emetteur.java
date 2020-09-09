package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

public class Emetteur extends Transmetteur<Boolean, Float>{
	private float Amax;
	private float Amin;
	private int nbEchantillon;
	private String encodeType;
	private Information<Float> informationConverti;
	
	public Emetteur() {
		Amax=5;
		Amin=-5;
		encodeType="NRZ";
		nbEchantillon=30;
		informationConverti =new Information<>();
		
	}
	
	public Emetteur(float Amax, float Amin, int nbEchantillon, String encodeType) {
		this.Amax=Amax;
		this.Amin=Amax;
		this.nbEchantillon=nbEchantillon;
		this.encodeType=encodeType;
		informationConverti =new Information<>();
	}
	
	//canal Rx Information (abstract dans la classe mere)
    public void recevoir(Information<Boolean> information) throws InformationNonConforme {
        informationRecue = information;
        CNA();
        emettre();

    }
    
    //canl Tx Information (abstract dans la classe mere)
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationConverti);
        }
        informationEmise = informationConverti;//transmetteur parfait src=dest

    }
    
	public void CNA() {
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
	
	public void changeCNAtype(String encodeType) {
		this.encodeType=encodeType;
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
