package comp1110.ass2;

// This class represent a node (a circular depression location on the board) and the pegs or pieces associated.

// finished state is reflected by :
// 1. all nodes are occupied with one and only piece;
// 2. all pegs are placed in the right place, of the color-matched piece

public class Node {


    int column;            // column location of the node, denoted as 1, 2, ... 8
    char row;              // row location of the node, denoted as A, B, C, or D
    Pieces pieces;         // the piece associated with this board location, if any
    Pegs pegs;             // the peg associated with this board location, if any



    // constructor of a node
    Node () {
        // ...
    }


    // method determine if there's either a peg or piece occupying this node
    boolean isEmpty (){
        // ...
        return true;
    }














}
