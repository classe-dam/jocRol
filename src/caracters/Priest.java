package caracters;

import game.Game;
import map.GameMap;

public class Priest extends PlayerCharacter {
    public Priest(GameMap gameMap, String name, Game game) {
        super(5, 3, "priest", gameMap, name, game);
    }
}
