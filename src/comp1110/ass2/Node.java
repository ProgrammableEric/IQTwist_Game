package comp1110.ass2;

import java.util.ArrayList;

/**
 * This class represent a node (a circular depression location on the board)
 * and the pegs or pieces associated.
 * For reducing computation load, the game board will be encoded
 * only by number indices/position ID as follows:
 *
 *  0  1  2  3  4  5  6  7
 *  8  9 10 11 12 13 14 15
 * 16 17 18 19 20 21 22 23
 * 24 25 26 27 28 29 30 31
 *
 * The position ID associated with each piece/peg will need be converted to the above format.
 *
 * Finished state is determined by :
 * 1. all nodes are occupied with one and only piece;
 * 2. all pegs are placed in the right place, of the color-matched piece
*/


public class Node {

    int value;             // value (0,1,2 according to Class Pieces) associated with the node,
    int PositionID;        // Location of each note.
    Pieces[] pieces;       // the piece associated with this board location, if any.
    Pegs pegs;             // the peg associated with this board location, if any.



    // constructor of a node
    Node (int PositionID, Pieces[] pieces, Pegs pegs) {
        this.PositionID = PositionID;
        this.pieces = pieces;
        this.pegs = pegs;
        // ...
    }


    // Decoding placement string into position ID.
    public static int getPositionID (String placementString){
        // ...
        return -1 ;
    }


    // method determine if there's either a peg or piece occupying this node
    boolean isEmpty (){
        // ...
        return true;
    }


    // method tells if a piece is placed on the node
    boolean pieceHere (){
        //...
        return false;
    }


    // method tells if a peg is placed on the node
    boolean pegHere (){
        //...
        return false;
    }


    // update the board information when a new piece is placed or moved on the board
    void placePiece (Pieces pieces){
        //...
    }


    // update the board information when a new peg is placed or moved on the board
    void placePeg (Pegs pegs){
        //...
    }





    // get all positions of all neighbours that are on the game board
    int[] getNeighbours (int positionID){
        // ...
        return null;
    }






}
