import game.Game;
import utils.Utils;

public class Main {
    public static void main(String[] args) {
        String name = Utils.getName();
        int choosenCharacterType = Utils.chooseCharacterType();
        System.out.println("chooose caracter type" + choosenCharacterType);
        if (name != null && !name.isEmpty()) {
            // Start the game with the provided name
            System.out.println("name" + name);
            new Game(name, choosenCharacterType);
        } else {
            System.out.println("No name entered. Exiting...");
        }
    }
}