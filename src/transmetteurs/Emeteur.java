package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;


/**
 * Classe Emeteur herite d'un composant transmetteur d'informations
 *
 * @author c.legruiec
 * @author e.leduc
 * @author b.demoulin
 */

public class Emeteur extends Transmetteur<Boolean, Float> {
    private Information <Float> informationAnalogique;
    private String protocole;

    public Emeteur(String p){
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
                        for(int npt=0;npt<30;npt++){informationAnalogique.add(5f);}
                    }
                    else {
                        for(int npt=0;npt<30;npt++){informationAnalogique.add(0f);}
                    }
                }
                break;

            case "NRZT":
                float etat=0;
                for(int i=0;i< informationRecue.nbElements();i++){
                    if(informationRecue.iemeElement(i)){
                        if(etat==5f){
                            for(int npt=0;npt<30;npt++){informationAnalogique.add(5f);}}
                        else if(etat==0){
                            for(int npt=0;npt<10;npt++){informationAnalogique.add(etat);etat+=0.5;}
                            for(int npt=0;npt<20;npt++){informationAnalogique.add(5f);etat=5f;}}
                        else {
                            for(int npt=0;npt<20;npt++){informationAnalogique.add(etat);etat+=0.5;}}
                            for(int npt=0;npt<10;npt++){informationAnalogique.add(5f);etat=5f;}
                    }
                    else {
                        if(etat==-5f){
                            for(int npt=0;npt<30;npt++){informationAnalogique.add(-5f);}}
                        else if(etat==0){
                            for(int npt=0;npt<10;npt++){informationAnalogique.add(etat);etat-=0.5;}
                            for(int npt=0;npt<20;npt++){informationAnalogique.add(5f);etat=-5f;}}
                        else {
                            for(int npt=0;npt<20;npt++){informationAnalogique.add(etat);etat-=0.5;}}
                            for(int npt=0;npt<10;npt++){informationAnalogique.add(-5f);etat=-5f;}
                    }
                }
                break;

            case "RZ":

                for(int i=0;i< informationRecue.nbElements();i++){
                    for(int npt=0;npt<30;npt++){informationAnalogique.add(0f);}
                    if(informationRecue.iemeElement(i)){
                        for(int npt=0;npt<30;npt++){informationAnalogique.add(5f);}
                    }
                    else {
                        for(int npt=0;npt<30;npt++){informationAnalogique.add(-5f);}
                    }
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + protocole);
        }
    }
}



