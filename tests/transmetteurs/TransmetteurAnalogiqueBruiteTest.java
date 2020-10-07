package transmetteurs;

import destinations.DestinationFinale;
import information.Information;
import information.InformationNonConforme;
import org.junit.Test;
import sources.SourceFixe;

public class TransmetteurAnalogiqueBruiteTest {

    @Test
    public void testTAB() throws InformationNonConforme {
        float snr = -10f; // Rapport snr bas pour declencher une erreur
        boolean error = false;
        for (int i = 0; i < 5; i++) {
            if (testTABsnr(snr * i) == false) { // Detection d'une erreur
                error = true;
            }
        }
        if (error) assert true; // Si il y a une erreur alors il y a bien du bruit dans le signal
        else assert false; // Si aucune erreur est detecte le generateur de bruit ne fct pas
    }

    private boolean testTABsnr(float snr) throws InformationNonConforme {
        SourceFixe sf = new SourceFixe("11001");
        Emetteur cna = new Emetteur();
        Recepteur can = new Recepteur();
        TransmetteurAnalogiqueBruite testTp = new TransmetteurAnalogiqueBruite(snr);
        DestinationFinale df = new DestinationFinale();
        Information bools = new Information(); // Signal en bool
        bools.add(true);
        bools.add(true);
        bools.add(false);
        bools.add(false);
        bools.add(true);
        sf.connecter(cna);
        cna.connecter(testTp);
        testTp.connecter(can);
        can.connecter(df);
        sf.emettre();
        return (bools.equals(df.getInformationRecue())); // Verifie si signal bool = signal recu

    }
}