package comp1110.ass2.gui;


import comp1110.ass2.Colour;
import comp1110.ass2.Piece;
import comp1110.ass2.TwistGame;

import comp1110.ass2.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;




import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.geometry.Bounds;


import java.awt.*;

public class Board extends Application {
    /*board layout*/
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 55;
    private static final int MAIN_PANEL_WIDTH = 8 * SQUARE_SIZE;
    private static final int MAIN_PANEL_HEIGHT = 4 * SQUARE_SIZE;
    private static final int MARGIN_X = (BOARD_WIDTH - 13*SQUARE_SIZE - 60)/2;
    private static final int MARGIN_Y = 80;

    private static final int MAIN_PANEL_OFFSET_X = BOARD_WIDTH - MARGIN_X - MAIN_PANEL_WIDTH;
    private static final int MAIN_PANEL_OFFSET_Y = BOARD_HEIGHT - MARGIN_Y - MAIN_PANEL_HEIGHT;

    private static VBox vBox;
    private static HBox hBox;


    //    private static final int MAIN_PANEL_X = MARGIN_X + MAIN_PANEL_HEIGHT;
    private static final int PIECE_SPACE = 20;


    /* where to find media assets*/
    private static final String URI_BASE = "assets/";
    //private static final String BASEBOARD_URI = Board.class.getResource(URI_BASE + "baseboard.png").toString();


    /* make for unplaced piece*/
    public static final char NOT_PLACED = 255;


    /* node groups */
    private final Group root = new Group();
    private final Group solution = new Group();
    private final Group gameBoard = new Group();
    private final Group controls = new Group();
    private final Group pieces = new Group();
    private final Group pegs = new Group();
    private final Group board = new Group();
    private final Group challenge = new Group();

    /* message on success*/
    private final Text completionText = new Text("Well done!");

    /* the state of the pieces */
    int[] pieceState = new int[8];        // state of each piece, if not on the board, -1, if on the board, it stores the
                                         // key pos index of the piece, which is an integer from 0 - 31.
    /* the orientation of the pieces */
    int[] pieceOrientation = new int[8];  //  denoted by integer 0 - 7

    String pegPlacementString = "";

    String startingPlacement = "";

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
        public Piece(char piece) {

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

        }

        /**
         * Constructor used to place the objective piece.
         *
         * @param piece       The piece to be displayed (one of ??? objectives)
         * @param column      The column index the piece (1-8)
         * @param row         The row index of the piece (A-D)
         * @param orientation The orientation index of the piece (0-7)
         */

