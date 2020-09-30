

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
			//decommenter au besoin
		    //-export pour activer l'ecriture dans un fichier

			/*String arguments;
			arguments="-seed 40 -form NRZT -ampl -5 5 -snrpb -20 -export";
			args= arguments.split(" ");
			for (int i = 0; i < 100000; i++) {
				Simulateur.main(args);
			}*/


			/*String arguments;
			for (float i = 0; i < 15; i+=0.1) {
					arguments="-mess 999999 -seed 40 -form RZ -ampl 0 5 -nbEch 60 -snrpb "+i+" -export";
					args= arguments.split(" ");
					Simulateur.main(args);

			}*/

			/*String arguments;
			for (int i = 0; i < 20*30; i+=1) {
					arguments="-seed 40 -form NRZT -ampl -5 5 -snrpb 3 -ti "+i+" 0.5 "+-i+" 0.5 -export";
					args= arguments.split(" ");
					Simulateur.main(args);

			}*/

			String arguments;
			for (float i = -50; i < 21; i+=0.1) {
					arguments="-seed 40 -form RZ -ampl -5 5 -nbEch 30 -snrpb "+i+" -export";
					args= arguments.split(" ");
					Simulateur.main(args);

			}
			
			


	}

}
