package items;

import javax.swing.*;

public class Item {
    ItemType item;
    JLabel label;
    String imageName;

    public Item(ItemType item) {
        this.item = item;
        this.imageName = item.getName();
        System.out.println("imageName" + this.imageName);
        this.label = new JLabel();
    }

    private void setImage(String movement){
        // Load the GIF image
        ImageIcon icon = new ImageIcon("./src/images/" + this.imageName +"/" + this.imageName + "_" + movement + ".gif");
        this.label.setIcon(icon);
    }
}

