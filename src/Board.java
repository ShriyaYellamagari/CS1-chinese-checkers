import java.awt.*;
import java.util.ArrayList;

public class Board {

    public int[] holesPerRow = {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};
    public int totalRows = 17;
    private int diameter;
    private int x;
    private int y;

    private int[][] holePositions;


    public Board(){
        diameter = 20;
        //drawHoles(g);
    }

    public void drawHoles(Graphics g) {
        for (int i = 0; i < totalRows; i++) {
            for (int hole = 0; hole < holesPerRow[i]; hole++) {
                calculatePosition(i, hole);
                g.drawOval(x, y, diameter, diameter);
            }
        }

    }

    public void calculatePosition(int rowNum, int holesInRow) {

    }


}