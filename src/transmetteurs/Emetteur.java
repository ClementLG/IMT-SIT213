package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

/**
 * Classe Emetteur hérité de la classe Transmetteur
 *
 * @author c.legruiec
 * @author e.leduc
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 */
public class Emetteur extends Transmetteur<Boolean, Float>{
	private float Amax;
	private float Amin;
	private int nbEchantillon;
	private String encodeType;
	private Information<Float> informationConverti;
<<<<<<< HEAD
	
	
=======


>>>>>>> origin/Gui
	/**
     * Constructeur par default de Emetteur sans parametre
     */
	public Emetteur() {
		Amax=5;
<<<<<<< HEAD
		Amin=-5;
		encodeType="NRZ";
		nbEchantillon=30;
		informationConverti =new Information<>();
		
	}
	
=======
		Amin=-0;
		encodeType="RZ";
		nbEchantillon=30;
		informationConverti =new Information<>();

	}

>>>>>>> origin/Gui
	/**
     * Constructeur de Emetteur à parametrer avec des infos de base
     * @param Amax : Amplitude Max
     * @param Amin : Amplitude Min
     * @param nbEchantillon : Nombre d'echantillon par symbole
     * @param encodeType : le type de conversion analogique (NRZ,NRZT,RZ)
     */
	public Emetteur(float Amax, float Amin, int nbEchantillon, String encodeType) {
		this.Amax=Amax;
		this.Amin=Amin;
		this.nbEchantillon=nbEchantillon;
		this.encodeType=encodeType;
		informationConverti =new Information<>();
	}
<<<<<<< HEAD
	
	/**
     * canal Rx Information (abstract dans la classe mere)
     * 
=======

	/**
     * canal Rx Information (abstract dans la classe mere)
     *
>>>>>>> origin/Gui
     */
    public void recevoir(Information<Boolean> information) throws InformationNonConforme {
        informationRecue = information;
        CNA();
        emettre();

    }
<<<<<<< HEAD
    
    /**
     * canal Tx Information (abstract dans la classe mere)
     * 
     */
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
        	destinationConnectee.recevoir(informationConverti);
