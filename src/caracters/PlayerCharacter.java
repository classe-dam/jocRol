package caracters;

import game.Game;
import items.Item;
import map.GameMap;

import java.awt.*;
import java.util.ArrayList;

public class PlayerCharacter extends Character
{
    private String name;
    private int gold;
    private ArrayList<Item> items;
    private long lastTimeCollidedEnemy;

    public PlayerCharacter(int lifes, int movementSpeed, String imageName, GameMap gameMap, String name, Game game) {
        //update size
        super(lifes, movementSpeed, imageName, gameMap, gameMap.getStartingPosition(), 22, game);
        this.gold = 0;
        this.lastTimeCollidedEnemy = 0;
        this.items = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public void up() {
        super.up();
        checkPositionForObstaclesOrEnemies();
    }

    @Override
    public void down() {
        super.down();
        checkPositionForObstaclesOrEnemies();
    }

    @Override
    public void right() {
        super.right();
        checkPositionForObstaclesOrEnemies();
    }

    @Override
    public void left() {
        super.left();
        checkPositionForObstaclesOrEnemies();
    }

    public void handleEnemyColision(){
        long currentTime = System.currentTimeMillis();

        // Check if at least half a second has passed since the last collision
        if (currentTime - lastTimeCollidedEnemy >= 500) {
            // Perform collision actions
            System.out.println("Player collided with enemy!");
            this.removeLife();

            // Update the last collision time
            lastTimeCollidedEnemy = currentTime;
        }
    }
    private void checkPositionForObstaclesOrEnemies(){
        //only object
    }
}
