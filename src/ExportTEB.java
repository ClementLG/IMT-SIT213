

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
			//lance 100k fois la simu
		    
			/*String arguments;
			arguments="-seed 40 -form NRZT -ampl -5 5 -snrpb -20 -export";
			args= arguments.split(" ");
			for (int i = 0; i < 100000; i++) {
				Simulateur.main(args);
			}*/
			
			
			String arguments;
			for (float i = -50; i < 20; i+=0.1) {
					arguments="-seed 40 -form RZ -ampl -5 5 -snrpb "+i+" -export";
					args= arguments.split(" ");
					Simulateur.main(args);

			}
			
			


	}

}
