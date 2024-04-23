package caracters;

import game.Game;
import items.ItemType;
import map.GameMap;

public class Priest extends PlayerCharacter {
    public Priest(GameMap gameMap, String name, Game game) {
        super(5, 3, "priest", gameMap, name, game, ItemType.MITRE);
    }

    @Override
    public void handleCharacterColisionSpecialItem(EnemyBot collidedBot){
        //recovery the losen life
        this.increaseLifes(1);
    }
}
