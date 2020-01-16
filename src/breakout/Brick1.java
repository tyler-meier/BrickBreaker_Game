package breakout;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Brick1 extends Main {
    public static final String BRICK_IMAGE1 = "brick1.gif";
    public static final double BRICK_WIDTH1 = 50;
    public static final double BRICK_HEIGHT1 = 25;

    private ImageView myBrick1;

    public Brick1(int width, int height) throws FileNotFoundException {


        myBrick1 = new ImageView(BRICK_IMAGE1);
        myBrick1.setFitWidth(BRICK_WIDTH1);
        myBrick1.setFitHeight(BRICK_HEIGHT1);


        File file1 = new File("/Users/tylermeier/Documents/comp308 workspace/game_tkm22/resources/lvl01");
        ArrayList<String> positions = new ArrayList<String>();

        try {
            Scanner scn = new Scanner(file1);
            while (scn.hasNextLine()) {
                positions.add(scn.next());
            }
            scn.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

        for (int i = 0; i < positions.size(); i += 3){
            myBrick1.setX(Double.parseDouble(positions.get(i)));
            myBrick1.setY(Double.parseDouble(positions.get(i+1)));
        }

    }
    public Node getView () {
        return myBrick1;
    }
}
