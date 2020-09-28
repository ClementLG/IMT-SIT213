

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
			for (float i = -50; i < 21; i+=0.1) {
					arguments="-seed 40 -form RZ -ampl -5 5 -nbEch 30 -snrpb "+i+" -export";
					args= arguments.split(" ");
					Simulateur.main(args);

			}
			
			


	}

}
