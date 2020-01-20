package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Main extends Application {
    public static final String TITLE = "Tyler's Breakout";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;


    private Scene myScene, startScene, rulesScene;
    private Stage myStage;
    private Bouncer myBouncer;
    private Paddle myPaddle;
    private Brick1 myBrick1;
    private ArrayList<Brick1> myBricks;
    private Button rulesButton, startGameButton;
    private int level = 1;
    private int score = 0;


    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) throws FileNotFoundException {
        // attach scene to the stage and display it
        myStage = stage;
        startScene = setupStart(SIZE, SIZE, BACKGROUND);
        rulesScene = setupRules(SIZE, SIZE, BACKGROUND);
        myStage.setScene(startScene);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                myStage.setScene(rulesScene);
            }
        };
        rulesButton.setOnAction(event);
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                try {
                    myScene = setupGame(SIZE, SIZE, BACKGROUND, level);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                myStage.setScene(myScene);
                KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e2 -> {
                    try {
                        step(SECOND_DELAY);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
                Timeline animation = new Timeline();
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.getKeyFrames().add(frame);
                animation.play();
            }
        };
        startGameButton.setOnAction(event2);
        myStage.setTitle(TITLE);
        myStage.show();
    }
    private Scene setupStart (int width, int height, Paint background){
        Group root = new Group();

        Text text = new Text();
        text.setText("Welcome to TYLER'S BREAKOUT GAME");
        text.setStyle("-fx-font: 21 arial;");
        text.setX(2);
        text.setY(100);

        rulesButton = new Button("Click to see rules");
        rulesButton.setLayoutX(140);
        rulesButton.setLayoutY(160);

        root.getChildren().add(rulesButton);
        root.getChildren().add(text);
        Scene scene1 = new Scene(root, width, height, background);

        return scene1;
    }
    private Scene setupRules (int width, int height, Paint background){
        Group root = new Group();

        Text text = new Text();
        text.setText("Here are the rules for the breakout game:\n\n" +
                "1. You have three lives to start the game\n" +
                "2. The goal of the game is to break all of the bricks on the screen for every level by having the ball hit each one\n" +
                "3. You must control the paddle by using the left and right arrow keys in order to hit the ball back up\n" +
                "4. If the ball hits the bottom of the screen and not the paddle, you will lose a life. If you lose all three, the game is over.");
        text.setStyle("-fx-font: 18 arial;");
        text.setY(40);

        startGameButton = new Button("Click to start the game!");
        startGameButton.setLayoutX(125);
        startGameButton.setLayoutY(300);

        Scene scene2 = new Scene(root, width, height, background);
        text.wrappingWidthProperty().bind(scene2.widthProperty().subtract(10));

        root.getChildren().add(text);
        root.getChildren().add(startGameButton);

        return scene2;
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    private Scene setupGame (int width, int height, Paint background, int level) throws FileNotFoundException {
        // create one top level collection to organize the things in the scene
        Group root = new Group();

        // make some shapes and set their properties
        myBouncer = new Bouncer(width, height);
        myPaddle = new Paddle(width, height);
        if (level == 2){
            myPaddle.mediumPaddle();
            myBouncer.speedUp();
        }
        if (level == 3){
            myPaddle.smallPaddle();
            myBouncer.speedUp();
        }
        myBrick1 = new Brick1();
        myBrick1.createArrayBrick(level);
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
        scene.setOnKeyPressed(e -> {
            try {
                handleKeyInput(e.getCode());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        return scene;
    }

    private Scene setupEnd(int width, int height, Paint background){
        Group root = new Group();

        Text text = new Text();
        text.setText("CONGRATULATIONS!!!! YOU WON THE GAME!");
        text.setStyle("-fx-font: 18 arial;");
        text.setX(2);
        text.setY(150);

        root.getChildren().add(text);

        Scene sceneEnd = new Scene(root, width, height, background);
        return sceneEnd;
    }

    private Scene setupGameOver( int width, int height, Paint background){
        Group root = new Group();

        Text text = new Text();
        text.setText("GAME OVERRRRR, sorry :/");
        text.setStyle("-fx-font: 18 arial;");
        text.setX(30);
        text.setY(150);

        root.getChildren().add(text);

        Scene sceneGameOver = new Scene(root, width, height, background);
        return sceneGameOver;

    }

    private void step (double elapsedTime) throws FileNotFoundException {
        // update "actors" attributes
        myBouncer.move(elapsedTime);

        //bounce off of all walls except bottom and paddle
        myBouncer.bounce(SIZE, SIZE, myPaddle);

        //if lives = 0, end game
        if (myBouncer.lives() == 0){
            myScene = setupGameOver(SIZE, SIZE, BACKGROUND);
            myStage.setScene(myScene);
        }

        for (int i=0; i < myBricks.size(); i++){
            Brick1 thisBrick = myBricks.get(i);
            if (myBouncer.hitBrick(thisBrick)){
                thisBrick.brickRemove(thisBrick.getView());
                myBricks.remove(thisBrick);
            }
            if (myBricks.size() == 0){
                if (level != 3){
                    level ++;
                    myScene = setupGame(SIZE, SIZE, BACKGROUND, level);
                }
                else {
                    myScene = setupEnd(SIZE, SIZE, BACKGROUND);
                }
                myStage.setScene(myScene);
            }
        }
    }

    private void handleKeyInput(KeyCode code) throws FileNotFoundException {
        myPaddle.move(code, myScene.getWidth());

        //all cheat codes below
        if (code == KeyCode.DIGIT1 || code == KeyCode.F){
            level = 1;
            myScene = setupGame(SIZE, SIZE, BACKGROUND, level);
            myStage.setScene(myScene);
        }
        if (code == KeyCode.DIGIT2){
            level = 2;
            myScene = setupGame(SIZE, SIZE, BACKGROUND, level);
            myStage.setScene(myScene);
        }
        if ((code == KeyCode.DIGIT3) || (code == KeyCode.DIGIT4) || (code == KeyCode.DIGIT5) ||
                (code == KeyCode.DIGIT6) || (code == KeyCode.DIGIT7) || (code == KeyCode.DIGIT8) || (code == KeyCode.DIGIT9)){
            level = 3;
            myScene = setupGame(SIZE, SIZE, BACKGROUND, level);
            myStage.setScene(myScene);
        }
        if (code == KeyCode.R){
            myBouncer.resetBouncer(SIZE, SIZE);
        }
        if (code == KeyCode.L){
            myBouncer.addLife();
        }
        if (code == KeyCode.W){
            myScene = setupEnd(SIZE, SIZE, BACKGROUND);
            myStage.setScene(myScene);
        }
        if (code == KeyCode.D){
            score = score * 2;
        }
    }

    public static void main (String[] args) { launch(args); }
}
