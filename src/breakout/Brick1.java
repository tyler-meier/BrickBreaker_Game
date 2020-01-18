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
    public ArrayList<String> positions = new ArrayList<String>();
    public ArrayList<Brick1> brickArray = new ArrayList<Brick1>();
    private ImageView myBrick1;

    public Brick1() {
        Image brickImage = new Image(getClass().getClassLoader().getResourceAsStream(BRICK_IMAGE1));
        myBrick1 = new ImageView(brickImage);
        myBrick1.setFitWidth(BRICK_WIDTH1);
        myBrick1.setFitHeight(BRICK_HEIGHT1);
    }

    public void createArrayBrick() {
        File file1 = new File("/Users/tylermeier/Documents/comp308 workspace/game_tkm22/resources/lvl01");

        try {
            Scanner scn = new Scanner(file1);
            while (scn.hasNextLine()) {
                String nextline = scn.nextLine();
                positions.add(nextline);
            }
            scn.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

    }

    public ArrayList<Brick1> setPositionBrick(){

        for (int i = 0; i < positions.size(); i ++){
            Brick1 b = new Brick1();
            String temp = positions.get(i);
            String[] splitTemp = temp.split(" ");
            int x = Integer.parseInt(splitTemp[0]);
            int y = Integer.parseInt(splitTemp[1]);
            b.myBrick1.setX(x);
            b.myBrick1.setY(y);
            brickArray.add(b);

        }
        return brickArray;
    }
    public void brickRemove(ImageView thisBrick){
        thisBrick.setImage(null);

    }

    public ImageView getView () {
        return this.myBrick1;
    }
}
