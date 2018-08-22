package comp1110.ass2;

// this class represent
public class Pieces {

    char piecesID;         // type of the piece, denoted by letters a,b, ... h
    int orientation;       // the orientation of the piece, denoted by int 0, 1, ... 7
    int columnLocation;    // the column the left of the piece is in, denoted by int 1, 2, ... 8
    char rowLocation;      // the row the top of the piece is in, denoted by A, B, C, or D
    Colour color;

    public Pieces (String placement){  // constructor for a new piece

    }



    // find positions of all nodes connected by this piece

    String[] connectedNodePosition (){
        // ...
        return null;
    }

    // find positions of all holes of the current piece

    String[] holePositions () {
        // ...
        return null;
    }

}
