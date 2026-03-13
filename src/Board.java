import java.awt.*;
import java.util.ArrayList;

public class Board {

    // how many holes are in each row of the star (top to bottom)
    public static final int[] HOLES_PER_ROW = {
        1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1
    };

    public static final int TOTAL_ROWS = 17;

    // spacing between holes in pixels
    public static final int H_SPACING = 32; // horizontal gap
    public static final int V_SPACING = 28; // vertical gap

    // where to draw the board on screen
    private int centerX;
    private int startY;
    private int holeSize = 18; // diameter of each hole

    // stores the pixel x,y of every hole on the board
    // holePositions.get(row) = array of {x, y} for each hole in that row
    private ArrayList<int[][]> holePositions;

    public Board(int centerX, int startY) {
        this.centerX = centerX;
        this.startY = startY;
        holePositions = new ArrayList<>();
        calculateHolePositions();
    }

    // figures out the pixel x,y for every hole and stores them
    private void calculateHolePositions() {
        for (int row = 0; row < TOTAL_ROWS; row++) {
            int numHoles = HOLES_PER_ROW[row];
            int[][] rowPositions = new int[numHoles][2]; // [holeIndex][0=x, 1=y]

            int y = startY + row * V_SPACING;

            // each row is centered horizontally around centerX
            // leftmost hole x = centerX - (numHoles-1)/2 * H_SPACING
            int startX = centerX - (numHoles - 1) * H_SPACING / 2;

            for (int col = 0; col < numHoles; col++) {
                int x = startX + col * H_SPACING;
                rowPositions[col][0] = x;
                rowPositions[col][1] = y;
            }

            holePositions.add(rowPositions);
        }
    }

    // draws the full board
    public void draw(Graphics g) {
        drawStarBackground(g);
        drawHoles(g);
    }

    // draws the tan/gold star shape behind the holes
    private void drawStarBackground(Graphics g) {
        g.setColor(new Color(160, 120, 60)); // dark gold border

        // top triangle (rows 0-8 area, pointing up)
        int[] xUp = {
            centerX,
            centerX - 6 * H_SPACING,
            centerX + 6 * H_SPACING
        };
        int[] yUp = {
            startY - V_SPACING,
            startY + 8 * V_SPACING,
            startY + 8 * V_SPACING
        };
        g.fillPolygon(xUp, yUp, 3);

        // bottom triangle (pointing down)
        int[] xDown = {
            centerX,
            centerX - 6 * H_SPACING,
            centerX + 6 * H_SPACING
        };
        int[] yDown = {
            startY + 17 * V_SPACING,
            startY + 8 * V_SPACING,
            startY + 8 * V_SPACING
        };
        g.fillPolygon(xDown, yDown, 3);

        // middle hexagon fill
        g.setColor(new Color(185, 155, 100));
        int[] xHex = {
            centerX - 6 * H_SPACING,
            centerX,
            centerX + 6 * H_SPACING,
            centerX + 6 * H_SPACING,
            centerX,
            centerX - 6 * H_SPACING
        };
        int[] yHex = {
            startY + 4 * V_SPACING,
            startY,
            startY + 4 * V_SPACING,
            startY + 12 * V_SPACING,
            startY + 16 * V_SPACING,
            startY + 12 * V_SPACING
        };
        g.fillPolygon(xHex, yHex, 6);
    }

    // draws every hole as a circle
    private void drawHoles(Graphics g) {
        for (int row = 0; row < TOTAL_ROWS; row++) {
            int[][] rowPos = holePositions.get(row);
            for (int col = 0; col < rowPos.length; col++) {
                int x = rowPos[col][0];
                int y = rowPos[col][1];
                int r = holeSize / 2;

                // empty hole = tan circle with darker outline
                g.setColor(new Color(210, 185, 145));
                g.fillOval(x - r, y - r, holeSize, holeSize);
                g.setColor(new Color(130, 100, 60));
                g.drawOval(x - r, y - r, holeSize, holeSize);
            }
        }
    }

    // returns the pixel x of a specific hole
    public int getHoleX(int row, int col) {
        return holePositions.get(row)[col][0];
    }

    // returns the pixel y of a specific hole
    public int getHoleY(int row, int col) {
        return holePositions.get(row)[col][1];
    }

    // returns total holes in a given row
    public int getHolesInRow(int row) {
        return HOLES_PER_ROW[row];
    }

    // checks if a given row,col is a valid position on the board
    public boolean isValidPosition(int row, int col) {
        if (row < 0 || row >= TOTAL_ROWS) return false;
        if (col < 0 || col >= HOLES_PER_ROW[row]) return false;
        return true;
    }
}