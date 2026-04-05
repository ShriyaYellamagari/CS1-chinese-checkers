import java.awt.*;

public class Board {
    
    //private int row; 
    //private int col;
    //private int x;
    //private int y;
    private static int holeSize = 17;
    private static final int CLICK_SIZE = 28; 
    
    private static int centerX = 639;
    private static int topY = 184;    

    private static int hSpacing = 35;
    private static int vSpacing = 30;

    private static int[][] holeX;
    private static int[][] holeY; 

    private static int[] holesEachRow = {1, 2, 3, 4, 
        13, 12, 11, 10, 9, 10, 11, 12, 13, 
        4, 3, 2, 1
    };

    public Board() {
        holeX = new int[17][];
        holeY = new int[17][];
    
        for (int r = 0; r < 17; r++) {
            int numHoles = holesEachRow[r];
            
            holeX[r]= new int[numHoles];
            holeY[r] = new int[numHoles];

            int y = topY + r * vSpacing;


            int leftmostX = centerX - (numHoles - 1) * hSpacing / 2;

            for (int c = 0; c < numHoles; c++) {
                holeX[r][c] = leftmostX + c * hSpacing;
                holeY[r][c] = y;
            }
        }
    }


    public static void drawBoardHoles (Graphics g) {
        for (int row = 0; row < 17; row++) {
            for(int col = 0; col < holesEachRow[row]; col++) {
                int x = holeX[row][col];
                int y = holeY[row][col];
                
                if (row <=3 || row >= 13) {
                    g.setColor(new Color(181, 138, 69)); // dark yellow 

                    g.fillOval(x - holeSize/2, y - holeSize/2, holeSize, holeSize);
                } else {
                    g.setColor(new Color(228, 173, 84)); // darker dark yellow
                    g.fillOval(x - holeSize/2, y - holeSize/2, holeSize, holeSize);
                }
                g.setColor(Color.black);
                g.drawOval(x - holeSize/2, y - holeSize/2, holeSize, holeSize);

            }
        }
    }

    public boolean isValidPosition (int row, int col) {
        if (row < 0 || row > 17) {return false;} 
        if (col < 0 || col > holesEachRow[row]) {return false;} 
        return true;
    }


    public int getHoleX(int row, int col) {
        return holeX[row][col];
    }


    public int getHoleY(int row, int col) {
        return holeY[row][col];
    }

    /*
    public int[] getHolesPerRow() {
        return holesEachRow; 
    } */

    public int getHolesInRow(int row) {
        return holesEachRow[row]; 
    }

    public int getHoleSize() {
//        return holeSize;
        return CLICK_SIZE;
    }

    public int getHSpacing() {
        return hSpacing;
    }
}   