package caracters;

import items.Item;
import map.GameMap;

import java.awt.*;
import java.util.ArrayList;

public class PlayerCharacter extends Character
{
    private String name;
    private int gold;
    private ArrayList<Item> items;

    public PlayerCharacter(int lifes, int movementSpeed, String imageName, GameMap gameMap, String name) {
        //update size
        super(lifes, movementSpeed, imageName, gameMap, gameMap.getStartingPosition());
        this.gold = 0;
        this.items = new ArrayList<>();
        this.name = name;
    }
}
