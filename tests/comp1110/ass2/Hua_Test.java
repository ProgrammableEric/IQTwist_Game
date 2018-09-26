package comp1110.ass2;

import comp1110.ass2.Direction;
import comp1110.ass2.Piece;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class Hua_Test {
    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);
    Piece piece1 = new Piece("a1A6");
    Piece piece2 = new Piece("c2A4");
    Piece piece3 = new Piece("c3B1");
    Piece piece4 = new Piece("g7B7");
    Piece piece5 = new Piece("d5B5");


    @Test
    public void testDirection() {
        assertTrue(piece1.orientation.equals(Direction.FSOUTH));
        assertTrue(piece2.orientation.equals(Direction.FNORTH));
        assertTrue(piece3.orientation.equals(Direction.EAST));
        assertTrue(piece4.orientation.equals(Direction.FWEST));
        assertTrue(piece5.orientation.equals(Direction.FEAST));


    }

    @Test
    public void testIsOnboard() {
        assertTrue(piece3.isOnBoard() == false);
        assertTrue(piece2.isOnBoard() == true);
        assertTrue(piece4.isOnBoard() == false);
        assertTrue(piece1.isOnBoard() == true);
        assertTrue(piece5.isOnBoard() == true);


    }

}
