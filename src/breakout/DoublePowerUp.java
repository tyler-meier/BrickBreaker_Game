package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Bounds;


/**
 * A PowerUp that doubles the score during the game
 *
 * @author Tyler Meier, tkm22
 */
public class DoublePowerUp extends Main {
    public static final String DOUBLE_POINTS = "pointspower.gif";
    public static final int POWER_SPEED = 60;
    public static final int DPU_WIDTH = 20;
    public static final int DPU_HEIGHT = 20;

    private ImageView myDoublePowerUp;
    /**
     * The constructor class for the power up that doubles the score
     * Sets it to an image to be used and its position
     * @param width, SIZE
     * @param height, SIZE
     */
    public DoublePowerUp(int width, int height){
        Image myDPUImage = new Image(getClass().getClassLoader().getResourceAsStream(DOUBLE_POINTS));
        myDoublePowerUp = new ImageView(myDPUImage);
        myDoublePowerUp.setFitWidth(DPU_WIDTH);
        myDoublePowerUp.setFitHeight(DPU_HEIGHT);
        myDoublePowerUp.setX(width/2 - myDoublePowerUp.getBoundsInLocal().getWidth() / 2);
        myDoublePowerUp.setY(height/2 - myDoublePowerUp.getBoundsInLocal().getHeight() / 2);

    }

    /**
     * Boolean method that tells when the power up hits the paddle
     * @param myPaddle
     * @return true or false depending on if the power up hits the paddle
     */
    public boolean hitPaddle (Paddle myPaddle){
        Bounds ivPower = myDoublePowerUp.getBoundsInParent();
        Bounds ivPaddle = myPaddle.getView().getBoundsInParent();
        if(ivPower.intersects(ivPaddle)){
            return true;
        }
        return false;
    }

    /**
     * allows the power up to move down towards the paddle
     * @param elapsedTime
     */
    public void move (double elapsedTime) {
        myDoublePowerUp.setY(myDoublePowerUp.getY() + POWER_SPEED * elapsedTime);
    }

    /**
     * removes the image of the power up when it hits the paddle
     * @param power
     */
    public void remove(ImageView power){
        power.setImage(null);
    }

    /**
     * @return internal view of bouncer to interact with other JavaFX methods.
     */
    public ImageView getView () {
        return myDoublePowerUp;
    }

}