        public Piece(char piece, char column, char row, char orientation) {

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

            // rotation of the image
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
                        setLayoutY(posY - SQUARE_SIZE / 2);
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
        public Peg(char peg, char column, char row) {
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

        int flipCount = 0;          // count the number of flips, used in flipPiece() method


        /**
         * Construct a draggable piece
         *
         * @param piece The piece identifier ('a' - 'h')
         */

        DraggablePiece(char piece) {
            super(piece);
            pieceState[piece - 'a'] = -1; //start out off panel
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
                    homeX = MARGIN_X;
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
                    homeX = MARGIN_X + 3 * PIECE_SPACE + 9 * SQUARE_SIZE;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y + PIECE_SPACE + 2* SQUARE_SIZE;
                    setLayoutY(homeY);
                    break;
                case 'e':
                    homeX = MARGIN_X + 2 * PIECE_SPACE + 6 * SQUARE_SIZE;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y +  PIECE_SPACE + 2 * SQUARE_SIZE;
                    setLayoutY(homeY);
                    break;
                case 'f':
                    homeX = MARGIN_X + 2 * PIECE_SPACE + 6 * SQUARE_SIZE;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y;
                    setLayoutY(homeY);
                    break;
                case 'g':
                    homeX = MARGIN_X + PIECE_SPACE + 3*SQUARE_SIZE;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y +  PIECE_SPACE + 2 * SQUARE_SIZE;
                    setLayoutY(homeY);
                    break;
                case 'h':
                    homeX = MARGIN_X + 1 * PIECE_SPACE + 3 * SQUARE_SIZE;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y ;
                    setLayoutY(homeY);
                    break;
            }

            /*             event handlers */
//            setOnScroll(event -> {            // scroll to change orientation
//                hideCompletion();
//                rotate();
//                event.consume();
//            });

            setOnMousePressed(event -> {      // mouse press indicates begin of drag

                setOnScroll(event2 -> {            // scroll to change orientation
                    hideCompletion();
                    rotate();
                    event.consume();
                });

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
            setOnMouseReleased(event -> {     // drag is complete (placing piece on grid)
                MouseButton btn2 = event.getButton();
                if (btn2 == MouseButton.PRIMARY)snapToGrid();

            });
        }


        /**
         * Snap the tile to the nearest grid position (if it is over the grid)
         */
        private void snapToGrid() {

            if (onBoard()) {

                switch (piece) {
                    case 4:
                    case 6:
                        for (int i = 0; i <= 7; i++) {
                            if (getLayoutX() >= (MAIN_PANEL_OFFSET_X - (SQUARE_SIZE / 2) + i * SQUARE_SIZE) &&
                                    (getLayoutX() < (MAIN_PANEL_OFFSET_X + (SQUARE_SIZE / 2) + i * SQUARE_SIZE))) {
                                setLayoutX(MAIN_PANEL_OFFSET_X + i * SQUARE_SIZE);
                            }

                            if (getLayoutY() >= (MAIN_PANEL_OFFSET_Y - (SQUARE_SIZE / 2) + i * SQUARE_SIZE) &&
                                    (getLayoutY() < (MAIN_PANEL_OFFSET_Y + (SQUARE_SIZE / 2) + i * SQUARE_SIZE))) {
                                setLayoutY(MAIN_PANEL_OFFSET_Y + i * SQUARE_SIZE);
                            }
                        } break;

                    case 0:
                    case 1:
                    case 3:
                    case 5:

                        if (pieceOrientation[piece] % 2 == 1) {
                            for (int i = 0; i <= 7; i++) {
                                if (getLayoutX() + SQUARE_SIZE / 2 >= (MAIN_PANEL_OFFSET_X - (SQUARE_SIZE / 2) + i * SQUARE_SIZE) &&
                                        (getLayoutX() + SQUARE_SIZE / 2 < (MAIN_PANEL_OFFSET_X + (SQUARE_SIZE / 2) + i * SQUARE_SIZE))) {
                                    setLayoutX(MAIN_PANEL_OFFSET_X + i * SQUARE_SIZE - SQUARE_SIZE / 2);
                                }

                                if (getLayoutY() - SQUARE_SIZE / 2 >= (MAIN_PANEL_OFFSET_Y - (SQUARE_SIZE / 2) + i * SQUARE_SIZE) &&
                                        (getLayoutY() - SQUARE_SIZE / 2 < (MAIN_PANEL_OFFSET_Y + (SQUARE_SIZE / 2) + i * SQUARE_SIZE))) {
                                    setLayoutY(MAIN_PANEL_OFFSET_Y + i * SQUARE_SIZE + SQUARE_SIZE / 2);
                                }
                            } break;
                        } else {
                            for (int j = 0; j <= 7; j++) {
                                if (getLayoutX() >= (MAIN_PANEL_OFFSET_X - (SQUARE_SIZE / 2) + j * SQUARE_SIZE) &&
                                        (getLayoutX() < (MAIN_PANEL_OFFSET_X + (SQUARE_SIZE / 2) + j * SQUARE_SIZE))) {
                                    setLayoutX(MAIN_PANEL_OFFSET_X + j * SQUARE_SIZE);
                                }

                                if (getLayoutY() >= (MAIN_PANEL_OFFSET_Y - (SQUARE_SIZE / 2) + j * SQUARE_SIZE) &&
                                        (getLayoutY() < (MAIN_PANEL_OFFSET_Y + (SQUARE_SIZE / 2) + j * SQUARE_SIZE))) {
                                    setLayoutY(MAIN_PANEL_OFFSET_Y + j * SQUARE_SIZE);
                                }
                            } break;
                        }

                    case 2:

                        if (pieceOrientation[piece] % 2 == 1) {
                            for (int i = 0; i <= 7; i++) {
                                if (getLayoutX() + SQUARE_SIZE * 1.5 >= (MAIN_PANEL_OFFSET_X - (SQUARE_SIZE / 2) + i * SQUARE_SIZE) &&
                                        (getLayoutX() + SQUARE_SIZE * 1.5 < (MAIN_PANEL_OFFSET_X + (SQUARE_SIZE / 2) + i * SQUARE_SIZE))) {
                                    setLayoutX(MAIN_PANEL_OFFSET_X + i * SQUARE_SIZE - SQUARE_SIZE * 1.5);
                                }

                                if (getLayoutY() - SQUARE_SIZE * 1.5 >= (MAIN_PANEL_OFFSET_Y - (SQUARE_SIZE / 2) + i * SQUARE_SIZE) &&
                                        (getLayoutY() - SQUARE_SIZE * 1.5 < (MAIN_PANEL_OFFSET_Y + (SQUARE_SIZE / 2) + i * SQUARE_SIZE))) {
                                    setLayoutY(MAIN_PANEL_OFFSET_Y + i * SQUARE_SIZE + SQUARE_SIZE * 1.5);
                                }
                            } break;
                        } else {
                            for (int j = 0; j <= 7; j++) {
                                if (getLayoutX() >= (MAIN_PANEL_OFFSET_X - (SQUARE_SIZE / 2) + j * SQUARE_SIZE) &&
                                        (getLayoutX() < (MAIN_PANEL_OFFSET_X + (SQUARE_SIZE / 2) + j * SQUARE_SIZE))) {
                                    setLayoutX(MAIN_PANEL_OFFSET_X + j * SQUARE_SIZE);
                                }

                                if (getLayoutY() >= (MAIN_PANEL_OFFSET_Y - (SQUARE_SIZE / 2) + j * SQUARE_SIZE) &&
                                        (getLayoutY() < (MAIN_PANEL_OFFSET_Y + (SQUARE_SIZE / 2) + j * SQUARE_SIZE))) {
                                    setLayoutY(MAIN_PANEL_OFFSET_Y + j * SQUARE_SIZE);
                                }
                            }break;
                        }

                    case 7:

                        if ( pieceOrientation[piece] % 2 == 1) {
                            for (int i = 0; i <= 7; i++) {
                                if (getLayoutX() + SQUARE_SIZE >= (MAIN_PANEL_OFFSET_X - (SQUARE_SIZE / 2) + i * SQUARE_SIZE) &&
                                        (getLayoutX() + SQUARE_SIZE < (MAIN_PANEL_OFFSET_X + (SQUARE_SIZE / 2) + i * SQUARE_SIZE))) {
                                    setLayoutX(MAIN_PANEL_OFFSET_X + i * SQUARE_SIZE - SQUARE_SIZE);
                                }

                                if (getLayoutY() - SQUARE_SIZE >= (MAIN_PANEL_OFFSET_Y - (SQUARE_SIZE / 2) + i * SQUARE_SIZE) &&
                                        (getLayoutY() - SQUARE_SIZE < (MAIN_PANEL_OFFSET_Y + (SQUARE_SIZE / 2) + i * SQUARE_SIZE))) {
                                    setLayoutY(MAIN_PANEL_OFFSET_Y + i * SQUARE_SIZE + SQUARE_SIZE);
                                }
                            }break;
                        } else {
                            for (int j = 0; j <= 7; j++) {
                                if (getLayoutX() >= (MAIN_PANEL_OFFSET_X - (SQUARE_SIZE / 2) + j * SQUARE_SIZE) &&
                                        (getLayoutX() < (MAIN_PANEL_OFFSET_X + (SQUARE_SIZE / 2) + j * SQUARE_SIZE))) {
                                    setLayoutX(MAIN_PANEL_OFFSET_X + j * SQUARE_SIZE);
                                }

                                if (getLayoutY() >= (MAIN_PANEL_OFFSET_Y - (SQUARE_SIZE / 2) + j * SQUARE_SIZE) &&
                                        (getLayoutY() < (MAIN_PANEL_OFFSET_Y + (SQUARE_SIZE / 2) + j * SQUARE_SIZE))) {
                                    setLayoutY(MAIN_PANEL_OFFSET_Y + j * SQUARE_SIZE);
                                }
                            }break;
                        }
                }

                System.out.println("layout x: "+ getLayoutX() + " layoutY: " + getLayoutY());

                setPosition();

                // check piece overlapping
                String tempString = "";
                for (int i = 0; i < pieceState.length; i ++){
                    if (pieceState[i] != -1){
                        tempString += String.valueOf((char) ('a' + i)) + comp1110.ass2.Piece.posEncode(pieceState[i]) + String.valueOf(pieceOrientation[i]);
                    }
                }
                tempString += pegPlacementString;
                System.out.println("temp string to check: " + tempString);

                if (!TwistGame.isPlacementStringValid(tempString)) {
                    System.out.println("there's an overlap");snapToHome();}

            }

         else{ snapToHome(); }

        updateAndCheck();
    }


        /**
         * Snap the mask to its home position (if it is not on the grid)
         */
        private void snapToHome() {

            if (flipCount % 2 == 0) {
                setLayoutX(homeX);
                setLayoutY(homeY);
                setRotate(0);
            } else {
                setLayoutX(homeX);
                setLayoutY(homeY);
                flipPiece();
                setRotate(0);
            }
            pieceState[piece] = -1;
            pieceOrientation[piece] = 0;
        }


        /**
         * Rotate the piece by 90 degrees (unless this is mask zero and there is a constraint on mask zero)
         */
        private void rotate() {
            double angle = (getRotate() + 90) % 360;
            setRotate(angle);
            int temp = pieceOrientation[piece];
            pieceOrientation[piece] = comp1110.ass2.Piece.rotate(temp);

            if (onBoard()) snapToGrid();

            setPosition();
            updateAndCheck();
        }

        /**
         * Flip the piece horizontally
         */
        private void flipPiece() {

            int prevOri = pieceOrientation[piece];
            pieceOrientation[piece] = comp1110.ass2.Piece.flip(piece, prevOri);

            flipCount ++;

            if (prevOri % 2 == 0) setScaleY(getScaleY() * -1);         // flip the piece
            else setScaleX(getScaleX() * -1);

            setPosition();
            updateAndCheck();
            }


        /**
         * @return true if the mask is on the board
         */
        private boolean onBoard() {
            Bounds bound = this.localToScene(this.getBoundsInLocal());
            double minx = bound.getMinX();
            double miny = bound.getMinY();
            double maxx = bound.getMaxX();
            double maxy = bound.getMaxY();

            return (minx > (MAIN_PANEL_OFFSET_X - SQUARE_SIZE/2)) && (miny > (MAIN_PANEL_OFFSET_Y - SQUARE_SIZE/2)) &&
                    (maxx < (MAIN_PANEL_OFFSET_X + MAIN_PANEL_WIDTH + SQUARE_SIZE/2)) && (maxy < (MAIN_PANEL_OFFSET_Y + MAIN_PANEL_HEIGHT + SQUARE_SIZE/2));

        }


        /**
         * Determine the grid-position of the origin of the piece
         * or -1 if it is off the grid, taking into account its rotation.
         */
        private void setPosition() {

            Bounds bound = this.localToScene(this.getBoundsInLocal());
            double minx = bound.getMinX();
            double miny = bound.getMinY();


            int x = (int) (minx - MAIN_PANEL_OFFSET_X + SQUARE_SIZE/2) / SQUARE_SIZE;
            int y = (int) (miny - MAIN_PANEL_OFFSET_Y + SQUARE_SIZE/2) / SQUARE_SIZE;

            System.out.println("posx: "+ x + " posy: " + y);

            if (x < 0 || y < 0) {
                pieceState[piece] = -1;
            }
            else {
                int val =  (y * 8 + x);
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
        //pieces.getChildren().clear();
        for (char m = 'a'; m <= 'h'; m++) {
            if (pieceState[m-'a'] == -1){
            pieces.getChildren().add(new DraggablePiece(m));}
        }
    }


    // calculate offset in Y direction
    private int offsetY(char row) {
        return MAIN_PANEL_OFFSET_Y + (row - 'A') * SQUARE_SIZE;
    }

    // calculate offset in X direction
    private int offsetX(char column) {
        return MAIN_PANEL_OFFSET_X + (column - '1') * SQUARE_SIZE;
    }


    private void updateAndCheck() {


        System.out.println("******************************************");
        for (int i = 0; i < pieceState.length; i ++){
            System.out.println("piece number: "+ i +" piece state:" + pieceState[i] + " piece ori: "+ pieceOrientation[i]);
        }
        System.out.println("\n");

        boolean finished = TwistGame.updateAndCheck(pieceState, pieceOrientation, pegPlacementString);
        if (!finished) { return; }
        else showCompletion();
        //...
    }

    /**
     * Create the message to be displayed when the player completes the puzzle.
     */
    private void makeCompletion() {
        completionText.setFill(Color.BLACK);
        completionText.setEffect(dropShadow);
        completionText.setCache(true);
        completionText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 80));
        completionText.setLayoutX(MAIN_PANEL_OFFSET_X);
        completionText.setLayoutY(MAIN_PANEL_OFFSET_Y - 100);
        completionText.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().add(completionText);
    }
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

        // baseboard as a triangle with colour GRAY
        Rectangle background = new Rectangle(MAIN_PANEL_OFFSET_X, MAIN_PANEL_OFFSET_Y, MAIN_PANEL_WIDTH, MAIN_PANEL_HEIGHT);
        background.setFill(Color.LIGHTGRAY);
        gameBoard.getChildren().add(background);

        // grids
        for (int i = 1; i < MAIN_PANEL_WIDTH/SQUARE_SIZE; i++){
            Line line = new Line();
            line.setStartX(MAIN_PANEL_OFFSET_X + i * SQUARE_SIZE);
            line.setEndX(MAIN_PANEL_OFFSET_X + i * SQUARE_SIZE);
            line.setStartY(MAIN_PANEL_OFFSET_Y);
            line.setEndY(MAIN_PANEL_OFFSET_Y + MAIN_PANEL_HEIGHT);
            gameBoard.getChildren().add(line);
        }

        for (int i = 1; i < MAIN_PANEL_HEIGHT/SQUARE_SIZE; i++){
            Line line = new Line();
            line.setStartX(MAIN_PANEL_OFFSET_X);
            line.setEndX(MAIN_PANEL_OFFSET_X +  MAIN_PANEL_WIDTH);
            line.setStartY(MAIN_PANEL_OFFSET_Y + i * SQUARE_SIZE);
            line.setEndY(MAIN_PANEL_OFFSET_Y + i * SQUARE_SIZE);
            gameBoard.getChildren().add(line);
        }

        gameBoard.toBack();
    }

    /**
     * Set up the group that represents the solution (and make it transparent)
     *
     * @param solution The solution as an array of chars.
     */
    private void makeSolution(String solution) {
        this.solution.getChildren().clear();
        if (solution == null) return;

        if (solution.length()/8 != 4) {
            throw new IllegalArgumentException("Solution incorrect length: " + solution);
        }
        for (int i = 0; i < solution.length(); i+=4) {
            char pieceid = solution.charAt(i);
            char column = solution.charAt(i+1);
            char row = solution.charAt(i+2);
            char ori = solution.charAt(i+3);
            Piece piece = new Piece(pieceid,column,row,ori);

//            int x = (solution[i] / 4) % 3;
//            int y = (solution[i] / 4) / 3;
//            int rotation = solution[i] % 4;
//
//            tile.setLayoutX(BOARD_X + (x * SQUARE_SIZE));
//            tile.setLayoutY(BOARD_Y + (y * SQUARE_SIZE));
//            tile.setRotate(90 * rotation);

            this.solution.getChildren().add(piece);
        }
        this.solution.setOpacity(0);
    }

    /**
     * Set up event handlers for the main game
     *
     * @param scene The Scene used by the game.
     */
    private void setUpHandlers(Scene scene) {
        /* create handlers for key press and release events */
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.M) {
                // toggleSoundLoop();
                event.consume();
            } else if (event.getCode() == KeyCode.Q) {
                Platform.exit();
                event.consume();
            } else if (event.getCode() == KeyCode.SLASH) {
                solution.setOpacity(1.0);
                pieces.setOpacity(0);
                event.consume();
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.SLASH) {
                solution.setOpacity(0);
                pieces.setOpacity(1.0);
                event.consume();
            }
        });
    }
    // FIXME Task 7: Implement a basic playable Twist Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement starting placements

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements

    /**
     * Implement starting placement
     *
     * @param placement A string representing starting state of the game
     */
    private void makePlacement(String placement) {

        char pieceType;
        char column;
        char row;
        char orientation;

        pieces.getChildren().clear();
        pegs.getChildren().clear();

        for (int i = 0; i < placement.length(); i += 4) {
            pieceType = placement.charAt(i);
            column = placement.charAt(i + 1);
            row = placement.charAt(i + 2);
            orientation = placement.charAt(i + 3);

            if (pieceType >= 'a' && pieceType <= 'h') {
                pieces.getChildren().add(new Board.Piece(pieceType, column, row, orientation));
                pieceState[pieceType - 'a'] = column - '1' + 8 * (row - 'A');
                pieceOrientation[pieceType - 'a'] = orientation - '0';

            } else if (pieceType >= 'i' && pieceType <= 'l') {
                pegPlacementString += placement.substring(i,i + 4);
                pegs.getChildren().add(new Board.Peg(pieceType, column, row));

            }

        }

        System.out.println("peg placement is: "+ pegPlacementString);
    }


    /**
     * Start a new game, resetting everything as necessary
     */
    private void newGame() {
        hideCompletion();

        // reset all information from the previous game
        for (int i = 0; i < pieceState.length; i ++){
            pieceState[i] = -1;
        }
        for (int i = 0; i < pieceOrientation.length; i ++){
            pieceOrientation[i] = 0;
        }
        pegPlacementString = "";
        startingPlacement = "";

        TwistGame1 twistGame = new TwistGame1((int)difficulty.getValue());  // start a new game with selected difficulty
        startingPlacement = twistGame.getPlacement();
        makePlacement(twistGame.getPlacement());  // put starting placement on the board
        makePieces();
        System.out.println("before computing solution");
        System.out.println(startingPlacement);
        String[] solu = TwistGame.getSolutions(startingPlacement);
        System.out.println("after computing solution");
        makeSolution(solu[1]);

    }

    /**
     * Put all of the pieces back in their home position
     * Author: Hua Guo
     */
    private void resetPieces() {
        pieces.toFront();
        for (Node n : pieces.getChildren()) {
            if (n instanceof DraggablePiece) {
                ((DraggablePiece)n).snapToHome();
        }}
    }

    /* the difficulty slider */
    private final Slider difficulty = new Slider();

    /**
     * Create the controls that allow the game to be start with selected difficulty
     * and reset pieces back in their home position
     * Author: Hua Guo
     */
    private void makeControls() {
        // start button
        Button button = new Button("Start");
        button.setLayoutX(MARGIN_X +  SQUARE_SIZE);
        button.setLayoutY(MAIN_PANEL_OFFSET_Y + SQUARE_SIZE);
        button.setTextFill(Color.RED);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                newGame();          // start a new game with selected difficulty
            }
        });
        controls.getChildren().add(button);

        // reset button
        Button button2 = new Button("Reset");
        button2.setLayoutX(MARGIN_X +  SQUARE_SIZE);
        button2.setLayoutY(MAIN_PANEL_OFFSET_Y + 2* SQUARE_SIZE);
        button2.setTextFill(Color.RED);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                resetPieces();      // put pieces back in their home position
            }
        });
        controls.getChildren().add(button2);

        // difficulty level
        difficulty.setMin(1);       // set the difficulty range
        difficulty.setMax(4);
        difficulty.setValue(2);     // set the initial default difficulty
        difficulty.setShowTickLabels(true);
        difficulty.setShowTickMarks(true);
        difficulty.setMajorTickUnit(1);
        difficulty.setMinorTickCount(1);
        difficulty.setSnapToTicks(true);

        difficulty.setLayoutX(MARGIN_X + SQUARE_SIZE);
        difficulty.setLayoutY(MAIN_PANEL_OFFSET_Y);
        controls.getChildren().add(difficulty);

        final javafx.scene.control.Label difficultyCaption = new Label("Difficulty:");
        difficultyCaption.setTextFill(Color.GREEN);
        difficultyCaption.setLayoutX(MARGIN_X + SQUARE_SIZE);
        difficultyCaption.setLayoutY(MAIN_PANEL_OFFSET_Y - 20);
        controls.getChildren().add(difficultyCaption);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TwistGame Board");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        root.getChildren().add(gameBoard);
        root.getChildren().add(pieces);
        root.getChildren().add(pegs);
        root.getChildren().add(controls);
        root.getChildren().add(solution);

        setUpHandlers(scene);
        makeGameBoard();
        makeControls();
        makeCompletion();

        newGame();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}







