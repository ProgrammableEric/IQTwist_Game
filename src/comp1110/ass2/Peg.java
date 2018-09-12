package comp1110.ass2;

public class Peg {

    char pegID;           // used to uniquely identify each peg, denoted as P1,P2 ... P7, each with its own colour
    int position;          // position of the peg, denoted by integer 0,1,... 31. position = -1
                           // if it's not placed on the game board
    Colour colour;         // colour of the peg


    // constructor for a new peg
    Peg (String placement){

        this.pegID = placement.charAt(0);
        this.position = posDecode(placement.charAt(1), placement.charAt(2));
        this.colour = colourDecode(placement.charAt(0));

        // ...
    }

    // given a piece placement string, decode piece key position (top left corner position of the squareboard )
    public static int posDecode (char col, char row){
        return (row - 'A')*8 + (int) col - 1;
    }



    public static Colour colourDecode (char pieceID) {
        switch (pieceID) {
            case 'i':
                return Colour.RED;
            case 'j':
                return Colour.BLUE;
            case 'k':
                return Colour.GREEN;
            case 'l':
                return Colour.YELLOW;

            default: return null;
        }

    }

}
