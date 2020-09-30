import destinations.DestinationFinale;
import org.junit.Test;
import sources.SourceAleatoire;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SimulateurTest {

	Simulateur simulateur_test = null;
	String arguments = "String arguments;\r\n"
			+ "            arguments=\"-seed 40 -form NRZT -ampl -5 5 -snrpb -20 -export ./test.txt\";\r\n"
			+ "            args= arguments.split(\" \");\r\n"
			+ "            Simulateur.main(args);";

    @Test
    public void analyseArguments() throws ArgumentsException {

        String[] null_args = {};
        simulateur_test = new Simulateur(null_args);
        float delta = 0;
        assertEquals(false, simulateur_test.getAffichage());
        assertEquals(true, simulateur_test.getMessageAleatoire());
        assertEquals(false, simulateur_test.getAleatoireAvecGerme());
        assertEquals(null, simulateur_test.getSeed());
        assertEquals(100, simulateur_test.getNbBitsMess());
        assertEquals("100", simulateur_test.getMessageString());
        assertEquals("RZ", simulateur_test.getForm());
        assertEquals(10000000f, simulateur_test.getSnr(), delta);
        assertEquals(30, simulateur_test.getNe());
        assertEquals(0f, simulateur_test.getMin(), delta);
        assertEquals(1f, simulateur_test.getMax(), delta);

        simulateur_test = null;

    	String[] args = {"-s"};
    	simulateur_test = new Simulateur(args);
    	assertEquals(true,simulateur_test.getAffichage());

    	simulateur_test = null;

    	String[] args_text = {"-mess","ersefse"};
        try {
            simulateur_test = new Simulateur(args_text);
            assertTrue(simulateur_test.getMessageAleatoire());
            //assertEquals(100, simulateur_test.getNbBitsMess());
            assertEquals("100", simulateur_test.getMessageString());
        } catch (Exception e) {
            assertEquals(ArgumentsException.class, e.getClass());
        }

        String[] args1 = {"-mess", "0"};
        try {
            simulateur_test = new Simulateur(args1);
            assertTrue(simulateur_test.getMessageAleatoire());
            //assertEquals(100, simulateur_test.getNbBitsMess());
            assertEquals("100", simulateur_test.getMessageString());
        } catch (Exception e) {
            assertEquals(ArgumentsException.class, e.getClass());
        }

        simulateur_test = null;

        String[] args2 = {"-mess", "10"};
        simulateur_test = new Simulateur(args2);
        assertTrue(simulateur_test.getMessageAleatoire());
        assertEquals(10, simulateur_test.getNbBitsMess());
        assertEquals("10", simulateur_test.getMessageString());

        simulateur_test = null;

        String[] args3 = {"-mess", "123456"};
        simulateur_test = new Simulateur(args3);
        assertTrue(simulateur_test.getMessageAleatoire());
        assertEquals(123456, simulateur_test.getNbBitsMess());
        assertEquals("123456", simulateur_test.getMessageString());

        simulateur_test = null;

        String[] args4 = {"-mess","1234567"};
        try {
            simulateur_test = new Simulateur(args4);
            assertTrue(simulateur_test.getMessageAleatoire());
            //assertEquals(100, simulateur_test.getNbBitsMess());
            assertEquals("100", simulateur_test.getMessageString());
        } catch (Exception e) {
            assertEquals(ArgumentsException.class, e.getClass());
        }

        simulateur_test = null;

        String[] args5 = {"-mess", "101001011010"};
        simulateur_test = new Simulateur(args5);
        assertEquals(false, simulateur_test.getMessageAleatoire());
        assertEquals(12, simulateur_test.getNbBitsMess());
        assertEquals("101001011010", simulateur_test.getMessageString());

        simulateur_test = null;

        String[] args6 = {"-seed", "784511"};
        simulateur_test = new Simulateur(args6);
        assertEquals(true, simulateur_test.getMessageAleatoire());
        assertEquals(true, simulateur_test.getAleatoireAvecGerme());
        assertEquals(784511, simulateur_test.getSeed(), delta);

        simulateur_test = null;

        String[] args6_1 = {"-seed","sdgdrfd"};
        try {
            simulateur_test = new Simulateur(args6_1);
            assertTrue(simulateur_test.getMessageAleatoire());
            assertTrue(simulateur_test.getAleatoireAvecGerme());
            assertEquals(784511, simulateur_test.getSeed(), delta);
        } catch (Exception e) {
            assertEquals(ArgumentsException.class, e.getClass());
        }

        simulateur_test = null;

        String[] args7 = {"-form","AEZFESFES"};
        try {
        	simulateur_test = new Simulateur(args7);
        	assertEquals("RZ", simulateur_test.getForm());
        } catch (Exception e) {
			assertEquals(ArgumentsException.class, e.getClass());
		}

        simulateur_test = null;

        String[] args7_1 = {"-form","52"};
        try {
        	simulateur_test = new Simulateur(args7_1);
        	assertEquals("RZ", simulateur_test.getForm());
        } catch (Exception e) {
			assertEquals(ArgumentsException.class, e.getClass());
		}

        simulateur_test = null;

        String[] args8 = {"-form","RZ"};
        try {
        	simulateur_test = new Simulateur(args8);
        	assertEquals("RZ", simulateur_test.getForm());
        } catch (Exception e) {
			assertEquals(ArgumentsException.class, e.getClass());
		}

        simulateur_test = null;

        String[] args9 = {"-form","NRZ"};
        try {
        	simulateur_test = new Simulateur(args9);
        	assertEquals("NRZ", simulateur_test.getForm());
        } catch (Exception e) {
			assertEquals(ArgumentsException.class, e.getClass());
		}

        simulateur_test = null;

        String[] args10 = {"-form","NRZT"};
        try {
        	simulateur_test = new Simulateur(args10);
        	assertEquals("NRZT", simulateur_test.getForm());
        } catch (Exception e) {
			assertEquals(ArgumentsException.class, e.getClass());
		}

        simulateur_test = null;

        String[] args11 = {"-nbEch","0"};
        try {
        	simulateur_test = new Simulateur(args11);
        	assertEquals(30, simulateur_test.getNe());
        } catch (Exception e) {
			assertEquals(ArgumentsException.class, e.getClass());
		}

        simulateur_test = null;

        String[] args12 = {"-nbEch","-8"};
        try {
        	simulateur_test = new Simulateur(args12);
        	assertEquals(30, simulateur_test.getNe());
        } catch (Exception e) {
			assertEquals(ArgumentsException.class, e.getClass());
		}

        simulateur_test = null;

        String[] args13 = {"-nbEch","50"};
        simulateur_test = new Simulateur(args13);
        assertEquals(50-50%3, simulateur_test.getNe());

        simulateur_test = null;

        String[] args14 = {"-nbEch","63"};
        simulateur_test = new Simulateur(args14);
        assertEquals(63, simulateur_test.getNe());
    }

    @Test
    public void testSimulateur()  {
        Simulateur simulateurTest = null;
        String[] args = {"-mess", "-a"};
        try {
            simulateurTest = new Simulateur(args);
            assert false;
        } catch (Exception e) {
            assertEquals(ArgumentsException.class, e.getClass());
        }
        try {
            simulateurTest.execute();
            assert false;
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());

        }
        args = new String[]{"-mess", "10"};
        try {
            simulateurTest = new Simulateur(args);
            //assertEquals(SourceAleatoire.class,simulateurTest.getSource().getClass());
            //assertEquals(DestinationFinale.class,simulateurTest.getDestination().getClass());
        } catch (Exception e) {
            assert false;
        }
        try {
            simulateurTest.execute();

        } catch (Exception e) {
            assert false;

        }
        args = new String[]{"-mess", "10"};
        try {
            simulateurTest = new Simulateur(args);
            //assertEquals(SourceAleatoire.class,simulateurTest.getSource().getClass());
            //assertEquals(DestinationFinale.class,simulateurTest.getDestination().getClass());
        } catch (Exception e) {
            assert false;
        }

    }

    @Test
    public void calculTauxErreurBinaire() {
    }

}