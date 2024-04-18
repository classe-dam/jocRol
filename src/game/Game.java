package game;

import caracters.Caracter;
import caracters.Priest;
import caracters.Warrior;
import caracters.Wizard;
import map.GameMap;
import playerkeylistener.PlayerKeyListener;

import javax.swing.*;

public class Game {
    public static JFrame frame;
    public Game(){
        int rows = 25;
        int cols = 25;
        int pxPerCell = 32;


        JFrame frame = new JFrame("My Swing Frame");
        Game.frame = frame;
        frame.setLayout(null);

        frame.setSize((cols + 1) * pxPerCell, (rows + 2) * pxPerCell);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameMap gameMap = new GameMap(frame, rows, cols, pxPerCell);
//        Caracter chosenCharacter = (Caracter) new Warrior(gameMap);
//                Caracter chosenCharacter = (Caracter) new Wizard(gameMap);
        Caracter chosenCharacter = (Caracter) new Priest(gameMap);
        frame.addKeyListener(new PlayerKeyListener(chosenCharacter));
        frame.setFocusable(true);
        System.out.println(frame.getBounds());
        gameMap.loadMap();
        frame.setVisible(true);
        
    }
}