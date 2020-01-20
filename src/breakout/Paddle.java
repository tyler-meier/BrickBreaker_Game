package breakout;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * This class creates the paddle used in the game
 * and all of its properties are in this class
 *
 * @author Tyler Meier, tkm22
 */
public class Paddle extends Main {
    public static final String PADDLE_IMAGE = "paddle.gif";
    public static final int PADDLE_SPEED = 25;
    public static final int SMALL_PADDLE = 30;
    public static final int MEDIUM_PADDLE = 40;
    public static final int NORMAL_PADDLE = 55;

    private ImageView myPaddle;

    /**
     * The constructor for the  paddle,
     * sets it to an image and sets its starting position
     * @param width
     * @param height
     */
    public Paddle(int width, int height){
        myPaddle = new ImageView(PADDLE_IMAGE);

        myPaddle.setX(width / 2 - myPaddle.getBoundsInLocal().getWidth() / 2);
        myPaddle.setY(height - 12);
    }

    /**
     * allows the paddle to move when using the left and right arrow keys
     * on the keypad
     * @param code
     * @param screenWidth
     */
    public void move(KeyCode code, double screenWidth){
        if (code == KeyCode.RIGHT && !(myPaddle.getX() > screenWidth - myPaddle.getBoundsInLocal().getWidth())) {
            myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT && !(myPaddle.getX() < 0)) {
            myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
        }
    }

    /**
     * changes the paddle size to small for the third level
     */
    public void smallPaddle(){
        myPaddle.setFitWidth(SMALL_PADDLE);
    }

    /**
     * changes the paddle size to medium for the second paddle
     */
    public void mediumPaddle(){
        myPaddle.setFitWidth((MEDIUM_PADDLE));
    }

    /**
     * changes the size of the paddle back to normal if you get the power up
     * on the third level
     */
    public void normalPaddle(){
        myPaddle.setFitWidth(NORMAL_PADDLE);
    }

    /**
     * @return internal view of paddle to interact with other JavaFX methods.
     */
    public ImageView getView () {
        return myPaddle;
    }
}
