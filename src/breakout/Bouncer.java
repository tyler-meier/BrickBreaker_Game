package breakout;

import java.util.Random;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;


public class Bouncer extends Main {
    public static final String BOUNCER_IMAGE = "ball.gif";
    public static final int BOUNCER_MINIMUM_SPEED = 70;
    public static final int BOUNCER_MAXIMUM_SPEED = 90;
    public static final int BOUNCER_WIDTH = 20;
    public static final int BOUNCER_HEIGHT = 20;


    private Random dice = new Random();
    private ImageView myBouncer;
    private Point2D myVelocity;

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
     * Move by taking one step based on its velocity.
     *
     * Note, elapsedTime is used to ensure consistent speed across different machines.
     */
    public void move (double elapsedTime) {
        myBouncer.setX(myBouncer.getX() + myVelocity.getX() * elapsedTime);
        myBouncer.setY(myBouncer.getY() + myVelocity.getY() * elapsedTime);
    }

    /**
     * Bounce off the walls represented by the edges of the screen.
     */
    public void bounce (double screenWidth, double screenHeight, Paddle myPaddle) {
        // collide all bouncers against the walls except bottom
        if (myBouncer.getX() < 0 || myBouncer.getX() > screenWidth - myBouncer.getBoundsInLocal().getWidth()) {
            myVelocity = new Point2D(-myVelocity.getX(), myVelocity.getY());
        }
        if (myBouncer.getY() < 0) {
            myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
        }

        //check if hits paddle
        Bounds ivBouncer = myBouncer.getBoundsInParent();
        Bounds ivPaddle = myPaddle.getView().getBoundsInParent();
        if(ivBouncer.intersects(ivPaddle)){
            myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
        }


    }
    public boolean hitBrick(Brick1 myBrick){
        Bounds ivBouncer = myBouncer.getBoundsInParent();
        Bounds ivBrick1 = myBrick.getView().getBoundsInParent();
        if(ivBouncer.intersects(ivBrick1)){
            myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
            return true;
        }
        return false;
    }

    /**
     * Returns internal view of bouncer to interact with other JavaFX methods.
     */
    public Node getView () {

        return myBouncer;
    }

    // Returns an "interesting", non-zero random value in the range (min, max)
    private int getRandomInRange (int min, int max) {
        return min + dice.nextInt(max - min) + 1;
    }






}
