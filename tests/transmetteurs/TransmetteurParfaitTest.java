package transmetteurs;

import destinations.DestinationFinale;
import information.Information;
import information.InformationNonConforme;
import org.junit.Test;
import sources.SourceFixe;

import static org.junit.Assert.assertEquals;

public class TransmetteurParfaitTest {

    @Test
    public void testTp() throws InformationNonConforme {
        SourceFixe sf = new SourceFixe("11001");
        TransmetteurParfait testTp = new TransmetteurParfait();
        DestinationFinale df = new DestinationFinale();
        Information bools = new Information();
        bools.add(true);
        bools.add(true);
        bools.add(false);
        bools.add(false);
        bools.add(true);
        sf.connecter(testTp);
        testTp.connecter(df);
        sf.emettre();
        assertEquals(bools, df.getInformationRecue());
    }
}