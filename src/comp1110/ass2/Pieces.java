package comp1110.ass2;

import java.util.ArrayList;

/** This class represents each piece from the game. It contains the characteristics
 *  of the pieces, and some relevant implementations.
 *
 *
 *  Representation of the Squareboard:
 *  The Squareboard is a smaller board that each piece can fit into. For every piece type other than "c"
 *  the Squareboard is set to be of size 3-by-3. For piece of type "c" the Squareboard is set as 4-by-4,
 *  For piece of type "e" the Squareboard is set as 2-by-2
 *  0 1 2            0   1   2   3             0  1
 *  3 4 5     or     4   5   6   7      or     2  3
 *  6 7 8            8   9  10  11
 *                  12  13  14  15
 *  After each piece is settled in the Squareboard. We use the location of the top left corner of the Squareboard
 *  (keyPosition) to locate the piece position on the game board.
 *
 *
 *  Representation of each piece on the its Squareboard:
 *  The Squareboard contains int value 0,1 or 2.
 *  0 -> the location is empty;
 *  1 -> the location is occupied by the piece and it's filled
 *  2 -> the location is occupied by the piece and it's a hole.
 *  the n-by-n array will then be converted to a 1-dimensional array by the order of left to right of each row, followed by
 *  the same of the next row.
 *  In this way, piece "a" with orientation "0" can be represented as:
 *  2 1 2
 *  0 0 1      ->      { 2,1,2,0,0,1,0,0,0 }
 *  0 0 0
 *
 *
*/

// ?????????  might create another enum named pieceType that stores Squareboard placement, given pieceID and orientation ?????????


public class Pieces {

    private char pieceID;                // type of the piece, denoted by letters a,b, ... h
    private Direction orientation;       // the orientation of the piece, denoted by int 0, 1, ... 7
    private int keyPosition;             // the characteristic location of the peg's Squareboard (the top left corner position ID of the Squareboard)
    private int[] squareBoard;           // Squareboard representation of the piece
    private Colour colour;               // colour of this piece

    // constructor for a new piece
    public Pieces (String placement){

        this.pieceID = placement.charAt(0);
        this.orientation = orientationDecode(placement.charAt(3));
        this.keyPosition = posDecode(placement.charAt(1), placement.charAt(2));
        this.squareBoard = squareboardDecode(placement.charAt(0), placement.charAt(3));
        this.colour = colourDecode(placement.charAt(0));

    }

    // given a piece placement string, decode piece direction
    public static Direction orientationDecode (char dir){
        switch (dir) {
            case '0': return Direction.NORTH;
            case '1': return Direction.EAST;
            case '2': return Direction.SOUTH;
            case '3': return Direction.WEST;
            case '4': return Direction.FNORTH;
            case '5': return Direction.FEAST;
            case '6': return Direction.FSOUTH;
            case '7': return Direction.FWEST;
            default: return null;
        }
    }


    // given a piece placement string, decode piece key position (top left corner position of the squareboard )
    public static int posDecode (char col, char row){
        return (row - 'A')*8 + (int) col - 1;
    }


    // given a piece placement string, decode piece squareboard representation as a 1-dimensional array.
    public static int[] squareboardDecode (char pieceID, char dir){
        switch (pieceID) {
            case 'a':
                switch (dir) {
                    case '0':
                        return new int[]{2, 1, 2, 0, 0, 1, 0, 0, 0};
                    case '1':
                        return new int[]{0, 2, 0, 0, 1, 0, 1, 2, 0};
                    // ......
                }

            default: return null;
        }
    }


    public static Colour colourDecode (char pieceID){
        switch (pieceID) {
            case 'a': case 'b':
                return Colour.RED;
            case 'c': case 'd':
                return Colour.BLUE;
            case 'e': case 'f':
                return Colour.GREEN;
            case 'g': case 'h':
                return Colour.YELLOW;
        }
    }








    // method updating the fields of the piece when it has been rotated
    void rotatePiece (int orientation){
        // ...
    }

    // method updating the fields of the piece when it has been moved
    void movePiece (int keyPosition){
        //...
    }







}
