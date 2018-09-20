package comp1110.ass2.gui;

import comp1110.ass2.Colour;
import comp1110.ass2.TwistGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 60;
    private static final int MAIN_PANEL_X = MARGIN_X + MAIN_PANEL_HEIGHT;
    private static final int PIECE_SPACE = 20;
    private static final int VIEWER_WIDTH = 750;
    private static final int VIEWER_HEIGHT = 500;

    private static int sceneOffsetX = 210;
    private static int sceneOffsetY = 150;


    /* where to find media assets*/
    private static final String URI_BASE = "assets/";
    private static final String BASEBOARD_URI = Board.class.getResource(URI_BASE + "baseboard.png").toString();



    /* make for unplaced piece*/
    public static final char NOT_PLACED = 255;

    /* node groups */
    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group pieces = new Group();
    private final Group pegs = new Group();
    private final Group board = new Group();

    /* message on success*/
    private final Text successText = new Text("Well done!");

    /* piece at the begin*/
    char [] piecestate = new char[8];

    /* the IQ-TWIST game*/
    TwistGame twistGame;

    /* Define a drop shadow effect that we will apply to tiles */
    private static DropShadow dropShadow;

    /* Static initializer to initialize dropShadow */
    {
        dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0);
        dropShadow.setOffsetY(2.0);
        dropShadow.setColor(Color.color(0, 0, 0, .4));
    }

    /** an inner class that represents pieces in the game
     *  visual presentation of the piece
     */

    class Piece extends ImageView {
        int piece;
        /** constructor for a playing piece
         *
         * @param piece the letter representing the piece to be created.
         */
        Piece (char piece){

            int posX;  int posY;
            double recHeight = 0;  double recWidth = 0;

            if (piece >= 'i') {
                throw new IllegalArgumentException("Bad piece: \"" + piece + "\"");
            }
            setImage(new Image(Viewer.class.getResource(URI_BASE + piece + ".png").toString()));
            this.piece = piece - 'a';
            switch (piece){
                case 'a': case 'b': case 'd': case 'f':
                    setFitHeight(2*SQUARE_SIZE); recHeight = 2*SQUARE_SIZE;
                    setFitWidth(3*SQUARE_SIZE);  recWidth = 3*SQUARE_SIZE;
                    setEffect(dropShadow); break;
                case 'c':
                    setFitHeight(SQUARE_SIZE); recHeight = SQUARE_SIZE;
                    setFitWidth(4*SQUARE_SIZE); recWidth = 4*SQUARE_SIZE;
                    setEffect(dropShadow); break;
                case 'e':
                    setFitHeight(2*SQUARE_SIZE); recHeight = 2*SQUARE_SIZE;
                    setFitWidth(2*SQUARE_SIZE); recWidth = 2*SQUARE_SIZE;
                    setEffect(dropShadow); break;
                case 'g':
                    setFitHeight(3*SQUARE_SIZE); recHeight = 3*SQUARE_SIZE;
                    setFitWidth(3*SQUARE_SIZE); recWidth = 3*SQUARE_SIZE;
                    setEffect(dropShadow); break;
                case 'h':
                    setFitHeight(SQUARE_SIZE); recHeight = SQUARE_SIZE;
                    setFitWidth(3*SQUARE_SIZE); recWidth = 3*SQUARE_SIZE;
                    setEffect(dropShadow);break;
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
            switch (piece){
                case 'e': case 'g':
//                    setLayoutX(posX);
//                    setLayoutY(posY);
                    break;
                case 'a': case 'b': case 'd': case 'f':
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
            }

        }
    }
    class Peg extends ImageView{
        int peg;
        /** constructor for a peg
         *
         */
        Peg (char peg, char column, char row){
            int posX;  int posY;

            if (peg >= 'm') {
                throw new IllegalArgumentException("Bad tile: \"" + peg + "\"");
            }
            setImage(new Image(Viewer.class.getResource(URI_BASE + peg + ".png").toString()));
            this.peg = peg - 'i';
            setFitHeight(SQUARE_SIZE);
            setFitWidth(SQUARE_SIZE);
            setEffect(dropShadow);

            // offsetting the peg
//            posX = offsetX(column);
//            posY = offsetY(row);

//            setLayoutX(posX);
//            setLayoutY(posY);
        }
    }
    // calculate offset in Y direction
//    private int offsetY (char row){
//        return sceneOffsetY + (row - 'A')*SQUARE_SIZE;
//    }
//    // calculate offset in X direction
//    private int offsetX (char column){
//        return sceneOffsetX + (column - '1')*SQUARE_SIZE;
//    }

    /**
     * This class extends piece with the capacity for it to be dragged and dropped,
     * and snap-to-grid.
     */
    class DraggablePiece extends Piece{
        int homeX, homeY;  // the position in the window where the piece should be when not on the panel
        double mouseX, mouseY;      // the last known mouse positions (used when dragging)


        /**
         * Construct a draggable piece
         *
         * @param piece The tile identifier ('a' - 'h')
         */

        DraggablePiece(char piece) {
            super(piece);
            piecestate[piece - 'a'] = NOT_PLACED; //start out off panel

            /* unplaced pieces location on board */
            switch (piece) {
                case 'a':
                    homeX = MARGIN_X;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y+ 2 * SQUARE_SIZE + PIECE_SPACE;
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
                    homeY = MARGIN_Y ;
                    setLayoutY(homeY);
                    break;
                case 'd':
                    homeX = MARGIN_X;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y ;
                    setLayoutY(homeY);
                    break;
                case 'e':
                    homeX = MARGIN_X ;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y + 3 * PIECE_SPACE + 7 * SQUARE_SIZE;
                    setLayoutY(homeY);
                    break;
                case 'f':
                    homeX = MARGIN_X + 2 * PIECE_SPACE + 6 * SQUARE_SIZE;
                    setLayoutX(homeX);
                    homeY = MARGIN_Y ;
                    setLayoutY(homeY);
                    break;
                case 'g':
                    homeX = MARGIN_X ;
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

            /* event handlers */
//            setOnScroll(event -> {            // scroll to change orientation
////                hideCompletion();
//                rotate();
//                event.consume();
//            });
//
//            setOnMousePressed(event -> {      // mouse press indicates begin of drag (select piece)
//                mouseX = event.getSceneX();
//                mouseY = event.getSceneY();
//            });

//            setOnMouseDragged(event -> {      // mouse is being dragged (holds piece)
//                hideCompletion();
//                toFront();
//                double movementX = event.getSceneX() - mouseX;
//                double movementY = event.getSceneY() - mouseY;
//                setLayoutX(getLayoutX() + movementX);
//                setLayoutY(getLayoutY() + movementY);
//                mouseX = event.getSceneX();
//                mouseY = event.getSceneY();
//                event.consume();
//            });
//            setOnMouseReleased(event -> {     // drag is complete (places piece on grid)
//                snapToGrid();
//            });

           /* setOnMouseClicked(event -> ); (rotates pieces)

            */

//        }
//
//        /**
//         * Snap the piece to its initial position (if it is not on the grid)
//         */
//        private void snapToHome() {
//            setLayoutX(homeX);
//            setLayoutY(homeY);
//            setRotate(0);
//            piecestate[piece] = NOT_PLACED;
//        }
//
//        /**
//         * Rotate the piece by 90 degrees
//         */
//        private void rotate() {
//            setRotate((getRotate() + 90) % 360);
//            setPosition();
//            updateAndCheck();
        }












        }

    private void makePieces() {
        pieces.getChildren().clear();
        for (char m = 'a'; m <= 'h'; m++) {
            pieces.getChildren().add(new DraggablePiece(m));
        }
    }

    private void newGame(){
        makePieces();
    }




    // FIXME Task 7: Implement a basic playable Twist Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement starting placements

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TwistGame Board");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        javafx.scene.shape.Rectangle background = new Rectangle(sceneOffsetX,sceneOffsetY,480,240);
        background.setFill(Color.GRAY);
        newGame();

//        root.getChildren().add(controls);
//        root.getChildren().add(background);
        root.getChildren().add(pieces);
//        root.getChildren().add(pegs);
         grid();

        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private void makeBoard() {
        board.getChildren().clear();

        ImageView baseboard = new ImageView();
        baseboard.setImage(new Image(BASEBOARD_URI));
        baseboard.setFitWidth(MAIN_PANEL_WIDTH);
        baseboard.setFitHeight(MAIN_PANEL_HEIGHT);
        baseboard.setLayoutX(MAIN_PANEL_X);
        board.getChildren().add(baseboard);

        board.toBack();
    }

    //use grid pane perhaps?
    public void grid() {
        Rectangle tile = new Rectangle(300, 200, 60, 60);
        int a = 300;
        int b = 250;
        int xstep = 60;
        int ystep = 60;
        tile.setFill(null);
        tile.setStroke(Color.BLACK);

        for (int i = 0; i < 8 ; i++) {
            tile = new Rectangle (a+xstep*i, b, 60, 60);
            root.getChildren().add(tile);
            tile.setFill(null);
            tile.setStroke(Color.BLACK);
            for (int j = 0; j < 4 ; j++) {
                tile = new Rectangle (a, b+ystep*j, 60, 60);
                root.getChildren().add(tile);
                tile.setFill(null);
                tile.setStroke(Color.BLACK);
            }

        }
    }



}