package comp1110.ass2;

/**
 * This class represents a game of 'TwitGame', which is based directly on Smart Games' IQ-Twist game
 * (http://www.smartgames.eu/en/smartgames/iq-twist)
 *
 * The game uses the following encodings to represent game state.
 * Author: Hua Guo
 */




public class TwitGame1 {

    Node[] nodes;


    /* The challenges represents the problem to be solved in this instance of the game. */
    private Challenges challenges;


    /**
     * Construct a game with a given difficulty, and initialize the node graph.
     *
     * @param challenge The challenge of this game.
     * Author: Hua Guo
     */
    TwitGame1(Challenges challenges) {
        this.challenges = challenges;
        initializeNodeGraph();
    }

    /**
     * Construct a game for a given level of difficulty.   This creates a new instance
     * of the game at the given level of difficulty.   It chooses a new challenge and then
     * initializes the node graph.
     *
     * @param difficulty The difficulty of the game.
     */
    public TwitGame1(int difficulty) {
        this(Challenges.newChallenge(difficulty));
    }


    /**
     * @return the statement for this challenge
     */
    public String getPlacement() {
        return this.challenges.getStatement();
    }


/**
 * Initialize the node graph according to the initial challenge placement
 * This is only called once, at the beginning of the program, from the
 * constructor.
 * Author: Hua Guo
 */
    private void initializeNodeGraph() {
        nodes = new Node[32];
        String initialStatement = challenges.getStatement();
        for (int i =0; i < nodes.length; i++){
            nodes[i] = new Node(0, null, null);
        }

        for (int j = 0; j < initialStatement.length() / 4; j++) {

            String temp = initialStatement.substring(j * 4, (j + 1) * 4);
            //System.out.println(temp);

            if (TwistGame.isPiece(temp)) {
                Piece newPiece = new Piece(temp);
                //int keyPos = newPiece.keyPosition;
                //int[] positions = newPiece.positions;
                //int[] pieceValues = newPiece.pieceValues;
                for (int i = 0; i < newPiece.positions.length; i++) {
                    int id = newPiece.keyPosition + newPiece.positions[i];
                    nodes[id].piece = newPiece;
                    nodes[id].pieceValue = newPiece.pieceValues[i];
                }
            } else if (TwistGame.isPeg(temp)) {
                Peg newPeg = new Peg(temp);
                int id2 = newPeg.position;
                nodes[id2].peg = newPeg;
            }
        }


    }

}
