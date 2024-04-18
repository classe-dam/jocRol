package playerkeylistener;
import caracters.Caracter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;


public class PlayerKeyListener implements KeyListener {

    Caracter chosenCharacter;

    public PlayerKeyListener(Caracter chosenCharacter) {
        this.chosenCharacter = chosenCharacter;
    }

    private HashMap<Character, Long> keyPressTimes = new HashMap<>();
    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        long currentTime = System.currentTimeMillis();

        if(keyPressTimes.containsKey(keyChar)){
            boolean isElapsed = isElapsed(keyChar);
            if(!isElapsed){
                return;
            }else{
                keyPressTimes.put(keyChar, currentTime);
            }
        }else{
            // Store the timestamp for the pressed key
            keyPressTimes.put(keyChar, currentTime);
        }




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

    private boolean isElapsed(char keyChar) {
        long lastKeyPressTime = keyPressTimes.getOrDefault(keyChar, 0L);
        long timeDiff = System.currentTimeMillis() - lastKeyPressTime;
        return timeDiff >= 250;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key release
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed
    }
}
