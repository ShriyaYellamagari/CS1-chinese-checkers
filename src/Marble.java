import java.awt.Color;
import java.awt.Graphics;

public class Marble {

    // 1. Declaration of Variables

    private double x;			//x-coordinate of the center of the marble
    private double y;			//y-coordinate of the center of the marble
    private double diameter;	//diameter of the marble
    private double radius;		//radius of the marble
    private Color color;		//color of the marble
    private double xSpeed;		//x-speed = change in x-position
    private double ySpeed;		//y-speed = change in y-position


    // 2. Create a default constructor

    public Marble() {
        setColor(Color.RED);
        setDiameter(25);
        setRadius(12.5);
        setXSpeed(0);
        setYSpeed(0);
        setX(0);
        setY(0);
    }

    // 3. Write a constructor that allows the user to input the parameters (x, y, diameter, Color)
    // and sets the x and y-speed = 0.

    public Marble(double aX, double aY, double aDiameter, Color aColor) {
        setColor(aColor);
        setDiameter(aDiameter);
        setRadius(aDiameter/2);
        setXSpeed(0);
        setYSpeed(0);
        setX(aX);
        setY(aY);
    }


    // 4. Create getters and setters for all private variables

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
        this.radius = diameter/2;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        this.diameter = radius*2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }


    // 5. Finish the following methods
    // 6. Test using BouncingBallTester.java

    public void draw(Graphics g) {
        g.setColor(color);
        //g.fillOval((int)(x=radius), (int)(y=radius), (int)diameter, (int)diameter);
        g.fillOval((int) (x-radius), (int) (y-radius), (int)diameter, (int)diameter);

    }

    // 7. Sets the center location of the ball
    //    This "teleports" the ball to the given x/y location
    public void setLocation(double xLoc, double yLoc) {
        setX(xLoc);
        setY(yLoc);
    }



    // 8. Sets the xSpeed and ySpeed to a value between
    // negative maxSpeed and positive maxSpeed
    public void setRandomSpeed(double maxSpeed) {
        xSpeed = (int) (Math.random()*maxSpeed*2+1) - maxSpeed;
        ySpeed = (int) (Math.random()*maxSpeed*2+1) - maxSpeed;
    }


    // 9. Write the move method to make the ball move around the screen
    // and bounce of the edges.
    public void move(int rightEdge, int bottomEdge) {
        x += xSpeed;
        y += ySpeed;
        if (x + radius >= rightEdge || x - radius <= 0) {
            xSpeed *= -1;
        }
        if (y + radius >= bottomEdge || y - radius <= 0) {
            ySpeed *= -1;
        }

    }
}