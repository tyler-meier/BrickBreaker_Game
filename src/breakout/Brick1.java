package breakout;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class Brick1 extends Main {
    public static final String BRICK_IMAGE1 = "brick1.gif";
    public static final double BRICK_WIDTH1 = 50;
    public static final double BRICK_HEIGHT1 = 25;

    private ImageView myBrick1;

    public Brick1(int width, int height) /*throws FileNotFoundException*/ {

        /*File file1 = new File("/Users/tylermeier/Documents/comp308 workspace/game_tkm22/resources/lvl01");
        ArrayList<String> positions = new ArrayList<String>();
        try {
            Scanner scn = new Scanner(file1);
            while (scn.hasNextLine()) {
                positions.add(scn.next());
            }
            scn.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }

        System.out.println(positions);*/

        myBrick1 = new ImageView(BRICK_IMAGE1);
        myBrick1.setFitWidth(BRICK_WIDTH1);
        myBrick1.setFitHeight(BRICK_HEIGHT1);

        myBrick1.setX(0);
        myBrick1.setY(375);


    }

    public Node getView () {
        return myBrick1;
    }
}
