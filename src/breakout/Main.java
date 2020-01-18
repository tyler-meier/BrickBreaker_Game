package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Main extends Application {
    public static final String TITLE = "Tyler's Breakout";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;


    private Scene myScene, startScene;
    private Stage myStage;
    private Bouncer myBouncer;
    private Paddle myPaddle;
    private Brick1 myBrick1;
    private ArrayList<Brick1> myBricks;


    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) throws FileNotFoundException {
        // attach scene to the stage and display it
        myStage = stage;
        startScene = setupStart(SIZE, SIZE, BACKGROUND);
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
        myStage.setScene(startScene);
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();

        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    private Scene setupStart (int width, int height, Paint background){
        Group root = new Group();

        Text text = new Text();
        text.setText("Welcome to the GAMMMMEEEE");
        text.setX(SIZE/2);
        text.setY(SIZE/2);

        Button seeRules = new Button("Click to see rules");

        root.getChildren().add(seeRules);
        Scene scene1 = new Scene(root, width, height, background);

        return scene1;
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    private Scene setupGame (int width, int height, Paint background) throws FileNotFoundException {
        // create one top level collection to organize the things in the scene
        Group root = new Group();

        // make some shapes and set their properties
        myBouncer = new Bouncer(width, height);
        myPaddle = new Paddle(width, height);
        myBrick1 = new Brick1();
        myBrick1.createArrayBrick();
        myBricks = myBrick1.setPositionBrick();
        for(int i=0; i < myBricks.size(); i++){
            Brick1 thisBrick = myBricks.get(i);
            root.getChildren().add(thisBrick.getView());
        }


        // order added to the group is the order in which they are drawn
        root.getChildren().add(myBouncer.getView());
        root.getChildren().add(myPaddle.getView());

        // create a place to see the shapes
        Scene scene = new Scene(root, width, height, background);

        //respond to input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

        return scene;
    }

    private void step (double elapsedTime) {
        // update "actors" attributes
        myBouncer.move(elapsedTime);

        //bounce off of all walls except bottom and paddle and bricks
        myBouncer.bounce(myScene.getWidth(), myScene.getHeight(), myPaddle);

        for (int i=0; i < myBricks.size(); i++){
            Brick1 thisBrick = myBricks.get(i);
            if (myBouncer.hitBrick(thisBrick)){
                thisBrick.brickRemove(thisBrick.getView());
                myBricks.remove(thisBrick);
            }
        }
    }

    private void handleKeyInput(KeyCode code){
        myPaddle.move(code, myScene.getWidth());

    }

    public static void main (String[] args) { launch(args); }
}
