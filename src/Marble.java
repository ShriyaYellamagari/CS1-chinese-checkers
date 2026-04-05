import java.awt.Color;
import java.awt.Graphics;

public class Marble {
    private int row;
    private int col;

    private int x;
    private int y;

    private int diameter;
    private Color color;
    private boolean reachedGoal; 
    
    public Marble() {
        this.color = Color.RED;
        this.diameter = 17;
        this.row = 0;
        this.col = 0;
        this.reachedGoal = false;
    }

    public Marble(int row, int col, int x, int y, int diameter, Color color) {
        this.row = row;
        this.col = col;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = color;
        this.reachedGoal = false;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - diameter/2, y-diameter/2, diameter, diameter);
        g.setColor(color.darker());
        g.drawOval(x - diameter/2, y-diameter/2, diameter, diameter);
    }

    public void moveTo(int newRow, int newCol, int newX, int newY) {
        this.row = newRow;
        this.col = newCol;
        this.x = newX;
        this.y = newY;
    }



    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }

    public int getCol() { return col; }
    public void setCol(int col) { this.col = col; }

    public double getX() { return x; }
    public void setX(int x) { this.x = x; }

    public double getY() { return y; }
    public void setY(int y) { this.y = y; }

    public double getDiameter() { return diameter; }
    public void setDiameter(int diameter) { this.diameter = diameter; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public boolean isReachedGoal() { return reachedGoal; }
    public void setReachedGoal(boolean reachedGoal) { this.reachedGoal = reachedGoal; }
}