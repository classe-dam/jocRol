package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    public static void showRanking(ArrayList<String> data) {
        // Create the frame
        JFrame frame = new JFrame("Game Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add each string from the array list as a label to the panel
        for (String item : data) {
            JLabel label = new JLabel(item);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            mainPanel.add(label);
        }

        // Create the "Start Game" button
        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setBackground(Color.GRAY);
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);
        startButton.setForeground(Color.WHITE);

        // Add action listener to the button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the frame on button click
                frame.dispose();
            }
        });

        // Add the button to the main panel
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(startButton);

        // Add the main panel to the frame
        frame.add(mainPanel, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }

}
