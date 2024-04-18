package playerkeylistener;
import caracters.Character;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PlayerKeyListener extends KeyAdapter {

    Character chosenCharacter;

    public PlayerKeyListener(Character chosenCharacter) {
        this.chosenCharacter = chosenCharacter;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        // Handle 'a', 'w', 's', 'd' keys
        switch (keyChar) {
            case 'a':
                chosenCharacter.left();
                break;
            case 'w':
                chosenCharacter.up();
                break;
            case 's':
                chosenCharacter.down();
                break;
            case 'd':
                chosenCharacter.right();
                break;
            default:
                // Handle other key presses
                break;
        }
    }
}
