package breakout;

import java.util.Random;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;


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
    public void resetBouncer(int width, int height){
        myBouncer.setX(width / 2 - myBouncer.getBoundsInLocal().getWidth() / 2);
        myBouncer.setY(height - 100);
    }
    public void addLife(){
        lives ++;
    }
    public Integer lives(){
        return this.lives;
    }
    public void speedUp(){
        myVelocity = new Point2D(getRandomInRange(BOUNCER_MINIMUM_HIGHSPEED, BOUNCER_MAXIMUM_HIGHSPEED),
                getRandomInRange(BOUNCER_MINIMUM_HIGHSPEED, BOUNCER_MAXIMUM_HIGHSPEED));
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
    public void bounce (int screenWidth, int screenHeight) {
        // collide all bouncers against the walls except bottom
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
    public void hitPaddle(Paddle myPaddle){
        Bounds ivBouncer = myBouncer.getBoundsInParent();
        Bounds ivPaddle = myPaddle.getView().getBoundsInParent();
        if(ivBouncer.intersects(ivPaddle)) {
            myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
        }
    }

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

    // Returns an "interesting", non-zero random value in the range (min, max)
    private int getRandomInRange (int min, int max) {
        return min + dice.nextInt(max - min) + 1;
    }






}
