package breakout;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Paddle extends Main {
    public static final String PADDLE_IMAGE = "paddle.gif";
    public static final int PADDLE_SPEED = 25;
    public static final int SMALL_PADDLE = 30;
    public static final int MEDIUM_PADDLE = 40;

    private ImageView myPaddle;

    public Paddle(int width, int height){
        myPaddle = new ImageView(PADDLE_IMAGE);

        myPaddle.setX(width / 2 - myPaddle.getBoundsInLocal().getWidth() / 2);
        myPaddle.setY(height - 12);
    }

    public void move(KeyCode code, double screenWidth){
        if (code == KeyCode.RIGHT && !(myPaddle.getX() > screenWidth - myPaddle.getBoundsInLocal().getWidth())) {
            myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT && !(myPaddle.getX() < 0)) {
            myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
        }
    }

    public void smallPaddle(){
        myPaddle.setFitWidth(SMALL_PADDLE);
    }

    public void mediumPaddle(){
        myPaddle.setFitWidth((MEDIUM_PADDLE));
    }


    public ImageView getView () {
        return myPaddle;
    }
}
