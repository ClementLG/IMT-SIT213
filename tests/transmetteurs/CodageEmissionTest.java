package transmetteurs;

import destinations.DestinationFinale;
import information.Information;
import information.InformationNonConforme;
import org.junit.Test;
import sources.SourceFixe;

import static org.junit.Assert.*;

public class CodageEmissionTest {

    @Test
    public void testCodage() throws InformationNonConforme {
        SourceFixe sf = new SourceFixe("01");
        CodageEmission crTest = new CodageEmission();
        DestinationFinale df = new DestinationFinale();
        sf.connecter(crTest);
        crTest.connecter(df);
        sf.emettre();
        Information<Boolean> bools = new Information();
        bools.add(false);
        bools.add(true);
        bools.add(false);
        bools.add(true);
        bools.add(false);
        bools.add(true);
        assertEquals(bools, crTest.informationEmise);
    }
}