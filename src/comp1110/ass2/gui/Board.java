package comp1110.ass2.gui;

import comp1110.ass2.Colour;
import comp1110.ass2.TwistGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.awt.*;

public class Board extends Application {
    /*board layout*/
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 60;
    private static final int MAIN_PANEL_WIDTH = 8 * SQUARE_SIZE;
    private static final int MAIN_PANEL_HEIGHT = 4 * SQUARE_SIZE;
    private static final int MARGIN_X = 40;
    private static final int MARGIN_Y = 80;
    private static final int MAIN_PANEL_OFFSET_X = BOARD_WIDTH - MARGIN_X - MAIN_PANEL_WIDTH;
    private static final int MAIN_PANEL_OFFSET_Y = BOARD_HEIGHT - MARGIN_Y - MAIN_PANEL_HEIGHT;

    //    private static final int MAIN_PANEL_X = MARGIN_X + MAIN_PANEL_HEIGHT;
    private static final int PIECE_SPACE = 20;


    /* where to find media assets*/
    private static final String URI_BASE = "assets/";
    //private static final String BASEBOARD_URI = Board.class.getResource(URI_BASE + "baseboard.png").toString();


    /* make for unplaced piece*/
    public static final char NOT_PLACED = 255;


    /* node groups */
    private final Group root = new Group();
    private final Group gameBoard = new Group();
    private final Group controls = new Group();
    private final Group pieces = new Group();
    private final Group pegs = new Group();
    private final Group board = new Group();

    /* message on success*/
    private final Text completionText = new Text("Well done!");

    /* the state of the pieces */
    char[] pieceState = new char[8];        // state of each piece, if not on the board, NOT_PLACED, if on the board, it stores the
                                            // key pos index of the piece, which is an integer from 0 - 31.
    /* the orientation of the pieces */
    char[] pieceOrientation = new char[8];  //  denoted by integer 0 - 7

    /* the IQ-TWIST game*/
    TwistGame twistGame;

    /* Define a drop shadow effect that we will apply to tiles */
    private static DropShadow dropShadow;

