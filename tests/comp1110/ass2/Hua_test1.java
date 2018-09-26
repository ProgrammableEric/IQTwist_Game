package comp1110.ass2;

import comp1110.ass2.Direction;
import comp1110.ass2.Piece;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.omg.CORBA.PUBLIC_MEMBER;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class Hua_test1 {
    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);
    String piece1 = new String("a1A6");
    Direction d1 = Piece.orientationDecode(piece1.charAt(3));

    String piece2 = new String("c2A4");
    Direction d2 = Piece.orientationDecode(piece2.charAt(3));

    String piece3 = new String("c3B1");
    Direction d3 = Piece.orientationDecode(piece3.charAt(3));

    String piece4 = new String("g7B7");
    Direction d4 = Piece.orientationDecode(piece4.charAt(3));

    String piece5 = new String("d5B5");
    Direction d5 = Piece.orientationDecode(piece5.charAt(3));

    Piece p1 = new Piece(piece1);
    Piece p2 = new Piece(piece2);
    Piece p3 = new Piece(piece3);
    Piece p4 = new Piece(piece4);
    Piece p5 = new Piece(piece5);


    @Test
    public void testDirection() {
        assertTrue(d1.equals(Direction.FSOUTH));
        assertTrue(d2.equals(Direction.FNORTH));
        assertTrue(d3.equals(Direction.EAST));
        assertTrue(d4.equals(Direction.FWEST));
        assertTrue(d5.equals(Direction.FEAST));


    }

    @Test
    public void testIsOnboard() {
        assertTrue(p3.isOnBoard() == false);
        assertTrue(p2.isOnBoard() == true);
        assertTrue(p4.isOnBoard() == false);
        assertTrue(p1.isOnBoard() == true);
        assertTrue(p5.isOnBoard() == true);

    }


}
