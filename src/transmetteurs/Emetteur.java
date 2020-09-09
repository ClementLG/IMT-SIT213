package transmetteurs;

public class Emetteur {
	
	public Emetteur() {
		
	}
	
	public Emetteur(float Amax, float Amin) {
		
	}
	
	public void CNA(String encodeType) {
		switch (encodeType) {
		case "NRZ":
			
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

}
