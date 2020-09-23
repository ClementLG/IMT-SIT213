import information.InformationTest;
import sources.*;
import transmetteurs.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ InformationTest.class, SourceAleatoireTest.class,SourceFixeTest.class,EmetteurTest.class,RecepteurTest.class,TransmetteurAnalogiqueBruiteTest.class,TransmetteurAnalogiqueParfaitTest.class,TransmetteurParfaitTest.class,SimulateurTest.class })
public class AllTests {

}