package comp1110.ass2;

import java.util.ArrayList;

/** This class represents each piece from the game. It contains the characteristics
 *  of the pieces, and some relevant implementations.
 *
 *
 *  Representation of the Squareboard:
 *  The Squareboard is a smaller board that each piece can fit into. For every piece type other than "c"
 *  the Squareboard is set to be of size 3-by-3. For piece of type "c" the Squareboard is set as 4-by-4
 *  0 1 2            0   1   2   3
 *  3 4 5     or     4   5   6   7
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
 *  In this way, piece "a" with orientation "0" can be represented as:
 *  2 1 2
 *  0 0 1
 *  0 0 0
 *
 *
*/

// ?????????  might create another enum named pieceType that stores Squareboard placement, given pieceID and orientation ?????????


public class Pieces {

    char piecesID;         // type of the piece, denoted by letters a,b, ... h
    int orientation;       // the orientation of the piece, denoted by int 0, 1, ... 7
    int keyPosition;       // the characteristic location of the peg's Squareboard (the top left corner position ID of the Squareboard)
    ArrayList<Integer>[][] Squareboard;       // Squareboard representation of the piece
    Colour colour;

    // constructor for a new piece
    public Pieces (char piecesID, int orientation, int keyPosition, ArrayList<Integer>[][] Squareboard){

        this.piecesID = piecesID;
        this.orientation = orientation;
        this.keyPosition = keyPosition;
        this.Squareboard = Squareboard;

        if (piecesID == 'c'){         // if piece type is "c", construct 4-by-4 Squareboard, otherwise 3-by-3
           // ...
        }else {};

        // ....
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
