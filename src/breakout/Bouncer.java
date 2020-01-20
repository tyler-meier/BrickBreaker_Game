package breakout;

import java.util.Random;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * This class creates the bouncer(ball) and all of the properties
 * of it are in this class
 *
 * @author Tyler Meier, tkm22
 */
public class Bouncer extends Main {
    public static final String BOUNCER_IMAGE = "ball.gif";
    public static final int BOUNCER_MINIMUM_SPEED = 70;
    public static final int BOUNCER_MAXIMUM_SPEED = 90;
    public static final int BOUNCER_MINIMUM_HIGHSPEED = 90;
    public static final int BOUNCER_MAXIMUM_HIGHSPEED = 110;
    public static final int BOUNCER_WIDTH = 20;
    public static final int BOUNCER_HEIGHT = 20;


    private Random dice = new Random();
    private ImageView myBouncer;
    private Point2D myVelocity;
    private Integer lives = 3;

    /**
     * constructor for the bouncer, creates the bouncer and sets its size
     * sets the speed to a velocity for a range
     * sets its starting position
     * @param width
     * @param height
     */
    public Bouncer (int width, int height) {
        myBouncer = new ImageView(BOUNCER_IMAGE);
        myBouncer.setFitWidth(BOUNCER_WIDTH);
        myBouncer.setFitHeight(BOUNCER_HEIGHT);

        // turn speed into velocity that can be updated on bounces
        myVelocity = new Point2D(getRandomInRange(BOUNCER_MINIMUM_SPEED, BOUNCER_MAXIMUM_SPEED),
                getRandomInRange(BOUNCER_MINIMUM_SPEED, BOUNCER_MAXIMUM_SPEED));

        // x and y represent the top left corner, so center it in window
        myBouncer.setX(width / 2 - myBouncer.getBoundsInLocal().getWidth() / 2);
        myBouncer.setY(height - 100);
    }

    /**
     * resets the bouncer to its original position
     * @param width
     * @param height
     */
    public void resetBouncer(int width, int height){
        myBouncer.setX(width / 2 - myBouncer.getBoundsInLocal().getWidth() / 2);
        myBouncer.setY(height - 100);
    }

    /**
     * adds an extra life to the player
     */
    public void addLife(){
        lives ++;
    }

    /**
     * @returns the current lives of the player
     */
    public Integer lives(){
        return this.lives;
    }

    /**
     * Speeds up the bouncer during level 2 and 3
     */
    public void speedUp(){
        myVelocity = new Point2D(getRandomInRange(BOUNCER_MINIMUM_HIGHSPEED, BOUNCER_MAXIMUM_HIGHSPEED),
                getRandomInRange(BOUNCER_MINIMUM_HIGHSPEED, BOUNCER_MAXIMUM_HIGHSPEED));
    }

    /**
     * Move by taking one step based on its velocity.
     *
     * Note, elapsedTime is used to ensure consistent speed across different machines.
     * @param elapsedTime
     */
    public void move (double elapsedTime) {
        myBouncer.setX(myBouncer.getX() + myVelocity.getX() * elapsedTime);
        myBouncer.setY(myBouncer.getY() + myVelocity.getY() * elapsedTime);
    }

    /**
     * Allows the bouncer to bounce off all of the walls except for the bottom one
     * @param screenWidth
     * @param screenHeight
     */
    public void bounce (int screenWidth, int screenHeight) {
        if (myBouncer.getX() < 0 || myBouncer.getX() > screenWidth - myBouncer.getBoundsInLocal().getWidth()) {
            myVelocity = new Point2D(-myVelocity.getX(), myVelocity.getY());
        }
        if (myBouncer.getY() < 30) {
            myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
        }
        if (myBouncer.getY() > screenHeight){
            lives --;
            resetBouncer(screenWidth, screenHeight);
        }
    }

    /**
     * If the bouncer at any point hits the paddle, change the direction of the bouncer
     * to bounce back up towards the bricks
     * @param myPaddle
     */
    public void hitPaddle(Paddle myPaddle){
        Bounds ivBouncer = myBouncer.getBoundsInParent();
        Bounds ivPaddle = myPaddle.getView().getBoundsInParent();
        if(ivBouncer.intersects(ivPaddle)) {
            myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
        }
    }

    /**
     * Boolean method that tells when the ball hits any of the bricks, and then changes direction if it does
     * @param myBrick
     * @return true if it hits one of the bricks
     */
    public boolean hitBrick(Brick myBrick){
        Bounds ivBouncer = myBouncer.getBoundsInParent();
        Bounds ivBrick1 = myBrick.getView1().getBoundsInParent();
        Bounds ivBrick2 = myBrick.getView2().getBoundsInParent();
        Bounds ivBrick3 = myBrick.getView3().getBoundsInParent();
        Bounds ivBrick4 = myBrick.getView4().getBoundsInParent();
        if(ivBouncer.intersects(ivBrick1)){
            myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
            return true;
        }
        else if(ivBouncer.intersects(ivBrick2)){
            myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
            return true;
        }
        else if(ivBouncer.intersects(ivBrick3)){
            myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
            return true;
        }
        else if(ivBouncer.intersects(ivBrick4)){
            myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
            return true;
        }
        return false;
    }

    /**
     * Returns internal view of bouncer to interact with other JavaFX methods.
     */
    public ImageView getView () {
        return myBouncer;
    }

    /**
     * Returns an "interesting", non-zero random value in the range (min, max)
     * @param min
     * @param max
     * @return random value
     */
    private int getRandomInRange (int min, int max) {
        return min + dice.nextInt(max - min) + 1;
    }






}
