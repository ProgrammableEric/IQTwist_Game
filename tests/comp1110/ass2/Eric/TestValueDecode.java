package comp1110.ass2.Eric;

import comp1110.ass2.Piece;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class TestValueDecode {

    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);


    private void test(char inID, char inOri, int[] expected) {
        int[] out = Piece.pieceValuesDecode(inID, inOri);
        assertTrue("Input was '"+inID + inOri +"', expected "+expected.toString()+" but got "+out.toString(), Arrays.equals(out,expected) );
    }


    @Test
    public void testNormal (){
        for (int i=0; i < PIECEPLACEMENT.length; i ++){
            char id = PIECEPLACEMENT[i].charAt(0);
            char ori = PIECEPLACEMENT[i].charAt(1);
            test(id, ori, NORMAL_EXPECTED[i]);
        }
    }


    @Test
    public void testFlipped (){
        for (int i=0; i < FLIPPED_PIECEPLACEMENT.length; i ++){
            char id = FLIPPED_PIECEPLACEMENT[i].charAt(0);
            char ori = FLIPPED_PIECEPLACEMENT[i].charAt(1);
            test(id, ori, FLIPPED_EXPECTED[i]);
        }
    }




    private static final String[] PIECEPLACEMENT = {
            "a1",
            "b2",
            "c0",
            "d2",
            "h3"

    };



    private static final int[][] NORMAL_EXPECTED = {
            {2, 1, 1, 2},
            {1, 2, 1, 1},
            {1, 2, 1, 1},
            {2, 2, 1, 1, 1},
            {1, 1, 2}
    };



    private static final String [] FLIPPED_PIECEPLACEMENT = {
            "a6",
            "b5",
            "c7",
            "d4",
            "h5"
    };


    private static final int[][] FLIPPED_EXPECTED = {
            {2, 1, 2, 1},
            {1, 1, 2, 1},
            {1, 1, 2, 1},
            {2, 2, 1, 1, 1},
            {2, 1, 1}
    };
}
