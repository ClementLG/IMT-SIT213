package transmetteurs;

import destinations.DestinationFinale;
import information.Information;
import information.InformationNonConforme;
import org.junit.Test;


import static org.junit.Assert.*;

public class RecepteurTest {
    @Test
    public void testRecepteur() {
        DestinationFinale df=new DestinationFinale();
        Recepteur tRecep = new Recepteur(5, -5, 10, "RZ");
        tRecep.connecter(df);
        assertTrue(tRecep.destinationsConnectees.contains(df));
        tRecep.deconnecter(df);
        assertFalse(tRecep.destinationsConnectees.contains(df));
    }
    @Test
    public void recevoir() throws InformationNonConforme {
        TransmetteurAnalogiqueParfait tf = new TransmetteurAnalogiqueParfait();
        Information flo = new Information();
        Recepteur testR = new Recepteur(5, -5, 6, "RZ");
        tf.connecter(testR);
        flo.add(0f);
        flo.add(3f);
        flo.add(0f);
        flo.add(0f);
        flo.add(3f);
        flo.add(3f);

        tf.recevoir(flo);
        tf.emettre();
        assertEquals(flo, testR.informationRecue);
    }

    @Test
    public void emettre() throws InformationNonConforme {
        DestinationFinale df = new DestinationFinale();
        Recepteur testR = new Recepteur(3, -3, 3, "NRZ");
        Information flo = new Information();
        Information bool = new Information();
        bool.add(true);
        bool.add(false);

        flo.add(3f);
        flo.add(3f);
        flo.add(3f);
        flo.add(-3f);
        flo.add(-3f);
        flo.add(-3f);
        testR.connecter(df);
        testR.recevoir(flo);
        testR.emettre();
        assertEquals(bool, df.getInformationRecue());
    }
}