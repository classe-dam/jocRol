package utils;

import javax.swing.*;

public class Utils {
    public static String getName() {
        return JOptionPane.showInputDialog(null, "Enter your name:");
    }

    public static int chooseCharacterType() {
        Object[] options = {"Warrior", "Wizard", "Priest"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose your character type:",
                "Character Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]); // Default option is the first one (Warrior)

        return choice;
    }

}
