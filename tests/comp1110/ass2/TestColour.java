package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import org.junit.rules.Timeout;

//Author: Mei Yee Chin
public class TestColour {

    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);
    String p1 = new String("a4C2");
    Colour c1 = Piece.colourDecode(p1.charAt(0));

    String p2 = new String("b2B1");
    Colour c2 = Piece.colourDecode(p2.charAt(0));

    String p3 = new String("c1A2");
    Colour c3 = Piece.colourDecode(p3.charAt(0));

    String p4 = new String("d6C4");
    Colour c4 = Piece.colourDecode(p4.charAt(0));

    String p5 = new String("e1A6");
    Colour c5 = Piece.colourDecode(p5.charAt(0));

    String p6 = new String ("f3D3");
    Colour c6 = Piece.colourDecode(p6.charAt(0));

    String p7 = new String("g4D4");
    Colour c7 = Piece.colourDecode(p7.charAt(0));

    String p8 = new String("g2B1");
    Colour c8 = Piece.colourDecode(p8.charAt(0));

    @Test
    public void TestColour() {
        assertTrue(c1.equals(Colour.RED));
        assertTrue(c2.equals(Colour.RED));
        assertTrue(c3.equals(Colour.BLUE));
        assertTrue(c4.equals(Colour.BLUE));
        assertTrue(c5.equals(Colour.GREEN));
        assertTrue(c6.equals(Colour.GREEN));
        assertTrue(c7.equals(Colour.YELLOW));
        assertTrue(c8.equals(Colour.YELLOW));
    }

}
