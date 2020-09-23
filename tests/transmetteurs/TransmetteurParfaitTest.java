package transmetteurs;

<<<<<<< HEAD
import destinations.DestinationFinale;
import information.Information;
import information.InformationNonConforme;
import org.junit.Test;
import sources.SourceFixe;
=======
import information.InformationNonConforme;
import org.junit.Test;
>>>>>>> master

import static org.junit.Assert.*;

public class TransmetteurParfaitTest {

    @Test
<<<<<<< HEAD
    public void testTp() throws InformationNonConforme {
        SourceFixe sf = new SourceFixe("11001");
        TransmetteurParfait testTp = new TransmetteurParfait();
        DestinationFinale df = new DestinationFinale();
        Information bools  = new Information();
        bools.add(true);
        bools.add(true);
        bools.add(false);
        bools.add(false);
        bools.add(true);
        sf.connecter(testTp);
        testTp.connecter(df);
        sf.emettre();
        assertEquals(bools,df.getInformationRecue());
=======
    public void recevoir() throws InformationNonConforme {
    }

    @Test
    public void emettre() throws InformationNonConforme {
>>>>>>> master
    }
}