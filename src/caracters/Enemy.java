package caracters;

import map.GameMap;

public class Enemy extends Caracter{
    public Enemy(GameMap gameMap) {
        super(1, 5, "skeleton", gameMap);
    }
}
