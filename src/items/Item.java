package items;

import map.GameMap;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Item {
    private ItemType item;
    private JLabel label;
    private String imageName;
    private GameMap gameMap;

    public Item(ItemType item, GameMap gameMap) {
        this.gameMap = gameMap;
        this.item = item;
        this.imageName = item.getName();
        this.initLabel();
    }

    public JLabel getLabel() {
        return label;
    }

    public String getImageName() {
        return imageName;
    }

    private void initLabel(){
        this.label = new JLabel();
        this.setImage(this.item.getName(),this.label);
        this.changeToRandomPosition();
        gameMap.getFrame().getContentPane().add(label);
    }

    private void setImage(String imageName, JLabel label){
        // Load the image
        String imagePath = "./src/images/dungeon/" + imageName + ".png";
        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Resize the image if necessary
        Image image = imageIcon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);
        ImageIcon scaledImageIcon = new ImageIcon(image);

        label.setSize(32, 32);
        label.setIcon(scaledImageIcon);
    }

    public Rectangle getPosition(){
        return this.label.getBounds();
    }

    public ItemType getItem() {
        return item;
    }

    public void changeToRandomPosition(){
        Random random = new Random();
        int xposition =  random.nextInt((gameMap.getCols() * gameMap.getPxPerCell() - 100) - 100 + 1) + 100;
        int yposition =  random.nextInt((gameMap.getRows() * gameMap.getPxPerCell() - 100) - 100 + 1) + 100;
        label.setBounds(xposition,yposition,32,32);
    }

    public void hide(){
        this.label.setVisible(false);
    }

    @Override
    public String toString() {
        return "Item{" +
                "item=" + item +
                '}';
    }
}

