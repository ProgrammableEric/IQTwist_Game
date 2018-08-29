package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A very simple viewer for piece placements in the twist game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 750;
    private static final int VIEWER_HEIGHT = 500;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group pieces = new Group();
    private final Group pegs = new Group();
    TextField textField;

    /* Define a drop shadow effect that we will appy to tiles */
    private static DropShadow dropShadow;

    /* Static initializer to initialize dropShadow */
    {
        dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0);
        dropShadow.setOffsetY(2.0);
        dropShadow.setColor(Color.color(0, 0, 0, .4));
    }

    class Piece extends ImageView{
        int piece;    // ????????????? what for ????????????????????
        /** constructor for a playing piece
         *
         */

        // Set images for each piece
        Piece (char piece, char column, char row, char orientation){

            int posX;  int posY;

            if (piece >= 'i') {
                throw new IllegalArgumentException("Bad tile: \"" + piece + "\"");
            }
            setImage(new Image(Viewer.class.getResource(URI_BASE + piece + ".png").toString()));
            this.piece = piece - 'a';
            switch (piece){
                case 'a': case 'b': case 'd': case 'f':
                    setFitHeight(2*SQUARE_SIZE);
                    setFitWidth(3*SQUARE_SIZE);
                    setEffect(dropShadow); break;
                case 'c':
                    setFitHeight(SQUARE_SIZE);
                    setFitWidth(4*SQUARE_SIZE);
                    setEffect(dropShadow);
                case 'e':
                    setFitHeight(2*SQUARE_SIZE);
                    setFitWidth(2*SQUARE_SIZE);
                    setEffect(dropShadow);
                case 'g':
                    setFitHeight(3*SQUARE_SIZE);
                    setFitWidth(3*SQUARE_SIZE);
                    setEffect(dropShadow);
                case 'h':
                    setFitHeight(SQUARE_SIZE);
                    setFitWidth(3*SQUARE_SIZE);
                    setEffect(dropShadow);
            }

            // rotation of the image
            if ( orientation<=3){
                setRotate(orientation*90);
            }else if ( orientation<=7){
                setScaleY(-1);
                setRotate((orientation-4) * 90);
            }

            // offsetting the piece
            posX = offsetX(column);
            posY = offsetY(row);


            setLayoutX(posX);
            setLayoutY(posY);
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
            posX = offsetX(column);
            posY = offsetY(row);

            setLayoutX(posX);
            setLayoutY(posY);
        }
    }


    int offsetY (char row){
        int sceneOffsetY = 150;
        return sceneOffsetY + (row - 'A')*SQUARE_SIZE + SQUARE_SIZE/2;
    }

    int offsetX (char column){
        int sceneOffsetX = 135;
        return sceneOffsetX + (column - '0')*SQUARE_SIZE + SQUARE_SIZE/2;
    }
    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */
    void makePlacement(String placement) {

        char pieceType;
        char column;
        char row;
        char orientation;

        for (int i = 0; i < placement.length();i += 4){
            pieceType = placement.charAt(i);
            column = placement.charAt(i+1);
            row = placement.charAt(i+2);
            orientation = placement.charAt(i+3);

            if (pieceType >= 'a' && pieceType <= 'h'){
                pieces.getChildren().add(new Piece(pieceType,column,row,orientation));
            } else if (pieceType >= 'i' && pieceType <= 'l'){
                pegs.getChildren().add(new Peg(pieceType,column,row));
            }

        }
        // FIXME Task 4: implement the simple placement viewer
    }



    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField ();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TwistGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);
        root.getChildren().add(pieces);
        root.getChildren().add(pegs);


        makeControls();
        makePlacement("a7A7b6A7c1A3d2A6e2C3f3C4g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0");    // run placement

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
