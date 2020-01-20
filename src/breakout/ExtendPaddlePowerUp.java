package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Bounds;

public class ExtendPaddlePowerUp {
    public static final String EXTEND_PADDLE = "sizepower.gif";
    public static final int POWER_SPEED = 60;
    public static final int EPU_WIDTH = 20;
    public static final int EPU_HEIGHT = 20;

    private ImageView myExtendedPaddle;
    public ExtendPaddlePowerUp(int width, int height){
        Image myEPPUImage = new Image(getClass().getClassLoader().getResourceAsStream(EXTEND_PADDLE));
        myExtendedPaddle = new ImageView(myEPPUImage);
        myExtendedPaddle.setFitWidth(EPU_WIDTH);
        myExtendedPaddle.setFitHeight(EPU_HEIGHT);
        myExtendedPaddle.setX(width/2 - myExtendedPaddle.getBoundsInLocal().getWidth() / 2);
        myExtendedPaddle.setY(height/2 - myExtendedPaddle.getBoundsInLocal().getHeight() / 2);
    }
    public boolean hitPaddle (Paddle myPaddle){
        Bounds ivPower = myExtendedPaddle.getBoundsInParent();
        Bounds ivPaddle = myPaddle.getView().getBoundsInParent();
        if(ivPower.intersects(ivPaddle)){
            return true;
        }
        return false;
    }
    public void move (double elapsedTime) {
        myExtendedPaddle.setY(myExtendedPaddle.getY() + POWER_SPEED * elapsedTime);
    }
    public void remove(ImageView power){
        power.setImage(null);
    }
    public ImageView getView () {
        return myExtendedPaddle;
    }
}
