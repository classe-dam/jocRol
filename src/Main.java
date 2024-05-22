import database.DBConnection;
import game.Game;
import utils.Utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DBConnection dbOps = new DBConnection();

        ArrayList<String> rankingElements =  dbOps.selectData();
        if(rankingElements!= null){
            Utils.showRanking(rankingElements);
        }
        String name = Utils.getName();
        int choosenCharacterType = Utils.chooseCharacterType();

        if (name != null && !name.isEmpty() && choosenCharacterType != -1) {
            // Start the game with the provided name and character type
            new Game(name, choosenCharacterType);
        } else {
            System.out.println("No name entered or character type not chosen. Exiting...");
        }

    }



    // test initialization of game
//    public static void main(String[] args) {
//            new Game("player1", 1);
//    }
}