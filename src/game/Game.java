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
        int cols = 25;
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
                choosenCharacter = (PlayerCharacter) new Warrior(gameMap, name);
                break;
            case 1:
                choosenCharacter = (PlayerCharacter) new Wizard(gameMap, name);
                break;
            case 2:
                 choosenCharacter = (PlayerCharacter) new Priest(gameMap,name);
                 break;
            default:
                choosenCharacter = (PlayerCharacter) new Warrior(gameMap, name);
                break;
        }

        gameMap.insertEnemyBots(choosenCharacter);
        gameMap.insertItems();

        //add the player keylistener
        frame.addKeyListener(new PlayerKeyListener(choosenCharacter));
        frame.setFocusable(true);

        //load the map on the frame
        gameMap.loadMap();
        frame.setVisible(true);
        
    }
}
