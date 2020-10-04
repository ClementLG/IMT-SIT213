package transmetteurs;

import destinations.DestinationFinale;
import information.Information;
import information.InformationNonConforme;
import org.junit.Test;
import sources.SourceFixe;

import static org.junit.Assert.assertEquals;

public class TransmetteurAnalogiqueParfaitTest {

    @Test
    public void testTAP() throws InformationNonConforme {
        SourceFixe sf = new SourceFixe("11001");
        Emetteur cna = new Emetteur();
        Recepteur can = new Recepteur();
        TransmetteurAnalogiqueParfait testTp = new TransmetteurAnalogiqueParfait();
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
        assertEquals(bools, df.getInformationRecue());
    }
}