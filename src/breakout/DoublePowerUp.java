package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Bounds;

public class DoublePowerUp extends Main {
    public static final String DOUBLE_POINTS = "pointspower.gif";
    public static final int POWER_SPEED = 60;
    public static final int DPU_WIDTH = 20;
    public static final int DPU_HEIGHT = 20;

    private ImageView myDoublePowerUp;
    public DoublePowerUp(int width, int height){
        Image myDPUImage = new Image(getClass().getClassLoader().getResourceAsStream(DOUBLE_POINTS));
        myDoublePowerUp = new ImageView(myDPUImage);
        myDoublePowerUp.setFitWidth(DPU_WIDTH);
        myDoublePowerUp.setFitHeight(DPU_HEIGHT);
        myDoublePowerUp.setX(width/2 - myDoublePowerUp.getBoundsInLocal().getWidth() / 2);
        myDoublePowerUp.setY(height/2 - myDoublePowerUp.getBoundsInLocal().getHeight() / 2);

    }
    public boolean hitPaddle (Paddle myPaddle){
        Bounds ivPower = myDoublePowerUp.getBoundsInParent();
        Bounds ivPaddle = myPaddle.getView().getBoundsInParent();
        if(ivPower.intersects(ivPaddle)){
            return true;
        }
        return false;
    }
    public void move (double elapsedTime) {
        myDoublePowerUp.setY(myDoublePowerUp.getY() + POWER_SPEED * elapsedTime);
    }
    public void remove(ImageView power){
        power.setImage(null);
    }
    public ImageView getView () {
        return myDoublePowerUp;
    }

}
