package caracters;

import game.Game;
import items.Item;
import map.GameMap;

import java.awt.*;
import java.util.ArrayList;

public class PlayerCharacter extends Character
{
    protected String name;
    protected int gold;
    protected ArrayList<Item> items;
    protected long lastTimeCollidedEnemy;

    public PlayerCharacter(int lifes, int movementSpeed, String imageName, GameMap gameMap, String name, Game game) {
        //update size
        super(lifes, movementSpeed, imageName, gameMap, gameMap.getStartingPosition(), 22, game);
        this.gold = 0;
        this.lastTimeCollidedEnemy = 0;
        this.items = new ArrayList<>();
        this.name = name;
    }

    public void addItem(Item item){
        items.add(item);
        //render item at the topbar

        this.gameMap.getTopBar().addItem(item);
    }

    public String getName() {
        return name;
    }

    public void increaseGold(){
        this.gold += 10;
        this.gameMap.getTopBar().setGold(String.valueOf(this.gold));
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
        checkGameWon();
    }

    @Override
    public void down() {
        super.down();
        checkPositionForObstaclesOrEnemies();
        checkGameWon();
    }

    @Override
    public void right() {
        super.right();
        checkPositionForObstaclesOrEnemies();
        checkGameWon();
    }

    @Override
    public void left() {
        super.left();
        checkPositionForObstaclesOrEnemies();
        checkGameWon();
    }

    public void handleEnemyColision(){
        long currentTime = System.currentTimeMillis();

        // Check if at least half a second has passed since the last collision
        if (currentTime - lastTimeCollidedEnemy >= 500) {
            this.removeLife();

            // Update the last collision time
            lastTimeCollidedEnemy = currentTime;
        }

        super.setPosition(this.gameMap.getStartingPosition());
    }
    private void checkPositionForObstaclesOrEnemies(){
        Item intersectedItem = this.gameMap.positionColliseWithItem(this.getPosition());
        //only object
        if(intersectedItem != null){
            switch (intersectedItem.getItem()){
                case SWORD, MITRE, POTION:
                    this.addItem(intersectedItem);
                    intersectedItem.hide();
                    break;
                case GOLD:
                    this.increaseGold();
                    intersectedItem.changeToRandomPosition();
                    break;
            }
        }


    }
    private void checkGameWon(){
        if(this.gameMap.positionIsAtExist(this.getPosition()) && this.gold >= 50){
            this.getGame().winGame();
        }
    }
}
