package information;

import org.junit.Test;

import javax.print.DocFlavor;

import static org.junit.Assert.*;

public class InformationTest {

    Information test1 = new Information();

    @Test
    public void add() {
        test1.add("TEST");
        test1.add("DE");
        test1.add("INFORMATION");

    }

    @Test
    public void nbElements() {
        add();
        assertEquals(3, test1.nbElements());
    }

    @Test
    public void iemeElement() {
        add();
        assertEquals("TEST", test1.iemeElement(0));
    }

    @Test
    public void setIemeElement() {
        add();
        test1.setIemeElement(1, "REUSSI");
        assertEquals("REUSSI", test1.iemeElement(1));
    }

    @Test
    public void testEquals() {
        add();
        assertTrue(test1.equals(test1));
        test1.add(true);
        assertSame( test1.iemeElement(3).getClass().getName(),"java.lang.Boolean");
    }

    @Test
    public void testToString() {
        add();
        assertEquals(" TEST DE INFORMATION", test1.toString());
    }

    @Test
    public void testIterator() {
        add();
        int cpt = 0;
        for (Object x : test1) {
            cpt++;
            assertSame( x.getClass().getName(),"java.lang.String");
        }
        assertEquals(3, cpt);
    }
}