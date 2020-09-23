import org.junit.Test;

import static org.junit.Assert.*;

public class SimulateurTest {

    @Test
    public void analyseArguments() {

    }

    @Test
    public void testSimulateur() throws ArgumentsException {
        String[] arg={"-mess","10"};
        Simulateur sTest=new Simulateur(arg);
        assertFalse(sTest.isAffichage());

    }

    @Test
    public void calculTauxErreurBinaire() {
    }

}