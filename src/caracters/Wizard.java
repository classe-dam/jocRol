package caracters;

import game.Game;
import map.GameMap;

public class Wizard extends  PlayerCharacter {

    public Wizard(GameMap gameMap, String name, Game game) {
        super(3, 7, "wizard", gameMap, name, game);
    }
}
