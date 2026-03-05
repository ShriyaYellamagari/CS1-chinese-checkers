import java.awt.Color;
import java.awt.Graphics;

public class Snowflake {

    //fields - what we need to draw a ornament
    private int x;
    private int y;
    private Color color;
    private int diameter;

    //constructor - 4 args so we can set all the fields
    public Snowflake(int aX, int aY, int aDiameter, Color aColor) {
        x = aX;
        y = aY;
        diameter = aDiameter;
        color = aColor;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x,y,diameter,diameter);
    }
}