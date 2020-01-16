package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Main extends Application {
    public static final String TITLE = "Tyler's Breakout";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;


    private Scene myScene;
    private Bouncer myBouncer;
    private Paddle myPaddle;
    private Brick1 myBrick1;


    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        // attach scene to the stage and display it
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    private Scene setupGame (int width, int height, Paint background) {
        // create one top level collection to organize the things in the scene
        Group root = new Group();

        // make some shapes and set their properties
        myBouncer = new Bouncer(width, height);
        myPaddle = new Paddle(width, height);
        myBrick1 = new Brick1(width, height);

        // order added to the group is the order in which they are drawn
        root.getChildren().add(myBouncer.getView());
        root.getChildren().add(myPaddle.getView());
        root.getChildren().add(myBrick1.getView());

        // create a place to see the shapes
        Scene scene = new Scene(root, width, height, background);

        //respond to input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

        return scene;
    }

    private void step (double elapsedTime) {
        // update "actors" attributes
        myBouncer.move(elapsedTime);

        //bounce off of all walls except bottom and paddle
        myBouncer.bounce(myScene.getWidth(), myScene.getHeight(), myPaddle);

        //

    }

    private void handleKeyInput(KeyCode code){
        myPaddle.move(code, myScene.getWidth());
    }

    public static void main (String[] args) { launch(args); }
}