    /* Static initializer to initialize dropShadow */ {
        dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0);
        dropShadow.setOffsetY(2.0);
        dropShadow.setColor(Color.color(0, 0, 0, .4));
    }


    /**
     * an inner class that represents pieces in the game
     * visual presentation of the piece
     */

    class Piece extends ImageView {
        int piece;

        /**
         * constructor for a playing piece
         *
         * @param piece the letter representing the piece to be created.
         */
        Piece(char piece) {

            if (piece >= 'i') {
                throw new IllegalArgumentException("Bad piece: \"" + piece + "\"");
            }
            setImage(new Image(Viewer.class.getResource(URI_BASE + piece + ".png").toString()));
            this.piece = piece - 'a';

            switch (piece) {
                case 'a':
                case 'b':
                case 'd':
                case 'f':
                    setFitHeight(2 * SQUARE_SIZE);
                    setFitWidth(3 * SQUARE_SIZE);
                    setEffect(dropShadow);
                    break;
                case 'c':
                    setFitHeight(SQUARE_SIZE);
                    setFitWidth(4 * SQUARE_SIZE);
                    setEffect(dropShadow);
                    break;
                case 'e':
                    setFitHeight(2 * SQUARE_SIZE);
                    setFitWidth(2 * SQUARE_SIZE);
                    setEffect(dropShadow);
                    break;
                case 'g':
                    setFitHeight(3 * SQUARE_SIZE);
                    setFitWidth(3 * SQUARE_SIZE);
                    setEffect(dropShadow);
                    break;
                case 'h':
                    setFitHeight(SQUARE_SIZE);
                    setFitWidth(3 * SQUARE_SIZE);
                    setEffect(dropShadow);
                    break;
            }

            // ???????????????? rotation of the image ?????????????????
//            if ( orientation<='3'){
//                setRotate((orientation - '0')*90);
//            }else if ( orientation<='7'){
//                setScaleY(-1);
//                setRotate((orientation - '4')*90);
//            }

            // put each piece in place by offsetting
//            posX = offsetX(column);
//            posY = offsetY(row);
//            switch (piece){
//                case 'e': case 'g':
//                    setLayoutX(posX);
//                    setLayoutY(posY);
//                    break;
//                case 'a': case 'b': case 'd': case 'f':
//                    if (Integer.parseInt(String.valueOf(orientation))%2 == 1){
//                        setLayoutX(posX - SQUARE_SIZE/2);
//                        setLayoutY(posY + SQUARE_SIZE/2);break;
//                    }else {
//                        setLayoutX(posX);
//                        setLayoutY(posY);break;}
//                case 'c':
//                    if (Integer.parseInt(String.valueOf(orientation))%2 == 1){
//                        setLayoutX(posX - SQUARE_SIZE*1.5);
//                        setLayoutY(posY + SQUARE_SIZE*1.5);break;
//                    }else {
//                        setLayoutX(posX);
//                        setLayoutY(posY);break;}
//                case 'h':
//                    if (Integer.parseInt(String.valueOf(orientation))%2 == 1){
//                        setLayoutX(posX - SQUARE_SIZE);
//                        setLayoutY(posY + SQUARE_SIZE);break;
//                    }else {
//                        setLayoutX(posX);
//                        setLayoutY(posY);break;}
//            }

        }

        /**
         * Constructor used to place the objective piece.
         *
         * @param piece       The piece to be displayed (one of ??? objectives)
         * @param column      The column index the piece (1-8)
         * @param row         The row index of the piece (A-D)
         * @param orientation The orientation index of the piece (0-7)
         */

        Piece(char piece, char column, char row, char orientation) {

            int posX;
            int posY;


            if (piece >= 'i') {
                throw new IllegalArgumentException("Bad piece: \"" + piece + "\"");
            }
            setImage(new Image(Viewer.class.getResource(URI_BASE + piece + ".png").toString()));
            this.piece = piece - 'a';
            switch (piece) {
                case 'a':
                case 'b':
                case 'd':
                case 'f':
                    setFitHeight(2 * SQUARE_SIZE);
                    setFitWidth(3 * SQUARE_SIZE);
                    setEffect(dropShadow);
                    break;
                case 'c':
                    setFitHeight(SQUARE_SIZE);
                    setFitWidth(4 * SQUARE_SIZE);
                    setEffect(dropShadow);
                    break;
                case 'e':
                    setFitHeight(2 * SQUARE_SIZE);
                    setFitWidth(2 * SQUARE_SIZE);
                    setEffect(dropShadow);
                    break;
                case 'g':
                    setFitHeight(3 * SQUARE_SIZE);
                    setFitWidth(3 * SQUARE_SIZE);
                    setEffect(dropShadow);
                    break;
                case 'h':
                    setFitHeight(SQUARE_SIZE);
                    setFitWidth(3 * SQUARE_SIZE);
                    setEffect(dropShadow);
                    break;
            }

            // ???????????????? rotation of the image ?????????????????
            if (orientation <= '3') {
                setRotate((orientation - '0') * 90);
            } else if (orientation <= '7') {
                setScaleY(-1);
                setRotate((orientation - '4') * 90);
            }

            // put each piece in place by offsetting
            posX = offsetX(column);
            posY = offsetY(row);

            switch (piece) {
                case 'e':
                case 'g':
                    setLayoutX(posX);
                    setLayoutY(posY);
                    break;
                case 'a':
                case 'b':
                case 'd':
                case 'f':
                    if (Integer.parseInt(String.valueOf(orientation)) % 2 == 1) {
                        setLayoutX(posX - SQUARE_SIZE / 2);
                        setLayoutY(posY + SQUARE_SIZE / 2);
                        break;
                    } else {
                        setLayoutX(posX);
                        setLayoutY(posY);
                        break;
                    }
                case 'c':
                    if (Integer.parseInt(String.valueOf(orientation)) % 2 == 1) {
                        setLayoutX(posX - SQUARE_SIZE * 1.5);
                        setLayoutY(posY + SQUARE_SIZE * 1.5);
                        break;
                    } else {
                        setLayoutX(posX);
                        setLayoutY(posY);
                        break;
                    }
                case 'h':
                    if (Integer.parseInt(String.valueOf(orientation)) % 2 == 1) {
                        setLayoutX(posX - SQUARE_SIZE);
                        setLayoutY(posY + SQUARE_SIZE);
                        break;
                    } else {
                        setLayoutX(posX);
                        setLayoutY(posY);
                        break;
                    }
            }

        }
    }

    class Peg extends ImageView {
        int peg;

        /**
         * Constructor used to place the objective peg.
         *
         * @param peg    The peg to be displayed (given in the objectives)
         * @param column The column index the peg (1-8)
         * @param row    The row index of the peg (A-D)
         */
        Peg(char peg, char column, char row) {
            int posX;
            int posY;

            if (peg >= 'm') {
                throw new IllegalArgumentException("Bad tile: \"" + peg + "\"");
            }
            setImage(new Image(Viewer.class.getResource(URI_BASE + peg + ".png").toString()));
            this.peg = peg - 'i';
            setFitHeight(SQUARE_SIZE);
            setFitWidth(SQUARE_SIZE);
            setEffect(dropShadow);

            // offsetting the peg
            posX = offsetX(column);
            posY = offsetY(row);

            setLayoutX(posX);
            setLayoutY(posY);
        }
    }


    /**
     * This class extends piece with the capacity for it to be dragged and dropped,
     * and snap-to-grid.
     */
    class DraggablePiece extends Piece {
        int homeX, homeY;           // the position in the window where the piece should be when not on the panel
        double mouseX, mouseY;      // the last known mouse positions (used when dragging)


        /**
         * Construct a draggable piece
         *
         * @param piece The piece identifier ('a' - 'h')
         */

        DraggablePiece(char piece) {
            super(piece);
            pieceState[piece - 'a'] = NOT_PLACED; //start out off panel
            pieceOrientation[piece - 'a'] = 0;

            /* unplaced pieces location on board */
            switch (piece) {
                case 'a':
                    homeX = MARGIN_X;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y + 2 * SQUARE_SIZE + PIECE_SPACE;
                    setLayoutY(homeY);
                    break;
                case 'b':
                    homeX = MARGIN_X + PIECE_SPACE + 3 * SQUARE_SIZE;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y;
                    setLayoutY(homeY);
                    break;
                case 'c':
                    homeX = MARGIN_X + 3 * PIECE_SPACE + 9 * SQUARE_SIZE;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y;
                    setLayoutY(homeY);
                    break;
                case 'd':
                    homeX = MARGIN_X;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y;
                    setLayoutY(homeY);
                    break;
                case 'e':
                    homeX = MARGIN_X;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y + 3 * PIECE_SPACE + 7 * SQUARE_SIZE;
                    setLayoutY(homeY);
                    break;
                case 'f':
                    homeX = MARGIN_X + 2 * PIECE_SPACE + 6 * SQUARE_SIZE;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y;
                    setLayoutY(homeY);
                    break;
                case 'g':
                    homeX = MARGIN_X;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y + 2 * PIECE_SPACE + 4 * SQUARE_SIZE;
                    setLayoutY(homeY);
                    break;
                case 'h':
                    homeX = MARGIN_X + 3 * PIECE_SPACE + 9 * SQUARE_SIZE;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y + PIECE_SPACE + SQUARE_SIZE;
                    setLayoutY(homeY);
                    break;
            }

            /*             event handlers */
            setOnScroll(event -> {            // scroll to change orientation
                hideCompletion();
                rotate();
                event.consume();
            });

            setOnMousePressed(event -> {      // mouse press indicates begin of drag
                MouseButton btn = event.getButton();
                if (btn == MouseButton.PRIMARY) {
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                } else if (btn == MouseButton.SECONDARY) {
                    flipPiece();
                }
            });

            setOnMouseDragged(event -> {      // mouse is being dragged (holds piece)
                hideCompletion();
                toFront();
                double movementX = event.getSceneX() - mouseX;
                double movementY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + movementX);
                setLayoutY(getLayoutY() + movementY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                event.consume();
            });
            setOnMouseReleased(event -> {     // drag is complete (places piece on grid)
                snapToGrid();
            });
        }


        /**
         * Snap the tile to the nearest grid position (if it is over the grid)
         */
        private void snapToGrid() {

            if (onBoard() ) {

                for (int i = 0; i <= 7; i++){
                    if (getLayoutX() >= (MAIN_PANEL_OFFSET_X - (SQUARE_SIZE / 2) + i*SQUARE_SIZE) &&
                            ( getLayoutX() < (MAIN_PANEL_OFFSET_X + (SQUARE_SIZE / 2) + i*SQUARE_SIZE)) ){
                        setLayoutX(MAIN_PANEL_OFFSET_X + i*SQUARE_SIZE);
                    }

                    if (getLayoutY() >= (MAIN_PANEL_OFFSET_Y - (SQUARE_SIZE / 2) + i*SQUARE_SIZE) &&
                            ( getLayoutY() < (MAIN_PANEL_OFFSET_Y + (SQUARE_SIZE / 2) + i*SQUARE_SIZE)) ){
                        setLayoutY(MAIN_PANEL_OFFSET_Y + i*SQUARE_SIZE);
                    }
                }
                setPosition();
            } else {
                snapToHome();
            }
            updateAndCheck();
        }

        /**
         * Snap the mask to its home position (if it is not on the grid)
         */
        private void snapToHome() {
            setLayoutX(homeX);
            setLayoutY(homeY);
            setRotate(0);
            pieceState[piece] = NOT_PLACED;
            pieceOrientation[piece] = '0';
        }


        /**
         * Rotate the piece by 90 degrees (unless this is mask zero and there is a constraint on mask zero)
         */
        private void rotate() {
            double angle = (getRotate() + 90) % 360;
            setRotate(angle);
            pieceOrientation[piece] =  (char) (angle / 90);
            setPosition();
            updateAndCheck();
        }

        /**
         * Flip the piece horizontally
         */
        private void flipPiece() {
            if (pieceState[piece] == NOT_PLACED){
                if ( pieceOrientation[piece] == '0') {
                    setScaleY(-1);
                    pieceOrientation[piece] = '4';
                    setPosition();
                    updateAndCheck();
                } else if (pieceOrientation[piece] == '4'){
                    setScaleY(-1);
                    pieceOrientation[piece] = '0';
                    setPosition();
                    updateAndCheck();
                }
            }
            }


        /**
         * @return true if the mask is on the board
         */
        private boolean onBoard() {
            return getLayoutX() > (MAIN_PANEL_OFFSET_X - (SQUARE_SIZE / 2)) && (getLayoutX() < (MAIN_PANEL_OFFSET_X + 7.5 * SQUARE_SIZE))
                    && getLayoutY() > (MAIN_PANEL_OFFSET_Y - (SQUARE_SIZE / 2)) && (getLayoutY() < (MAIN_PANEL_OFFSET_Y + 3.5 * SQUARE_SIZE));
        }


        /**
         * Determine the grid-position of the origin of the tile
         * or -1 if it is off the grid, taking into account its rotation.
         */
        private void setPosition() {
            int x = (int) (getLayoutX() - MAIN_PANEL_OFFSET_X) / SQUARE_SIZE;
            int y = (int) (getLayoutY() - MAIN_PANEL_OFFSET_Y) / SQUARE_SIZE;
            int rotate = (int) getRotate() / 90;
            if (x < 0 || y < 0)
                pieceState[piece] = NOT_PLACED;
            else {
                char val = (char) (y * 8 + x);
                pieceState[piece] = val;
            }
        }


        /**
         * @return the mask placement represented as a string
         */
        public String toString() {
            return "Pos index: " + pieceState[piece] + ", Orientation index: " + pieceOrientation[piece];
        }

    }

    /**
     * Set up each of the eight pieces
     */
    private void makePieces() {
        pieces.getChildren().clear();
        for (char m = 'a'; m <= 'h'; m++) {
            pieces.getChildren().add(new DraggablePiece(m));
        }
    }


    private void updateAndCheck() { //...
    }



    private void newGame() {
        makePieces();
    }


    // calculate offset in Y direction
    private int offsetY(char row) {
        return MAIN_PANEL_OFFSET_Y + (row - 'A') * SQUARE_SIZE;
    }

    // calculate offset in X direction
    private int offsetX(char column) {
        return MAIN_PANEL_OFFSET_X + (column - '1') * SQUARE_SIZE;
    }


    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
