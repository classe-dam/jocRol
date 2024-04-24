package caracters;

import game.Game;
import items.ItemType;
import map.GameMap;

public class Warrior extends PlayerCharacter {
    public Warrior(GameMap gameMap, String name, Game game) {
        super(5, 5, "warrior", gameMap, name, game, ItemType.SWORD);

    }

    @Override
    public void handleCharacterColisionSpecialItem(EnemyBot collidedBot){
        //destroy enemy
        collidedBot.hide();
        this.label.repaint();
    }
}
