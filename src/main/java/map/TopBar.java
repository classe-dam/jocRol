package map;

import game.Game;
import items.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class TopBar {
    JLabel lifes;
    JLabel gold;
    JLabel items;
    JLabel transcurredSeconds;

    JPanel itemsPanel;

    ArrayList<Item> itemsArray;

    HashSet<Item> addedItems;
    TopBar(JFrame frame, int lifes, int gold, String playerName, Game game){
        this.itemsArray = new ArrayList<>();
        this.addedItems = new HashSet<>();
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setSize(new Dimension(300,30));

        //player name
        JLabel playerNameLabel = new JLabel();
        playerNameLabel.setText(playerName);
        panel.add(playerNameLabel);

        //lifes panel
        this.lifes = new JLabel();
        this.lifes.setText(String.valueOf(lifes));
        this.lifes.setSize(10,30);
        JLabel imgLifesLabel = new JLabel();
        this.setImage("heart", imgLifesLabel);
        panel.add(imgLifesLabel);
        panel.add(this.lifes);

        //gold panel
        this.gold = new JLabel();
        this.gold.setText(String.valueOf(gold));
        this.gold.setSize(10,30);
        JLabel imgGoldLabel = new JLabel();
        imgGoldLabel.setSize(32,32);
        this.setImage("dollar",imgGoldLabel);
        panel.add(imgGoldLabel);
        panel.add(this.gold);

        //transcurred seconds
        transcurredSeconds = new JLabel();
        transcurredSeconds.setText(this.convertSecondsToTime(0));
        panel.add(transcurredSeconds);

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

    public String convertSecondsToTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        // Format the time as "minutes:seconds"
        String formattedTime = String.format("%d:%02d", minutes, seconds);
        return formattedTime;
    }



    public void addItem(Item item){
        if(!this.addedItems.contains(item)){
            this.itemsArray.add(item);
            this.addedItems.add(item);
            this.renderItems();
        }
    }

    public void removeItem(Item item){
        if(this.addedItems.contains(item)) {
            this.itemsArray.remove(item);
            this.renderItems();
            this.addedItems.remove(item);
        }
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

    public void setTranscurredTime(int seconds){
        transcurredSeconds.setText(convertSecondsToTime(seconds));
    }
}
