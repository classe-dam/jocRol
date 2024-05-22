package caracters;

import game.Game;
import map.GameMap;
import org.xml.sax.Locator;

import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * this class is a type of abstraction for label with a gif that alows any child class to
 * interact and update the position of a label with a set of methods, requirements are have gjfs inside
 * ./src/images/{imagename} with name {imagename}-up.gif, {imagename}-down.gif, {imagename}-left.gif, {imagename}-right.gif
 */
public abstract class Character {
    protected int lifes;
    protected int movementSpeed;

    protected JLabel label;
    protected int sizePx;
    protected String imageName;
    protected GameMap gameMap;
    protected Game game;
    protected Rectangle position;

    public Rectangle getPosition(){
        return label.getBounds();
    }

    public void setPosition(Rectangle r){
        this.label.setBounds(r);
    }
    public void setLocation(Point p){
        this.label.setLocation(p);
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
        ImageIcon resizedIcon = getScaledIcon(icon, this.sizePx, this.sizePx);
        this.label.setIcon(resizedIcon);
    }

    public static ImageIcon getScaledIcon(ImageIcon icon, int width, int height) {
        // Get the image from the ImageIcon
        Image image = icon.getImage();

        // Create a new BufferedImage with the desired width and height
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Draw the original image onto the resized image
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        // Create a new ImageIcon from the resized image
        return new ImageIcon(resizedImage);
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

    public void increaseLifes(int value){
        this.lifes += value;
        this.gameMap.getTopBar().setLifes(String.valueOf(this.lifes));
    }

    public JLabel getLabel() {
        return label;
    }
}
