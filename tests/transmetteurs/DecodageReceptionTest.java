package transmetteurs;

import destinations.DestinationFinale;
import information.Information;
import information.InformationNonConforme;
import org.junit.Test;
import sources.SourceFixe;

import static org.junit.Assert.*;

public class DecodageReceptionTest {

    @Test
    public void testDecode() throws InformationNonConforme {
        SourceFixe sf = new SourceFixe("000010110011111101001100");
        DecodageReception drTest = new DecodageReception();
        DestinationFinale df = new DestinationFinale();
        sf.connecter(drTest);
        drTest.connecter(df);
        sf.emettre();
        Information<Boolean> bools = new Information();
        bools.add(false);
        bools.add(false);
        bools.add(false);
        bools.add(false);
        bools.add(true);
        bools.add(true);
        bools.add(true);
        bools.add(true);
        assertEquals(bools, drTest.informationEmise);

    }

}