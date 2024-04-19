package caracters;

import caracters.Character;
import map.GameMap;

import java.awt.*;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyBot extends Character {
    PlayerCharacter pursuedCharacter;
    int waitMovementTime;
    public EnemyBot(GameMap gameMap, PlayerCharacter pursuedCharacter) {
        super(
                1,
                2,
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
        startChasingCharacter();
    }

    public static int generateYPosition(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private void startChasingCharacter(){
        Timer timer = new Timer (this.waitMovementTime, new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                move();
            }
        });

        timer.start();
    }

    private void move(){
        Rectangle botPosition = super.getPosition();
        Rectangle pursuedPosition = this.pursuedCharacter.getPosition();

        // Calculate x and y distances between the rectangles
        int xDistance = pursuedPosition.x - botPosition.x;
        int yDistance = pursuedPosition.y - botPosition.y;

        boolean moveXAxis;

        if(xDistance == 0){
            moveXAxis = false;
        }if(yDistance ==0){
            moveXAxis = true;
        }else{
            moveXAxis = new Random().nextBoolean();
        }
        if(moveXAxis){
            if(xDistance > 0){
                super.right();
            }else{
                super.left();
            }
        }else{
            if(yDistance > 0){
                super.down();
            }else{
                super.up();
            }
        }
    }
}
