package comp1110.ass2;

public class Pegs {

    pegType peg;           // used to uniquely identify each peg, denoted as P1,P2 ... P7, each with its own colour
    int position;          // position of the peg, denoted by integer 0,1,... 31. position = -1
                           // if it's not placed on the game board

    Colour colour;         // colour of the peg


    // constructor for a new peg
    Pegs (pegType peg, int position, Colour colour){

        this.peg = peg;
        this.position = position;
        this.colour = colour;

        // ...
    }










}
