package transmetteurs;

import destinations.DestinationFinale;
import information.Information;
import information.InformationNonConforme;
import org.junit.Test;
import sources.SourceFixe;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TransmetteurAnalogiqueBruitReelTest {

    @Test
    public void testTABRNone() {
        //Verifie que le Transmetteur ne fait rien si il n'y a pas d'argument de decalage
        ArrayList<Float> parametres = new ArrayList<>();
        for (int x = 1; x != 30; x++) {
            Information i = new Information();
            Information wanted = new Information();
            TransmetteurAnalogiqueBruitReel testTABR = new TransmetteurAnalogiqueBruitReel(1000000000, parametres);
            i.add(new ArrayList(Collections.nCopies(2 * x, 5f)));
            i.add(new ArrayList(Collections.nCopies(2 * x, 0f)));
            try {
                testTABR.recevoir(i);
                testTABR.emettre();
            } catch (InformationNonConforme e) {
                System.err.println(e);
            }
            wanted.add(new ArrayList(Collections.nCopies(2 * x, 5f)));
            wanted.add(new ArrayList(Collections.nCopies(2 * x, 0f)));
            assertEquals(wanted, testTABR.informationEmise);
        }
    }

    @Test
    public void testTABRMono() {
        //Verifie qu'un signal basique est bien dephase de x ech
        for (int x = 1; x != 30; x++) {
            ArrayList<Float> parametres = new ArrayList<>();
            Information i = new Information();
            Information wanted = new Information();
            parametres.add((float) x);
            parametres.add(1f);
            TransmetteurAnalogiqueBruitReel testTABR = new TransmetteurAnalogiqueBruitReel(1000000000, parametres);
            i.add(new ArrayList(Collections.nCopies(2 * x, 5f)));
            i.add(new ArrayList(Collections.nCopies(2 * x, 0f)));
            try {
                testTABR.recevoir(i);
                testTABR.emettre();
            } catch (InformationNonConforme e) {
            }
            wanted.add(new ArrayList(Collections.nCopies(x, 5f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 10f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 5f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 0f)));
            assertEquals(wanted, testTABR.informationEmise);
        }
    }

    @Test
    public void testTABRMulti() {
        //Verifie que 5 signaux basique sont bien dephase de x ech
        for (int x = 1; x != 30; x++) {
            ArrayList<Float> parametres = new ArrayList<>();
            Information i = new Information();
            Information wanted = new Information();
            parametres.add((float) x);
            parametres.add(1f);
            parametres.add((float) 2 * x);
            parametres.add(1f);
            parametres.add((float) 3 * x);
            parametres.add(1f);
            parametres.add((float) 4 * x);
            parametres.add(1f);
            parametres.add((float) 5 * x);
            parametres.add(1f);
            TransmetteurAnalogiqueBruitReel testTABR = new TransmetteurAnalogiqueBruitReel(1000000000, parametres);
            i.add(new ArrayList(Collections.nCopies(5 * x, 5f)));
            i.add(new ArrayList(Collections.nCopies(6 * x, 0f)));
            try {
                testTABR.recevoir(i);
                testTABR.emettre();
            } catch (InformationNonConforme e) {
            }
            wanted.add(new ArrayList(Collections.nCopies(x, 5f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 10f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 15f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 20f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 25f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 25f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 20f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 15f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 10f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 5f)));
            wanted.add(new ArrayList(Collections.nCopies(x, 0f)));
            assertEquals(wanted, testTABR.informationEmise);
        }
    }

    @Test
    public void testTABR() throws InformationNonConforme {
        float snr = -10f; // Rapport snr bas pour declencher une erreur
        boolean error = false;
        for (int i = 0; i < 5; i++) {
            if (testTABRsnr(snr * i) == false) { // Detection d'une erreur
                error = true;
            }
        }
        if (error) assert true; // Si il y a une erreur alors il y a bien du bruit dans le signal
        else assert false; // Si aucune erreur est detecte le generateur de bruit ne fct pas
    }

    private boolean testTABRsnr(float snr) throws InformationNonConforme {
        SourceFixe sf = new SourceFixe("11001");
        Emetteur cna = new Emetteur();
        Recepteur can = new Recepteur();
        ArrayList<Float> parametres = new ArrayList<>();
        TransmetteurAnalogiqueBruitReel testTp = new TransmetteurAnalogiqueBruitReel(snr,parametres);
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