//    private void makePlacement(String placement) {
//
//        char pieceType;
//        char column;
//        char row;
//        char orientation;
//
//        pieces.getChildren().clear();
//        pegs.getChildren().clear();
//
//        for (int i = 0; i < placement.length(); i += 4) {
//            pieceType = placement.charAt(i);
//            column = placement.charAt(i + 1);
//            row = placement.charAt(i + 2);
//            orientation = placement.charAt(i + 3);
//
//            if (pieceType >= 'a' && pieceType <= 'h') {
//                pieces.getChildren().add(new Viewer.Piece(pieceType, column, row, orientation));
//
//            } else if (pieceType >= 'i' && pieceType <= 'l') {
//                pegs.getChildren().add(new Viewer.Peg(pieceType, column, row));
//            }
//
//        }
//    }


    /**
     * Show the completion message
     */
    private void showCompletion() {
        completionText.toFront();
        completionText.setOpacity(1);
    }


    /**
     * Hide the completion message
     */
    private void hideCompletion() {
        completionText.toBack();
        completionText.setOpacity(0);
    }

    /**
     * Set up the group that represents the places that make the board
     */
    private void makeGameBoard() {
        gameBoard.getChildren().clear();

        Rectangle background = new Rectangle(MAIN_PANEL_OFFSET_X, MAIN_PANEL_OFFSET_Y, MAIN_PANEL_WIDTH, MAIN_PANEL_HEIGHT);
        background.setFill(Color.BLACK);
        System.out.println("here");

        gameBoard.getChildren().add(background);
        gameBoard.toBack();
    }


    // FIXME Task 7: Implement a basic playable Twist Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement starting placements

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TwistGame Board");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

