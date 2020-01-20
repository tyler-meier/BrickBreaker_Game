package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Bounds;

/**
 * A PowerUp that extends the length of the paddle
 *
 * @author Tyler Meier, tkm22
 */
public class ExtendPaddlePowerUp {
    public static final String EXTEND_PADDLE = "sizepower.gif";
    public static final int POWER_SPEED = 60;
    public static final int EPU_WIDTH = 20;
    public static final int EPU_HEIGHT = 20;

    private ImageView myExtendedPaddle;

    /**
     * The constructor class for the power up that extends the paddle
     * Sets it to an image to be used and its position
     * @param width, SIZE
     * @param height, SIZE
     */
    public ExtendPaddlePowerUp(int width, int height){
        Image myEPPUImage = new Image(getClass().getClassLoader().getResourceAsStream(EXTEND_PADDLE));
        myExtendedPaddle = new ImageView(myEPPUImage);
        myExtendedPaddle.setFitWidth(EPU_WIDTH);
        myExtendedPaddle.setFitHeight(EPU_HEIGHT);
        myExtendedPaddle.setX(width/2 - myExtendedPaddle.getBoundsInLocal().getWidth() / 2);
        myExtendedPaddle.setY(height/2 - myExtendedPaddle.getBoundsInLocal().getHeight() / 2);
    }

    /**
     * Boolean method that tells when the power up hits the paddle
     * @param myPaddle
     * @return true or false depending on if the power up hits the paddle
     */
    public boolean hitPaddle (Paddle myPaddle){
        Bounds ivPower = myExtendedPaddle.getBoundsInParent();
        Bounds ivPaddle = myPaddle.getView().getBoundsInParent();
        if(ivPower.intersects(ivPaddle)){
            return true;
        }
        return false;
    }

    /**
     * allows the power up to move down off towards the paddle
     * @param elapsedTime
     */
    public void move (double elapsedTime) {
        myExtendedPaddle.setY(myExtendedPaddle.getY() + POWER_SPEED * elapsedTime);
    }

    /**
     * removes the image of the power up when this is called
     * @param power
     */
    public void remove(ImageView power){
        power.setImage(null);
    }

    /**
     * @return internal view of bouncer to interact with other JavaFX methods.
     */
    public ImageView getView () {
        return myExtendedPaddle;
    }
}
