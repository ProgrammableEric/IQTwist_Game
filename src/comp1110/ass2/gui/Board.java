package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Board extends Application {
    private static final int WINDOW_WIDTH = 933;
    private static final int WINDOW_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group pieces = new Group();
    private final Group pegs = new Group();

    // FIXME Task 7: Implement a basic playable Twist Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement starting placements

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements









    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Twist Game");
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    }
}
