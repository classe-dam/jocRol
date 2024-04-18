package caracters;

import game.Game;
import map.GameMap;

import javax.swing.*;
import java.awt.*;

public abstract class Caracter {
    int lifes;
    int movementSpeed;

    JLabel label;
    String imageName;
    GameMap gameMap;

    public Caracter(int lifes, int movementSpeed, String imageName, GameMap gameMap) {
        this.gameMap = gameMap;
        this.lifes = lifes;
        this.movementSpeed = movementSpeed;
        this.imageName = imageName;
        this.initLabel();
    }

    private void initLabel(){
        // Create a JLabel with the GIF image
        label = new JLabel();
        Rectangle startingPosition = gameMap.getStartingPosition();
        label.setBounds(startingPosition.x,startingPosition.y,gameMap.getPxPerCell(),gameMap.getPxPerCell());
        Game.frame.getContentPane().add(label);
        this.setImage("up");
    }

    private void setImage(String movement){
        // Load the GIF image
        ImageIcon icon = new ImageIcon("./src/images/" + this.imageName +"/" + this.imageName + "_" + movement + ".gif");
        this.label.setIcon(icon);
    }

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
    }public void left(){
        this.setImage("left");
        moveLabel(-this.movementSpeed,0);
    }

    private void moveLabel(int x, int y ){
        Rectangle actulRectangle = this.label.getBounds();
        actulRectangle.setLocation(actulRectangle.x + x * 2,actulRectangle.y + y * 2);
        if(!gameMap.isCharacterTouchingWall(actulRectangle))
            this.label.setBounds(actulRectangle);
    }

    
}
