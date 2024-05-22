package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnection {

    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/joc_rol";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // JDBC connection
    private Connection connection;

    // Constructor to establish the connection
    public DBConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String userName, int coins, int lifes, boolean didUserWon, int timeDuration) {
        String insertSQL = "INSERT INTO ranking (userName, coins, lifes, didUserWon, gameDuration) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, coins);
            preparedStatement.setInt(3, lifes);
            preparedStatement.setBoolean(4, didUserWon);
            preparedStatement.setInt(5, timeDuration);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s) successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> selectData() {
        String selectSQL = "SELECT * FROM ranking ORDER BY didUserWon DESC, gameDuration DESC, coins DESC, lifes DESC;\n";
        ArrayList<String> rankingElements = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                int coins = resultSet.getInt("coins");
                int lifes = resultSet.getInt("lifes");
                boolean didUserWon = resultSet.getBoolean("didUserWon");
                int gameDuration = resultSet.getInt("gameDuration");
                String element = ("ID: " + id + ", UserName: " + userName + ", Coins: " + coins + ", Lifes: " + lifes + ", DidUserWon: " + didUserWon + ", GameDuration: " + gameDuration);
                rankingElements.add(element);
            }

            return rankingElements;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
