package transmetteurs;

import information.Information;
import information.InformationNonConforme;
import org.junit.Test;
import sources.SourceFixe;

import static org.junit.Assert.*;

public class EmetteurTest {
    @Test
    public void testEmetteur() {
        TransmetteurAnalogiqueParfait tap = new TransmetteurAnalogiqueParfait();
        Emetteur tEmet = new Emetteur(5, -5, 10, "RZ");
        tEmet.connecter(tap);
        assertTrue(tEmet.destinationsConnectees.contains(tap));
        tEmet.deconnecter(tap);
        assertFalse(tEmet.destinationsConnectees.contains(tap));
    }

    @Test
    public void testRecevoir() throws InformationNonConforme {
        SourceFixe sf = new SourceFixe("110");
        Information bool = new Information();
        Emetteur testE = new Emetteur(5, -5, 9, "RZ");
        bool.add(true);
        bool.add(true);
        bool.add(false);

        sf.connecter(testE);
        sf.emettre();
        assertEquals(bool, testE.informationRecue);
    }

    @Test
    public void testEmettre() throws InformationNonConforme {
        TransmetteurAnalogiqueParfait tap = new TransmetteurAnalogiqueParfait();
        Emetteur testE = new Emetteur(3, -3, 1, "NRZ");
        Information flo = new Information();
        Information bool = new Information();
        bool.add(true);
        bool.add(false);

        flo.add(3f);
        flo.add(-3f);

        testE.connecter(tap);
        testE.recevoir(bool);
        testE.emettre();
        assertEquals(flo, tap.informationRecue);

    }

    @Test(expected = InformationNonConforme.class)
    public void testCNA() throws InformationNonConforme {
        Emetteur testE1 = new Emetteur(3, -3, 3, "RZ");
        Emetteur testE2 = new Emetteur(3, -3, 3, "NRZ");
        Emetteur testE3 = new Emetteur(3, -3, 3, "NRZT");

        Information bool = new Information();
        bool.add(true);
        bool.add(true);
        bool.add(false);
        bool.add(false);
        bool.add(true);

        Information flo1 = new Information();
        flo1.add(0f);
        flo1.add(3f);
        flo1.add(0f);
        flo1.add(0f);
        flo1.add(3f);
        flo1.add(0f);
        flo1.add(0f);
        flo1.add(0f);
        flo1.add(0f);
        flo1.add(0f);
        flo1.add(0f);
        flo1.add(0f);
        flo1.add(0f);
        flo1.add(3f);
        flo1.add(0f);

        Information flo2 = new Information();
        flo2.add(3f);
        flo2.add(3f);
        flo2.add(3f);
        flo2.add(3f);
        flo2.add(3f);
        flo2.add(3f);
        flo2.add(-3f);
        flo2.add(-3f);
        flo2.add(-3f);
        flo2.add(-3f);
        flo2.add(-3f);
        flo2.add(-3f);
        flo2.add(3f);
        flo2.add(3f);
        flo2.add(3f);

        Information flo3 = new Information();
        flo3.add(0f);
        flo3.add(3f);
        flo3.add(3f);
        flo3.add(3f);
        flo3.add(3f);
        flo3.add(3f);
        flo3.add(-0f); // FIX WTF IS THAT
        flo3.add(-3f);
        flo3.add(-3f);
        flo3.add(-3f);
        flo3.add(-3f);
        flo3.add(-3f);
        flo3.add(0f);
        flo3.add(3f);
        flo3.add(3f);


        testE1.recevoir(bool);
        testE2.recevoir(bool);
        testE3.recevoir(bool);

        assertEquals(flo1, testE1.informationEmise);
        assertEquals(flo2, testE2.informationEmise);
        assertEquals(flo3, testE3.informationEmise);

        Emetteur testE = new Emetteur(3, -3, 3, "NRZTZDGDGETGZG");
        testE.recevoir(bool);
    }
}