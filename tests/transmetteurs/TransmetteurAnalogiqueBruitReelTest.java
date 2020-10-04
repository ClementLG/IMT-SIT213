package transmetteurs;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TransmetteurAnalogiqueBruitReelTest {

    @Test
    public void recevoir() {
        ArrayList<Float> parametres = new ArrayList<>();
        parametres.add(30f);
        parametres.add(1f);
        TransmetteurAnalogiqueBruitReel testTABR = new TransmetteurAnalogiqueBruitReel(9999,parametres);
    }
}