//        root.getChildren().add(controls);
        root.getChildren().add(gameBoard);
        root.getChildren().add(pieces);
//        root.getChildren().add(pegs);

        makeGameBoard();
        //makeControls();
        //makePlacement("a7A7b6A7c1A3d2A6e2C3f3C4g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0");    // run placement

        newGame();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}

//
//    private void makeBoard() {
//        board.getChildren().clear();
//
//        ImageView baseboard = new ImageView();
//        baseboard.setImage(new Image(BASEBOARD_URI));
//        baseboard.setFitWidth(MAIN_PANEL_WIDTH);
//        baseboard.setFitHeight(MAIN_PANEL_HEIGHT);
//        baseboard.setLayoutX(MAIN_PANEL_X);
//        board.getChildren().add(baseboard);
//
//        board.toBack();
//    }

//    //use grid pane perhaps?
//    public void grid() {
//        Rectangle tile = new Rectangle(300, 200, 60, 60);
//        int a = 300;
//        int b = 250;
//        int xstep = 60;
//        int ystep = 60;
//        tile.setFill(null);
//        tile.setStroke(Color.BLACK);
//
//        for (int i = 0; i < 8 ; i++) {
//            tile = new Rectangle (a+xstep*i, b, 60, 60);
//            root.getChildren().add(tile);
//            tile.setFill(null);
//            tile.setStroke(Color.BLACK);
//            for (int j = 0; j < 4 ; j++) {
//                tile = new Rectangle (a, b+ystep*j, 60, 60);
//                root.getChildren().add(tile);
//                tile.setFill(null);
//                tile.setStroke(Color.BLACK);
//            }
//
//        }
//    }






