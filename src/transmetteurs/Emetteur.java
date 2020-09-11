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
		this.Amin=Amin;
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
    
	public void CNA() throws InformationNonConforme {
		switch (encodeType) {
		case "NRZ":
			ConvertToNRZ();
			break;
			
		case "NRZT":
			ConvertToNRZT();
			break;
			
		case "RZ":
			ConvertToRZ();
			break;

		default:
			System.out.println("Aucun type d'encodage ne correspond à l'entree saisie");
			throw new InformationNonConforme();
		}
	}
	
	public void changeCNAtype(String encodeType) {
		this.encodeType=encodeType;
	}
	
	public void getType() {
		System.out.println("Type d'emetteur: "+encodeType+"avec Amax="+Amax+"et Amin="+Amin+". nBechantillon par symbole "+nbEchantillon);
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
		float quantumP=Amax/divTrois;
		float quantumM=Amin/divTrois;
		boolean checkAfter=false;
		boolean checkBefore=false;
		
		
		
		for (int i = 0; i < informationRecue.nbElements(); i++) {
			if(i!=informationRecue.nbElements()-1) {
				checkAfter=informationRecue.iemeElement(i+1);
				//if(checkAfter) System.out.println("rien apres : "+i);
			}
			if(i!=0) {
				checkBefore=informationRecue.iemeElement(i-1);
				//if(checkBefore) System.out.println("rien avant : "+i);
			}
			
			if(i==0 && !informationRecue.iemeElement(i)) checkBefore=true;
			if(i==informationRecue.nbElements()-1 && !informationRecue.iemeElement(i)) checkAfter=true;
			if(i==informationRecue.nbElements()-1 && informationRecue.iemeElement(i)) checkAfter=false;
			
			if(informationRecue.iemeElement(i)) {
				if(!checkAfter && !checkBefore) {
					
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(quantumP*j);
					}
					for (int j = divTrois; j < 2*divTrois; j++) {
						informationConverti.add(Amax);
					}
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(Amax-(quantumP*j));
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
						informationConverti.add(quantumP*j);
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
						informationConverti.add(Amax-(quantumP*j));
					}
					checkAfter=false;
					checkBefore=false;
				}
				
				
				
				
				
			} else {
				
				
				
				if(checkAfter && checkBefore) {
					
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(quantumM*j);
					}
					for (int j = divTrois; j < 2*divTrois; j++) {
						informationConverti.add(Amin);
					}
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(Amin-(quantumM*j));
					}
					checkAfter=true;
					checkBefore=true;
				}
				
				if(!checkAfter && !checkBefore) {	
					for (int j = 0; j < nbEchantillon; j++) {
						informationConverti.add(Amin);
					}
					checkAfter=true;
					checkBefore=true;
				}
				
				
				if(!checkAfter && checkBefore) {	
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(quantumM*j);
					}
					for (int j = divTrois; j < 3*divTrois; j++) {
						informationConverti.add(Amin);
					}
					checkAfter=true;
					checkBefore=true;
				}
				
				if(checkAfter && !checkBefore) {	
					
					for (int j = 0; j < 2*divTrois; j++) {
						informationConverti.add(Amin);
					}
					for (int j = 0; j < divTrois; j++) {
						informationConverti.add(Amin-(quantumM*j));
					}
					checkAfter=true;
					checkBefore=true;
				}
			}
			
		}
		
	}
	
	private void ConvertToRZ() {
		int divTrois=nbEchantillon/3;
		
		for (int i = 0; i < informationRecue.nbElements(); i++) {
			if(informationRecue.iemeElement(i).equals(true)) {
				for (int j = 0; j < divTrois; j++) {
					informationConverti.add(0f);
				}
				for (int j = 0; j < divTrois; j++) {
					informationConverti.add(Amax);
				}
				for (int j = 0; j < divTrois; j++) {
					informationConverti.add(0f);
				}
				
			}
			if(informationRecue.iemeElement(i).equals(false)) {
				for (int j = 0; j < nbEchantillon; j++) {
					informationConverti.add(0f);
				}
				
			}
		}
	}
	
	

}
