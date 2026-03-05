import java.awt.Color;
import java.awt.Graphics;

public class Marble {

    // 1. Declaration of Variables

    private double diameter;    //diameter of the marble
    private Color color;        //color of the marble
    private boolean reachedGoal; //has reached opposite 

    private int row; 
    private int col;

    //position for drawing
    private double x;
    private double y;


    public Marble() {
        this.row = 0; 
        this.col = 0;
        this.diameter = 25; 
        this.color =  Color.RED;
        this.reachedGoal = false;
    }
    

    public Marble(int row, int col, double x, double y, double diameter, Color color) {
        this.row = row; 
        this.col = col; 
        this.x = x;
        this.y = y; 
        this.diameter = diameter; 
        this.color = color;
        this.reachedGoal = false;
    }

    public void draw(Graphics g) {
        double radius = diameter/2;

        //fills circle
        g.setColor(color);
        g.fillOval((int) (x-radius), (int) (y-radius), (int)diameter, (int)diameter);

        //dark outline
        g.setColor(color.darker());
        g.drawOval((int)(x - radius), (int)(y - radius), (int)diameter, (int)diameter);
    }

    public void moveTo(int newRow, int newCol, double newX, double newY) {
        this.row = newRow; 
        this.col = newCol; 
        this.x = newX; 
        this.y = newY;
    }
    

    // Getters and setters

    public double getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public double getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isReachedGoal() {
        return reachedGoal;
    }

    public void setReachedGoal(boolean reachedGoal) {
        this.reachedGoal = reachedGoal;
    }

}