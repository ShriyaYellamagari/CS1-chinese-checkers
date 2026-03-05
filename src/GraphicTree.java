import java.awt.Color;
import java.awt.Graphics;

public class GraphicTree {
    private int x;
    private int y;
    private int width;
    private int height;
    //private int trunkx;
    //private int trunky;
    private Color color;

    public GraphicTree(int ax, int ay, int aWidth, int aHeight, Color aColor) {
        x = ax;
        y = ay;
        width = aWidth;
        height = aHeight;
        color = aColor;
    }

    public void draw(Graphics g) {
        //tree trunk
        int rectx = x - width/6;
        int recty = y;
        int rectWidth = 2* (x - rectx);
        int rectHeight = height/2;
        g.setColor(new Color(88,57,39));
        g.fillRect(rectx,recty,rectWidth,rectHeight);

        for (int i = 0; i < 4; i++) {
            g.setColor(color);
            int xPoints[] = {x-(width/2), x, x+(width/2)};
            int yPoints[] = {y, y-height, y};
            g.fillPolygon(xPoints, yPoints, 3);

            width = width - 50;
            y = y - (height - 50);
        }
    }
}