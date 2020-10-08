package sources;

import destinations.DestinationFinale;
import information.Information;
import information.InformationNonConforme;
import org.junit.Test;
import static org.junit.Assert.*;

public class SourceFixeTest {
    @Test
    public final void sFixeGen() {

        SourceFixe st1, st2, st3, st4;

        Information test = new Information();
        test.add(true);
        test.add(true);
        st1 = new SourceFixe("11");
        assertEquals(test, st1.informationGeneree);
        test.setIemeElement(0, false);
        test.setIemeElement(1, false);
        st2 = new SourceFixe("00");
        assertEquals(test, st2.informationGeneree);
        st3 = new SourceFixe("10");
        test.setIemeElement(0, true);
        assertEquals(test, st3.informationGeneree);
        st4 = new SourceFixe("01");
        test.setIemeElement(0, false);
        test.setIemeElement(1, true);
        assertEquals(test, st4.informationGeneree);
    }

    @Test
    public final void sConnect() throws InformationNonConforme {
        SourceFixe st1 = new SourceFixe("11");
        Information test = new Information();
        DestinationFinale dft1, dft2;
        dft1 = new DestinationFinale();
        dft2 = new DestinationFinale();
        test.add(true);
        test.add(true);
        st1.connecter(dft1);
        st1.connecter(dft2);
        st1.deconnecter(dft1);
        st1.emettre();
        assertEquals(test, st1.getInformationEmise());
        assertEquals(null, dft1.getInformationRecue());
        assertEquals(test, dft2.getInformationRecue());

    }

}