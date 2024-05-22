package caracters;

import game.Game;
import items.ItemType;
import map.GameMap;

public class Wizard extends  PlayerCharacter {

    public Wizard(GameMap gameMap, String name, Game game) {
        super(3, 7, "wizard", gameMap, name, game, ItemType.POTION);
    }

    @Override
    public void handleCharacterColisionSpecialItem(EnemyBot collidedBot){
        //recovery the losen life and increase one
        this.increaseLifes(2);
    }
}
