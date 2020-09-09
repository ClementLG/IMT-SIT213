package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;


/**
 * Classe Emmeteur herite d'un composant transmetteur d'informations
 *
 * @author c.legruiec
 * @author e.leduc
 * @author b.demoulin
 */

public class Emmeteur extends Transmetteur<Boolean, Float> {
    private Information <Float> informationAnalogique;
    private String protocole;

    public Emmeteur(String p){
        protocole=p;
        informationAnalogique= new Information<>();
    }



    //canal Rx Information (abstract dans la classe mere)
    public void recevoir(Information<Boolean> information) throws InformationNonConforme {
        informationRecue = information;
        emettre();//envoie l'information

    }

    //canal Tx Information (abstract dans la classe mere)
    public void emettre() throws InformationNonConforme {
        encode();
        for (DestinationInterface<Float> destinationConnectee : destinationsConnectees) {
            destinationConnectee.recevoir(informationAnalogique);
        }

    }
    public void encode() throws InformationNonConforme {
        switch(protocole){

            case "NRZ":
                for(int i=0;i< this.informationRecue.nbElements();i++){
                    if(informationRecue.iemeElement(i)){
                        informationAnalogique.add(5f);
                        informationAnalogique.add(5f);
                    }
                    else {
                        informationAnalogique.add(0f);
                        informationAnalogique.add(0f);
                    }

                }
                break;

            case "NRZT":

                for(int i=0;i< informationRecue.nbElements();i++){
                    informationAnalogique.add(0f);
                    if(informationRecue.iemeElement(i)){
                        informationAnalogique.add(5f);
                    }
                    else informationAnalogique.add(-5f);
                }
                break;

            case "RZ":

                for(int i=0;i< informationRecue.nbElements();i++){
                    if(informationRecue.iemeElement(i)){
                        informationAnalogique.add(5f);
                        informationAnalogique.add(5f);
                    }
                    else {
                        informationAnalogique.add(-5f);
                        informationAnalogique.add(-5f);
                    }
                    informationAnalogique.add(0f);
                    informationAnalogique.add(0f);
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + protocole);
        }

    }


}