=======

    /**
     * canal Tx Information (abstract dans la classe mere)
     *
     */
    public void emettre() throws InformationNonConforme {
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
            destinationConnectee.recevoir(informationConverti);
>>>>>>> origin/Gui
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
<<<<<<< HEAD
	public void CNA() throws InformationNonConforme {
=======
	private void CNA() throws InformationNonConforme {
>>>>>>> origin/Gui
		switch (encodeType) {
		case "NRZ":
			ConvertToNRZ();
			break;
<<<<<<< HEAD
			
		case "NRZT":
			ConvertToNRZT();
			break;
			
=======

		case "NRZT":
			ConvertToNRZT();
			break;

>>>>>>> origin/Gui
		case "RZ":
			ConvertToRZ();
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
     * Permet de convertir un signal logique en analogigue NRZ
     */
	private void ConvertToNRZ() {
<<<<<<< HEAD
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
	
=======
        for (Boolean recu : informationRecue) {
            if (recu) {
                for (int j = 0; j < nbEchantillon; j++) {
                    informationConverti.add(Amax);
                }
            } // Ajoute Amax a informationConverti lorsque le bit recus est True
            if (!recu) {
                for (int j = 0; j < nbEchantillon; j++) {
                    informationConverti.add(Amin);
                }

            }// Ajoute Amin a informationConverti lorsque le bit recus est False
        }
    }

>>>>>>> origin/Gui
	/**
     * Permet de convertir un signal logique en analogigue NRZT
     */
	private void ConvertToNRZT() {
<<<<<<< HEAD
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
	
=======
        int divTrois = nbEchantillon / 3;
        float quantumP = Amax / divTrois;
        float quantumM = Amin / divTrois;
        int nbElem=informationRecue.nbElements();
        boolean checkAfter = false;
        boolean checkBefore = false;
        checkAfter = informationRecue.iemeElement(1);

        if (informationRecue.iemeElement(0)) {
            if (!checkAfter){
            for (int j = 0; j < divTrois; j++) {
                informationConverti.add(quantumP * j);
            }
            for (int j = 0; j < divTrois; j++) {
                informationConverti.add(Amax);
            }
            for (int j = 0; j < divTrois; j++) {
                informationConverti.add(Amax - (quantumP * j));
            }
            }
            else{
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(quantumP * j);
                }
                for (int j = 0; j < 2* divTrois; j++) {
                    informationConverti.add(Amax);
                }
            }
        }
        else {
            if (checkAfter){
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(quantumM * j);
                }
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(Amin);
                }
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(Amin - (quantumM * j));
                }
            }
            else{
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(quantumM * j);
                }
                for (int j = 0; j < 2* divTrois; j++) {
                    informationConverti.add(Amin);
                }
            }
        }

        for (int i = 1; i < nbElem-1; i++) {

            checkAfter = informationRecue.iemeElement(i + 1);
            checkBefore = informationRecue.iemeElement(i - 1);

            if (informationRecue.iemeElement(i)) {
                if (!checkAfter && !checkBefore) {

                    for (int j = 0; j < divTrois; j++) {
                        informationConverti.add(quantumP * j);
                    }
                    for (int j = 0; j < divTrois; j++) {
                        informationConverti.add(Amax);
                    }
                    for (int j = 0; j < divTrois; j++) {
                        informationConverti.add(Amax - (quantumP * j));
                    }
                }

                else if (checkAfter && checkBefore) {
                    for (int j = 0; j < nbEchantillon; j++) {
                        informationConverti.add(Amax);
                    }
                }

                else if (checkAfter && !checkBefore) {
                    for (int j = 0; j < divTrois; j++) {
                        informationConverti.add(quantumP * j);
                    }
                    for (int j = 0; j < 2 * divTrois; j++) {
                        informationConverti.add(Amax);
                    }
                }

                else {

                    for (int j = 0; j < 2 * divTrois; j++) {
                        informationConverti.add(Amax);
                    }
                    for (int j = 0; j < divTrois; j++) {
                        informationConverti.add(Amax - (quantumP * j));
                    }
                }

            }
            else {

                if (checkAfter && checkBefore) {

                    for (int j = 0; j < divTrois; j++) {
                        informationConverti.add(quantumM * j);
                    }
                    for (int j = 0; j < divTrois; j++) {
                        informationConverti.add(Amin);
                    }
                    for (int j = 0; j < divTrois; j++) {
                        informationConverti.add(Amin - (quantumM * j));
                    }

                }

                else if (!checkAfter && !checkBefore) {
                    for (int j = 0; j < nbEchantillon; j++) {
                        informationConverti.add(Amin);
                    }

                }

                else if (!checkAfter && checkBefore) {
                    for (int j = 0; j < divTrois; j++) {
                        informationConverti.add(quantumM * j);
                    }
                    for (int j = 0; j < 2 * divTrois; j++) {
                        informationConverti.add(Amin);
                    }
                }

                else  {
                    for (int j = 0; j < 2 * divTrois; j++) {
                        informationConverti.add(Amin);
                    }
                    for (int j = 0; j < divTrois; j++) {
                        informationConverti.add(Amin - (quantumM * j));
                    }
                }
            }
        }

        checkBefore = informationRecue.iemeElement(nbElem-2);;
        if (informationRecue.iemeElement(nbElem-1)) {
            if (!checkBefore){
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(quantumP * j);
                }
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(Amax);
                }
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(Amax - (quantumP * j));
                }
            }
            else{

                for (int j = 0; j < 2* divTrois; j++) {
                    informationConverti.add(Amax);
                }
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(Amax-(quantumP * j));
                }
            }
        }
        else {
            if (checkBefore){
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(quantumM * j);
                }
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(Amin);
                }
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(Amin - (quantumM * j));
                }
            }
            else{

                for (int j = 0; j < 2* divTrois; j++) {
                    informationConverti.add(Amin);
                }
                for (int j = 0; j < divTrois; j++) {
                    informationConverti.add(Amin-(quantumM * j));
                }
            }
        }
    }

>>>>>>> origin/Gui
	/**
     * Permet de convertir un signal logique en analogigue RZ
     */
	private void ConvertToRZ() {
<<<<<<< HEAD
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
	
	
=======

        int divTrois = nbEchantillon / 3;

        for (Boolean recu : informationRecue) {
            if (recu) { // Ajoute Amax pendant T/3 entoure de 0
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
            if (!recu) {
                for (int j = 0; j < nbEchantillon; j++) {
                    informationConverti.add(0f);
                }

            }
        }
    }
>>>>>>> origin/Gui

}
