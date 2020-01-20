package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
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

/**
 * Breakout: a brick breaker game
 *
 * @author Tyler Meier, tkm22
 */

public class Main extends Application {
    public static final String TITLE = "Tyler's Breakout";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;
    public static final int SCORE_PER_BRICK = 100;
    private static final int DOUBLE_PU_NUM = 40;


    private Scene myScene, startScene, rulesScene;
    private Stage myStage;
    private Bouncer myBouncer;
    private Paddle myPaddle;
    private Brick myBrick;
    private DoublePowerUp myDoublePowerUp;
    private ExtendPaddlePowerUp myExtendedPad;
    private ArrayList<Brick> myBricks;
    private Button rulesButton, startGameButton;
    private int level = 1;
    private int score = 0;
    private int DoubleUpAt = 35;
    private int ExtendAt = 30;
    private int turnOnDoubleUp = 0;
    private int turnOnExtend = 0;


    /**
     * Initializes what will be displayed and how to display it
     * @param stage
     * @throws FileNotFoundException
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

    /**
     * Sets up the start scene, or the scene that first shows up
     * at the beginning of the game, that introduces the game and has a button
     * to take you to the next scene
     * @param width, SIZE
     * @param height, SIZE
     * @param background
     * @return the scene to be used
     */
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

    /**
     * Sets up the rules scene, or the scene after the start scene, that explains
     * the rules for the game and has a button to press for the next scene
     * @param width, SIZE
     * @param height, SIZE
     * @param background
     * @return the scene to be used
     */
    private Scene setupRules (int width, int height, Paint background){
        Group root = new Group();

        Text text = new Text();
        text.setText("Here are the rules for the breakout game:\n\n" +
                "1. The goal of the game is to break all of the bricks on each level by using a paddle to hit a ball. " +
                "The ball will 'break' each brick that it touches.\n" +
                "2. To control the paddle, use the left and right arrow keys.\n" +
                "3. There are three levels in this game. Each level is harder than the previous one. " +
                "In later levels, there are powerups that may help you to win the game.\n" +
                "4. The bricks may take 1, 2, 3 or 4 hits to break.\n" +
                "5. You will have three lives at the start of the game. " +
                "If the ball hits the bottom of the screen and not the paddle, you will lose a life. If you lose all three, the game is over.\n\n" +
                "                                  GOOD LUCK!!!");
        text.setStyle("-fx-font: 16 arial;");
        text.setX(3);
        text.setY(40);

        startGameButton = new Button("Click to start the game!");
        startGameButton.setLayoutX(120);
        startGameButton.setLayoutY(350);

        Scene scene2 = new Scene(root, width, height, background);
        text.wrappingWidthProperty().bind(scene2.widthProperty().subtract(10));

        root.getChildren().add(text);
        root.getChildren().add(startGameButton);

        return scene2;
    }

    /**
     * Creates the game's main playing scene: which shapes/images will be in it and
     * some of their starting properties
     * @param width
     * @param height
     * @param background
     * @param level
     * @return the scene to be used
     * @throws FileNotFoundException
     */
    private Scene setupGame (int width, int height, Paint background, int level) throws FileNotFoundException {
        // create one top level collection to organize the things in the scene
        Group root = new Group();

        myDoublePowerUp = new DoublePowerUp(width, height);
        myExtendedPad = new ExtendPaddlePowerUp(width, height);
        myBouncer = new Bouncer(width, height);
        myPaddle = new Paddle(width, height);
        if (level == 2) {
            myPaddle.mediumPaddle();
            myBouncer.speedUp();
        }
        if (level == 3) {
            myPaddle.smallPaddle();
            myBouncer.speedUp();
        }
        myBrick = new Brick();
        myBrick.createArrayBrick(level);
        myBricks = myBrick.setPositionBrick();
        for (int i = 0; i < myBricks.size(); i++) {
            Brick thisBrick = myBricks.get(i);
            root.getChildren().add(thisBrick.getView1());
            root.getChildren().add(thisBrick.getView2());
            root.getChildren().add(thisBrick.getView3());
            root.getChildren().add(thisBrick.getView4());
        }

        Text text = new Text();
        text.setText("Score: " + score);
        text.setStyle("-fx-font: 12 arial;");
        text.setX(100);
        text.setY(10);

        Text text2 = new Text();
        text2.setText("Lives Left: " + myBouncer.lives());
        text2.setStyle("-fx-font: 12 arial;");
        text2.setX(300);
        text2.setY(10);

        // order added to the group is the order in which they are drawn
        root.getChildren().add(myBouncer.getView());
        root.getChildren().add(myPaddle.getView());
        root.getChildren().add(myDoublePowerUp.getView());
        root.getChildren().add(myExtendedPad.getView());
        root.getChildren().add(text);
        root.getChildren().add(text2);

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

    /**
     * Sets up the end scene, or the scene that pops up if you win
     * the game
     * @param width, SIZE
     * @param height, SIZE
     * @param background
     * @return the scene to be used
     */
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

    /**
     * Sets up the scene that is displayed if you lose all of your lives
     * and you lose the game
     * @param width
     * @param height
     * @param background
     * @return the scene to be used
     */
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

    /**
     * updates all of the attributes of the game, basically sets all of the
     * things that happen in the game and what to do if things change
     * @param elapsedTime
     * @throws FileNotFoundException
     */
    private void step (double elapsedTime) throws FileNotFoundException {
        // update "actors" attributes
        myBouncer.move(elapsedTime);

        //bounce off of all walls except bottom and paddle
        myBouncer.bounce(SIZE, SIZE);
        myBouncer.hitPaddle(myPaddle);

        //if lives = 0, end game
        if (myBouncer.lives() == 0){
            myScene = setupGameOver(SIZE, SIZE, BACKGROUND);
            myStage.setScene(myScene);
        }

        for (int i=0; i < myBricks.size(); i++){
            Brick thisBrick = myBricks.get(i);
            if (myBouncer.hitBrick(thisBrick)){
                score += SCORE_PER_BRICK;
                thisBrick.brickRemove(thisBrick.getView1());
                thisBrick.brickRemove(thisBrick.getView2());
                thisBrick.brickRemove(thisBrick.getView3());
                thisBrick.brickRemove(thisBrick.getView4());
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
            if (myBricks.size() == DoubleUpAt && (level > 1)){
                turnOnDoubleUp = 1;
            }
            if (myBricks.size() == ExtendAt && level == 3){
                turnOnExtend = 1;
            }

        }
        if (turnOnDoubleUp == 1){
            myDoublePowerUp.move(elapsedTime);
        }
        if (myDoublePowerUp.hitPaddle(myPaddle)){
            turnOnDoubleUp = 0;
            DoublePowerUp thisPower = myDoublePowerUp;
            thisPower.remove(thisPower.getView());
            score = score * 2;
        }
        if (turnOnExtend == 1){
            myExtendedPad.move(elapsedTime);
        }
        if (myExtendedPad.hitPaddle(myPaddle)){
            turnOnExtend = 0;
            ExtendPaddlePowerUp thisPower1 = myExtendedPad;
            thisPower1.remove(thisPower1.getView());
            myPaddle.normalPaddle();
        }

    }

    /**
     * handles what happens when certain keys are pressed, contains
     * all of the cheat codes that were added to the game
     * @param code
     * @throws FileNotFoundException
     */
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

    /**
     * starts the game
     * @param args
     */
    public static void main (String[] args) { launch(args); }
}
