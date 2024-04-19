package map;

import caracters.EnemyBot;
import caracters.PlayerCharacter;
import items.Item;
import items.ItemType;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameMap {
    private int rows;
    private int cols;
    private int pxPerCell;
    private Rectangle startingPosition;

    private HashSet<Rectangle> bannedRectangles;
    private HashSet<Rectangle> wallRectangles;
    private HashSet<Rectangle> exitMapRectangles;
    private JFrame frame;

    private LinkedList<Item> items;
    private LinkedList<EnemyBot> enemys;


    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Rectangle getStartingPosition() {
        return startingPosition;
    }

    public int getPxPerCell() {
        return pxPerCell;
    }

    public boolean isCharacterTouchingWall(Rectangle characterBounds) {

        // Iterate through the wall rectangles and check for intersection
        for (Rectangle wall : wallRectangles) {
            if (characterBounds.intersects(wall)) {
                return true; // Character is touching a wall
            }
        }
        return false; // Character is not touching any wall
    }

    public GameMap(JFrame frame, int rows, int cols, int pxPerCell){
        this.frame = frame;
        this.cols = cols;
        this.rows = rows;
        this.pxPerCell = pxPerCell;
        this.initializePoints();
    }

    private void initializePoints(){

        this.wallRectangles = new HashSet<>();
        this.startingPosition = new Rectangle(0,2 * pxPerCell,this.pxPerCell, this.pxPerCell );
        this.exitMapRectangles = new HashSet<>();
        this.exitMapRectangles.add(new Rectangle(this.cols * pxPerCell,(this.rows - 1 )* pxPerCell, this.pxPerCell,this.pxPerCell));
        this.exitMapRectangles.add(new Rectangle(this.cols * pxPerCell,(this.rows - 2 )* pxPerCell, this.pxPerCell,this.pxPerCell));
        this.bannedRectangles = loadBannedPoints();
    }

    public void loadMap(){
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
            Rectangle p = new Rectangle(col * this.pxPerCell, row * this.pxPerCell, this.pxPerCell, this.pxPerCell);
            if(this.bannedRectangles.contains(p)){
                return false;
            }
            this.wallRectangles.add(p);
            return true;
        }else{
            return false;
        }
    }

    private HashSet<Rectangle> loadBannedPoints(){
        HashSet<Rectangle> bannedRectangles = new HashSet<>();
        bannedRectangles.add(this.startingPosition);
        bannedRectangles.addAll(this.exitMapRectangles);
        bannedRectangles.add(new Rectangle(0,3 * pxPerCell, this.pxPerCell, this.pxPerCell));

        return bannedRectangles;
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

    }

    public void insertEnemyBots(PlayerCharacter choosenCharacter){
        //generate bots
        for(int i = 0; i <= 5; i++){
            EnemyBot enemy = new EnemyBot(this, choosenCharacter);
//            this.enemys.add(enemy);
        }
    }

    public void insertItems(){
        //insert items
        List<ItemType> items = Arrays.asList(ItemType.values());
        for(ItemType item : items){
            Item createdItem = new Item(item);
//            this.items.add(createdItem);
        }
    }
}
