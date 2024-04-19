package caracters;

import items.Item;
import map.GameMap;

import java.util.ArrayList;

public class PlayerCharacter extends Character
{
    String name;
    int gold;
    ArrayList<Item> items;

    public PlayerCharacter(int lifes, int movementSpeed, String imageName, GameMap gameMap, String name) {
        super(lifes, movementSpeed, imageName, gameMap, gameMap.getStartingPosition());
        this.gold = 0;
        this.items = new ArrayList<>();
        this.name = name;
    }
}
