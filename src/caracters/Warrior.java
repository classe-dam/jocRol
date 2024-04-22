package caracters;

import game.Game;
import map.GameMap;

public class Warrior extends PlayerCharacter {
    public Warrior(GameMap gameMap, String name, Game game) {
        super(5, 3, "warrior", gameMap, name, game);
    }
}
