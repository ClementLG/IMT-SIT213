package transmetteurs;

import destinations.DestinationFinale;
import information.Information;
import information.InformationNonConforme;
import org.junit.Test;
import sources.SourceFixe;

public class TransmetteurAnalogiqueBruiteTest {

    @Test
    public void testTAB() throws InformationNonConforme {
        float x = -10f;
        boolean b = false;
        for (int i = 0; i < 5; i++) {
            if (testTABsnr(x * i) == false) {
                b = true;
            }
        }
        if (b) assert true;
        else assert false;
    }

    public boolean testTABsnr(float snr) throws InformationNonConforme {
        SourceFixe sf = new SourceFixe("11001");
        Emetteur cna = new Emetteur();
        Recepteur can = new Recepteur();
        TransmetteurAnalogiqueBruite testTp = new TransmetteurAnalogiqueBruite(snr);
        DestinationFinale df = new DestinationFinale();
        Information bools = new Information();
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
        return (bools.equals(df.getInformationRecue()));

    }
}