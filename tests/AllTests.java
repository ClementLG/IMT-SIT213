import information.InformationTest;
import sources.*;
import transmetteurs.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
<<<<<<< HEAD
@SuiteClasses({ InformationTest.class, SourceAleatoireTest.class,SourceFixeTest.class,EmetteurTest.class,RecepteurTest.class,TransmetteurAnalogiqueBruiteTest.class,TransmetteurAnalogiqueParfaitTest.class,TransmetteurParfaitTest.class,SimulateurTest.class })
=======
@SuiteClasses({InformationTest.class, SourceAleatoireTest.class, SourceFixeTest.class,CodageEmissionTest.class,DecodageReceptionTest.class, EmetteurTest.class, RecepteurTest.class, TransmetteurAnalogiqueBruiteTest.class,TransmetteurAnalogiqueBruitReelTest.class, TransmetteurAnalogiqueParfaitTest.class, TransmetteurParfaitTest.class, SimulateurTest.class})
>>>>>>> clg
public class AllTests {

}