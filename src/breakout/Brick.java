package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class creates the bricks used in each level and
 * all of their properties
 *
 * @author Tyler Meier, tkm22
 */
public class Brick extends Main {
    public static final String BRICK_IMAGE1 = "brick1.gif";
    public static final String BRICK_IMAGE2 = "brick2.gif";
    public static final String BRICK_IMAGE3 = "brick3.gif";
    public static final String BRICK_IMAGE4 = "brick4.gif";
    public static final double BRICK_WIDTH1 = 50;
    public static final double BRICK_HEIGHT1 = 25;

    public ArrayList<String> positions = new ArrayList<String>();
    public ArrayList<Brick> brickArray = new ArrayList<Brick>();
    private ImageView myBrick1, myBrick2, myBrick3, myBrick4;
    private File file1;

    /**
     * Creates the constructor for the brick, creating an imageview for each
     * different type of brick and setting it to be a different image
     */
    public Brick() {
        Image brickImage1 = new Image(getClass().getClassLoader().getResourceAsStream(BRICK_IMAGE1));
        myBrick1 = new ImageView(brickImage1);
        myBrick1.setFitWidth(BRICK_WIDTH1);
        myBrick1.setFitHeight(BRICK_HEIGHT1);

        Image brickImage2 = new Image(getClass().getClassLoader().getResourceAsStream(BRICK_IMAGE2));
        myBrick2 = new ImageView(brickImage2);
        myBrick2.setFitWidth(BRICK_WIDTH1);
        myBrick2.setFitHeight(BRICK_HEIGHT1);

        Image brickImage3 = new Image(getClass().getClassLoader().getResourceAsStream(BRICK_IMAGE3));
        myBrick3 = new ImageView(brickImage3);
        myBrick3.setFitWidth(BRICK_WIDTH1);
        myBrick3.setFitHeight(BRICK_HEIGHT1);

        Image brickImage4 = new Image(getClass().getClassLoader().getResourceAsStream(BRICK_IMAGE4));
        myBrick4 = new ImageView(brickImage4);
        myBrick4.setFitWidth(BRICK_WIDTH1);
        myBrick4.setFitHeight(BRICK_HEIGHT1);
    }

    /**
     * reads in a file depending on what level it is and creates an
     * arraylist of the positions and type of strength that the brick will be
     * @param level
     */
    public void createArrayBrick(int level) {
        if (level == 1){
            file1 = new File("/Users/tylermeier/Documents/comp308 workspace/game_tkm22/resources/lvl01");
        }
        if (level == 2){
            file1 = new File("/Users/tylermeier/Documents/comp308 workspace/game_tkm22/resources/lvl02");
        }
        if (level == 3){
            file1 = new File("/Users/tylermeier/Documents/comp308 workspace/game_tkm22/resources/lvl03");
        }

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

    /**
     * Creates an array list of all the bricks at their set positions
     * and of  their  set strengths / different colors
     * @return the arrayList
     */
    public ArrayList<Brick> setPositionBrick(){

        for (int i = 0; i < positions.size(); i ++){
            Brick b = new Brick();
            String temp = positions.get(i);
            String[] splitTemp = temp.split(" ");
            int x = Integer.parseInt(splitTemp[0]);
            int y = Integer.parseInt(splitTemp[1]);
            int strength = Integer.parseInt(splitTemp[2]);
            if (strength == 1){
                b.myBrick1.setX(x);
                b.myBrick1.setY(y);
            }
            if (strength == 2){
                b.myBrick2.setX(x);
                b.myBrick2.setY(y);
            }
            if (strength == 3){
                b.myBrick3.setX(x);
                b.myBrick3.setY(y);
            }
            if (strength == 4){
                b.myBrick4.setX(x);
                b.myBrick4.setY(y);
            }
            brickArray.add(b);

        }
        return brickArray;
    }

    /**
     * removes a brick when it is hit by the bouncer
     * @param thisBrick
     */
    public void brickRemove(ImageView thisBrick){
        thisBrick.setImage(null);
    }

    /**
     * @return internal view of first type of brick to interact with other JavaFX methods.
     */
    public ImageView getView1 () {
        return this.myBrick1;
    }

    /**
     * @return internal view of second type of brick to interact with other JavaFX methods.
     */
    public ImageView getView2 () {
        return this.myBrick2;
    }

    /**
     *
     * @return internal view of third type of brick to interact with other JavaFX methods.
     */
    public ImageView getView3 () {
        return this.myBrick3;
    }

    /**
     *
     * @return internal view of fourth type of brick to interact with other JavaFX methods.
     */
    public ImageView getView4 () {
        return this.myBrick4;
    }
}
