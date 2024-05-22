package caracters;

import game.Game;
import items.Item;
import items.ItemType;
import map.GameMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class PlayerCharacter extends Character
{
    protected String name;
    protected int gold;
    protected ArrayList<Item> items;
    protected long lastTimeCollidedEnemy;

    private ItemType specialItem;
    public PlayerCharacter(int lifes, int movementSpeed, String imageName, GameMap gameMap, String name, Game game, ItemType specialItem) {
        //update size
        super(lifes, movementSpeed, imageName, gameMap, gameMap.getStartingPosition(), 64, game);
        this.specialItem = specialItem;
        this.gold = 0;
        this.lastTimeCollidedEnemy = 0;
        this.items = new ArrayList<>();
        this.name = name;
        //temporal bug fix
        this.down();
        this.left();
        this.up();
        this.up();
        this.right();
        this.down();
    }

    public void addItem(Item item){
        items.add(item);

        //render item at the topbar
        this.gameMap.getTopBar().addItem(item);
    }

    public void removeItem(ItemType removingItem){
        Iterator<Item> itemsIterator = this.items.iterator();
        while (itemsIterator.hasNext()){
            Item item = itemsIterator.next();
            if(item.getItem() == removingItem){
                items.remove(item);
                this.gameMap.getTopBar().removeItem(item);
            }
        }
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

    public void handleEnemyColision(EnemyBot collidedBot){
        long currentTime = System.currentTimeMillis();

        // Check if at least half a second has passed since the last collision
        if (currentTime - lastTimeCollidedEnemy >= 500) {
            this.removeLife();

            // Update the last collision time
            lastTimeCollidedEnemy = currentTime;
        }

        Rectangle r = this.gameMap.getStartingPosition();
        super.setLocation(new Point(r.x, r.y));
        //special habilities
        if(this.characterContainsItem(this.specialItem)){
            handleCharacterColisionSpecialItem(collidedBot);
            this.removeItem(this.specialItem);
        }
    }

    //to be overriden
    public abstract void handleCharacterColisionSpecialItem(EnemyBot enemyBot);
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

    protected boolean characterContainsItem(ItemType itemType){
        for(Item item : items){
            if(item.getItem() == itemType){
                return true;
            }
        }
        return false;
    }
}
