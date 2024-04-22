package caracters;

import game.Game;
import map.GameMap;

import javax.swing.*;
import java.awt.*;

/**
 * this class is a type of abstraction for label with a gif that alows any child class to
 * interact and update the position of a label with a set of methods, requirements are have gjfs inside
 * ./src/images/{imagename} with name {imagename}-up.gif, {imagename}-down.gif, {imagename}-left.gif, {imagename}-right.gif
 */
public abstract class Character {
    private int lifes;
    private int movementSpeed;

    private JLabel label;
    private int sizePx;
    private String imageName;
    private GameMap gameMap;
    private Game game;
    private Rectangle position;

    public Rectangle getPosition(){
        return label.getBounds();
    }

    public void setPosition(Rectangle r){
        this.label.setBounds(r);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Character(int lifes, int movementSpeed, String imageName, GameMap gameMap, Rectangle startingPosition, int sizePx, Game game) {
        this.game = game;
        this.gameMap = gameMap;
        this.lifes = lifes;
        this.movementSpeed = movementSpeed;
        this.sizePx = sizePx;
        this.imageName = imageName;
        this.initLabel(startingPosition);
    }

    private void initLabel(Rectangle startingPosition){
        // Create a JLabel with the GIF image
        label = new JLabel();
        label.setBounds(startingPosition.x,startingPosition.y,sizePx,sizePx);
        Game.frame.getContentPane().add(label);
        this.setImage("up");
    }


    public Game getGame() {
        return game;
    }

    private void setImage(String movement){
        // Load the GIF image
        ImageIcon icon = new ImageIcon("./src/images/" + this.imageName +"/" + this.imageName + "_" + movement + ".gif");
        this.label.setIcon(icon);
    }

//    private void setImage(String movement) {
//        try {
//            // Load the GIF image using ImageIO
//            Image image = ImageIO.read(new File("./src/images/" + this.imageName + "/" + this.imageName + "_" + movement + ".gif"));
//
//            // Scale the image to fit within the label's size
//            Image scaledImage = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
//
//            // Create an ImageIcon from the scaled image
//            ImageIcon icon = new ImageIcon(scaledImage);
//
//            // Set the icon to the label
//            label.setIcon(icon);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void up(){
        this.setImage("up");
        moveLabel(0,-this.movementSpeed);
    }
    public void down(){
        this.setImage("down");
        moveLabel(0,this.movementSpeed);
    }
    public void right(){
        this.setImage("right");
        moveLabel(this.movementSpeed,0);
    }
    public void left(){
        this.setImage("left");
        moveLabel(-this.movementSpeed,0);
    }

    /**
     * returns true if character is collisioning with a wall
     * @param x
     * @param y
     * @return
     */
    private void moveLabel(int x, int y ){
        Rectangle actulRectangle = this.label.getBounds();
        actulRectangle.setLocation(actulRectangle.x + x * 2,actulRectangle.y + y * 2);
        if(!gameMap.positionIsTouchingWall(actulRectangle)){
            this.label.setBounds(actulRectangle);
            this.position = actulRectangle;
        }else{
        }
    }

    public int getLifes() {
        return lifes;
    }

    public void removeLife(){
        this.lifes -= 1;
        this.gameMap.getTopBar().setLifes(String.valueOf(this.lifes));
        if(this.lifes == 0){
            game.endGame();
        };
    }
}
