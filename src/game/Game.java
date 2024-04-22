package game;

import caracters.EnemyBot;
import caracters.*;
import items.ItemType;
import map.GameMap;
import playerkeylistener.PlayerKeyListener;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Game {
    public static JFrame frame;
    public Game(String name, int characterType){
        int rows = 25;
        int cols = 35;
        int pxPerCell = 32;
        PlayerCharacter choosenCharacter;

        //create the framedsd
        JFrame frame = new JFrame("My Swing Frame");
        Game.frame = frame;
        frame.setLayout(null);
        frame.setSize((cols + 1) * pxPerCell, (rows + 2) * pxPerCell);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //init gameMap
        GameMap gameMap = new GameMap(frame, rows, cols, pxPerCell);

        //initialize the chosen character
        switch (characterType){
            case 0:
                choosenCharacter = (PlayerCharacter) new Warrior(gameMap, name, this);
                break;
            case 1:
                choosenCharacter = (PlayerCharacter) new Wizard(gameMap, name, this);
                break;
            case 2:
                 choosenCharacter = (PlayerCharacter) new Priest(gameMap,name, this);
                 break;
            default:
                choosenCharacter = (PlayerCharacter) new Warrior(gameMap, name, this);
                break;
        }

        gameMap.insertEnemyBots(choosenCharacter);
        gameMap.insertItems();
        gameMap.insertTopBar(choosenCharacter);

        //add the player keylistener
        frame.addKeyListener(new PlayerKeyListener(choosenCharacter));
        frame.setFocusable(true);

        //load the map on the frame
        gameMap.loadMap();
        frame.setVisible(true);
        
    }

    public void endGame(){
        System.out.println(" end game ");
        frame.dispose();
        JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
}
