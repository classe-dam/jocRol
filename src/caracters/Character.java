package caracters;

import game.Game;
import map.GameMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * this class is a type of abstraction for label with a gif that alows any child class to
 * interact and update the position of a label with a set of methods, requirements are have gjfs inside
 * ./src/images/{imagename} with name {imagename}-up.gif, {imagename}-down.gif, {imagename}-left.gif, {imagename}-right.gif
 */
public abstract class Character {
    int lifes;
    int movementSpeed;

    JLabel label;
    String imageName;
    GameMap gameMap;
    Rectangle position;

    public Rectangle getPosition(){
        return label.getBounds();
    }

    public Character(int lifes, int movementSpeed, String imageName, GameMap gameMap, Rectangle startingPosition) {
        this.gameMap = gameMap;
        this.lifes = lifes;
        this.movementSpeed = movementSpeed;
        this.imageName = imageName;
        this.initLabel(startingPosition);
    }

    private void initLabel(Rectangle startingPosition){
        // Create a JLabel with the GIF image
        label = new JLabel();
        label.setBounds(startingPosition.x,startingPosition.y,64,64);
        Game.frame.getContentPane().add(label);
        this.setImage("up");
    }



//    private void setImage(String movement){
//        // Load the GIF image
//        ImageIcon icon = new ImageIcon("./src/images/" + this.imageName +"/" + this.imageName + "_" + movement + ".gif");
//        this.label.setIcon(icon);
//    }

    private void setImage(String movement) {
        try {
            // Load the GIF image using ImageIO
            Image image = ImageIO.read(new File("./src/images/" + this.imageName + "/" + this.imageName + "_" + movement + ".gif"));

            // Scale the image to fit within the label's size
            Image scaledImage = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);

            // Create an ImageIcon from the scaled image
            ImageIcon icon = new ImageIcon(scaledImage);

            // Set the icon to the label
            label.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean up(){
        this.setImage("up");
        return moveLabel(0,-this.movementSpeed);
    }
    public boolean down(){
        this.setImage("down");
        return moveLabel(0,this.movementSpeed);
    }
    public boolean right(){
        this.setImage("right");
        return moveLabel(this.movementSpeed,0);
    }public boolean left(){
        this.setImage("left");
        return moveLabel(-this.movementSpeed,0);
    }

    /**
     * returns true if character is collisioning with a wall
     * @param x
     * @param y
     * @return
     */
    private boolean moveLabel(int x, int y ){
        Rectangle actulRectangle = this.label.getBounds();
        actulRectangle.setLocation(actulRectangle.x + x * 2,actulRectangle.y + y * 2);
        if(!gameMap.isCharacterTouchingWall(actulRectangle)){
            this.label.setBounds(actulRectangle);
            this.position = actulRectangle;
            return true;
        }else{
            return false;
        }
    }

    
}
