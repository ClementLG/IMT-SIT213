

/**
 * Classe ExportTEB qui permet de lancer plusieur fois avec un SNR différents
 *
 * @author c.legruiec
 * @author e.leduc
 * @author p.maquin
 * @author g.fraignac
 * @author m.lejeune
 */
public class ExportTEB {

    public static void main(String[] args) {
        String arguments;
        //decommenter au besoin
        //-export pour activer l'ecriture dans un fichier
/*
        //Fct pour avoir Snrpb
        for (float i = -25; i < 20; i += 0.1) {
            arguments = "-mess 100000 -seed 40 -form NRZT -ampl -5 5 -nbEch 30 -snrpb " + i + " -export -codeur";
            args = arguments.split(" ");
            Simulateur.main(args);
        }*/
        /*
        //Fct pour avoir trajet multiple qui varie en fct de ech avec codeur
        for (int i = 0; i < 601; i += 1) {
            arguments = "-seed 40 -form NRZT -ampl -5 5 -snrpb 3 -ti " + i + " 1 " + " -codeur -export";
            args = arguments.split(" ");
            Simulateur.main(args);

        }*/
        /*
        //Fct pour avoir trajet multiple qui varie en fct de ech
        for (int i = 0; i < 601; i++) {
            arguments = "-mess 100000 -seed 40 -form NRZT -ampl -5 5 -snrpb 3 -ti " + i + " 1 -export";
            args = arguments.split(" ");
            Simulateur.main(args);

        }*/
    }

}
