package caracters;

import caracters.Character;
import map.GameMap;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyBot extends Character {
    PlayerCharacter pursuedCharacter;
    int waitMovementTime;
    public EnemyBot(GameMap gameMap, PlayerCharacter pursuedCharacter) {
        super(
                1,
                5,
                "skeleton",
                gameMap,
                new Rectangle(
                        ((gameMap.getCols() - 5 )* gameMap.getPxPerCell() ),
                        ((gameMap.getRows() -  generateYPosition(5,gameMap.getRows() - 1)) * gameMap.getPxPerCell() ),
                        64,
                        64
                )
        );
        this.pursuedCharacter = pursuedCharacter;
        this.waitMovementTime = 50;
    }

    public static int generateYPosition(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public void startChasingCharacter(){
//        Timer timer = new Timer(50, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Code to be executed every 50 milliseconds
//                System.out.println("Executed something");
//            }
//        });
//
//        // Start the timer
//        timer.start();
    }
}
