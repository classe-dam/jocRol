package map;

import items.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TopBar {
    JLabel lifes;
    JLabel gold;
    JLabel items;

    JPanel itemsPanel;

    ArrayList<Item> itemsArray;
    TopBar(JFrame frame, int lifes, int gold){
        this.itemsArray = new ArrayList<>();
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setSize(new Dimension(300,30));

        //lifes panel
        this.lifes = new JLabel();
        this.lifes.setText(String.valueOf(lifes));
        this.lifes.setSize(10,30);
        JLabel imgLifesLabel = new JLabel();
        this.setImage("heart", imgLifesLabel);
        panel.add(this.lifes);
        panel.add(imgLifesLabel);

        //gold panel
        this.gold = new JLabel();
        this.gold.setText(String.valueOf(gold));
        this.gold.setSize(10,30);
        JLabel imgGoldLabel = new JLabel();
        imgGoldLabel.setSize(32,32);
        this.setImage("dollar",imgGoldLabel);
        panel.add(this.gold);
        panel.add(imgGoldLabel);

        //items panel
        itemsPanel = new JPanel();
        itemsPanel.setBackground(Color.GRAY);
        itemsPanel.setSize(100,30);
        this.renderItems();

        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setSize(300,30);
        outerPanel.add(panel, BorderLayout.CENTER); // Align panel to the left
        outerPanel.add(itemsPanel, BorderLayout.EAST); // Align itemsPanel to the right

        frame.getContentPane().add(BorderLayout.NORTH,outerPanel);
    }



    public void addItem(Item item){
        this.itemsArray.add(item);
        this.renderItems();
    }

    public void removeItem(Item item){
        this.itemsArray.remove(item);
        this.renderItems();
    }

    private void renderItems(){
        // Clear the itemsPanel
        this.itemsPanel.removeAll();

        // Iterate through itemsArray and add new items to itemsPanel
        for (Item item: this.itemsArray) {
            JLabel imgLabel = new JLabel();
            imgLabel.setSize(32, 32);
            this.setImage(item.getImageName(), imgLabel);
            this.itemsPanel.add(imgLabel);
        }

        // After adding all items, revalidate and repaint the itemsPanel
        this.itemsPanel.revalidate();
        this.itemsPanel.repaint();

    }

    private void setImage(String imageName, JLabel label){
        // Load the image
        String imagePath = "./src/images/dungeon/" + imageName + ".png";
        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Resize the image if necessary
        Image image = imageIcon.getImage().getScaledInstance(32 /2, 32 /2, Image.SCALE_DEFAULT);
        ImageIcon scaledImageIcon = new ImageIcon(image);

        label.setSize(32, 32);
        label.setIcon(scaledImageIcon);
    }

    public void setLifes(String lifes){
        this.lifes.setText(lifes);
    }

    public void setGold(String gold){
        this.gold.setText(gold);
    }
}
