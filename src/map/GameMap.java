package map;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GameMap {
    int rows;
    int cols;
    int pxPerCell;

    HashSet<Point> bannedPoints;
    HashSet<Point> wallPoints;
    public GameMap(JFrame frame, int rows, int cols, int pxPerCell){
        this.wallPoints = new HashSet<>();
        this.cols = cols;
        this.rows = rows;
        this.pxPerCell = pxPerCell;
        this.bannedPoints = loadBannedPoints();
        loadMap(frame,  pxPerCell);
    }

    private void loadMap(JFrame frame, int pxPerCell){
        for(int col = 0; col <= cols; col++){
            for(int row = 0; row <= rows; row ++){
                if(loadWall(col, row)){
                    insertMapCell(pxPerCell, pxPerCell, col * pxPerCell, row * pxPerCell, "./src/images/dungeon/wall.png", frame);
                }else{
                    insertMapCell(pxPerCell, pxPerCell, col * pxPerCell, row * pxPerCell, "./src/images/dungeon/land.png", frame);
                }

            }
        }
    }

    private boolean loadWall(int col, int row){
        if(col == 0 || row == 0 || col == cols || row == rows){
            Point p = new Point(col * this.pxPerCell, row * this.pxPerCell);
            if(this.bannedPoints.contains(p)){
                return false;
            }
            this.wallPoints.add(p);
            return true;
        }else{
            return false;
        }
    }

    private HashSet<Point> loadBannedPoints(){
        HashSet<Point> bannedPoints = new HashSet<>();
        bannedPoints.add(new Point(0,2 * pxPerCell ));
        bannedPoints.add(new Point(0,3 * pxPerCell));
        bannedPoints.add(new Point(this.cols * pxPerCell,(this.rows - 1 )* pxPerCell));
        bannedPoints.add(new Point(this.cols * pxPerCell,(this.rows - 2 )* pxPerCell));

        return bannedPoints;
    }

    private boolean CoordinateIsADoorSpace(int col, int row){



        return false;
    }

    public static void insertMapCell(int width, int height, int x, int y, String imagePath, JFrame frame) {
        // Load the image
        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Resize the image if necessary
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon scaledImageIcon = new ImageIcon(image);

        // Create the JLabel with the image
        JLabel imageLabel = new JLabel(scaledImageIcon);

        // Set the position of the JLabel
        imageLabel.setBounds(x, y, width, height);

        // Set the z-index of the JLabel
        frame.getContentPane().add(imageLabel);

        System.out.println("inserting at" + x + "----" + y + "-----" + imageLabel.getBounds());

    }
}
