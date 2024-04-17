import map.GameMap;

import javax.swing.*;

public class Game {
    Game(){
        int rows = 25;
        int cols = 25;
        int pxPerCell = 32;


        JFrame frame = new JFrame("My Swing Frame");
        frame.setLayout(null);

        frame.setSize((cols + 1) * pxPerCell, (rows + 2) * pxPerCell);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println(frame.getBounds());
        GameMap gameMap = new GameMap(frame, rows, cols, pxPerCell);
        frame.setVisible(true);
    }
}
