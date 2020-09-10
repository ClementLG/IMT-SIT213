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
			ConvertToNRZT();
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
	
	//Converti un signal logique en analogique en utilisant NRZ
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
	
	//Converti un signal logique en analogique en utilisant NRZT (triangle)
	private void ConvertToNRZT() {
		int divTrois=nbEchantillon/3;
		float quantum=Amax/divTrois;
		boolean checkAfter=false;
		boolean checkBefore=false;
		
		
		
		for (int i = 0; i < informationRecue.nbElements(); i++) {
			if(i!=informationRecue.nbElements()-1) {
				checkAfter=informationRecue.iemeElement(i+1);
				if(checkAfter) System.out.println("rien apres : "+i);
			}
			if(i!=0) {
				checkBefore=informationRecue.iemeElement(i-1);
				if(checkBefore) System.out.println("rien avant : "+i);
			}
			
			if(i==0 && !informationRecue.iemeElement(i)) checkBefore=true;
			if(i==informationRecue.nbElements()-1 && !informationRecue.iemeElement(i)) checkAfter=true;
			
			if(informationRecue.iemeElement(i)) {
				if(!checkAfter && !checkBefore) {
					
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(quantum*j);
					}
					for (int j = divTrois; j < 2*divTrois; j++) {
						informationConverti.add(Amax);
					}
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(Amax-(quantum*j));
					}
					checkAfter=false;
					checkBefore=false;
				}
				
				if(checkAfter && checkBefore) {	
					for (int j = 0; j < nbEchantillon; j++) {
						informationConverti.add(Amax);
					}
					checkAfter=false;
					checkBefore=false;
				}
				
				if(checkAfter && !checkBefore) {	
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(quantum*j);
					}
					for (int j = divTrois; j < 3*divTrois; j++) {
						informationConverti.add(Amax);
					}
					checkAfter=false;
					checkBefore=false;
				}
				
				if(!checkAfter && checkBefore) {	
					
					for (int j = 0; j < 2*divTrois; j++) {
						informationConverti.add(Amax);
					}
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(Amax-(quantum*j));
					}
					checkAfter=false;
					checkBefore=false;
				}
				
				
				
				
				
			} else {
				
				
				
				if(checkAfter && checkBefore) {
					
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(-quantum*j);
					}
					for (int j = divTrois; j < 2*divTrois; j++) {
						informationConverti.add(-Amax);
					}
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(-Amax+(quantum*j));
					}
					checkAfter=true;
					checkBefore=true;
				}
				
				if(!checkAfter && !checkBefore) {	
					for (int j = 0; j < nbEchantillon; j++) {
						informationConverti.add(-Amax);
					}
					checkAfter=true;
					checkBefore=true;
				}
				
				
				if(!checkAfter && checkBefore) {	
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(-quantum*j);
					}
					for (int j = divTrois; j < 3*divTrois; j++) {
						informationConverti.add(-Amax);
					}
					checkAfter=true;
					checkBefore=true;
				}
				
				if(checkAfter && !checkBefore) {	
					
					for (int j = 0; j < 2*divTrois; j++) {
						informationConverti.add(-Amax);
					}
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(-Amax+(quantum*j));
					}
					checkAfter=true;
					checkBefore=true;
				}
			}
			
		}
		
	}

}
