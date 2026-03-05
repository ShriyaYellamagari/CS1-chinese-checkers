//CS1 Winter Wonderland Project
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import  java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ChineseCheckers extends JPanel /*implements MouseListener*/ {

    //frame sizes
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private static int playButtonX = 400;
    private static int playButtonY = 450;

    private BufferedImage image;
    private Graphics g;
    //private Timer timer;
    private Marble marble;//TODO: EDIT MARBLE CLASS
    private ArrayList<Ball> marbles;
    private JPanel mousepanel;
    private JLabel statusbar;

    public ChineseCheckers() {
        image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();

        statusbar = new JLabel("home page");
        add(statusbar, BorderLayout.NORTH); //TODO: CAN REMOVE THIS LATER ONCE WORKS

        marbles = new ArrayList<>();

        setFocusable(true);
        //addMouseListener(this);
    }

    /*
    @Override
    public void mouseClicked (MouseEvent e) {
        statusbar.setText(String.format("Clicked at %d, %d", e.getX(), e.getY()));
        //if clicked on play button
        if ( playButtonX < e.getX() < (playButtonX + 200) && playButtonY < e.getY() < (playButtonY + 200) ) {
            //TODO: SWITCH SCREEN TO NEW PAGE
            repaint();
        }
    }
     */

    //paints things onto panel
    public void paintComponent (Graphics g) {
        drawHomePage(g);
    }

    public static void drawHomePage(Graphics g) {
        g.setColor(new Color(195, 170, 137)); //page color
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Title
        g.setColor(new Color(79, 67, 53));//dark brown
        g.setFont(new Font("Serif", Font.BOLD, 120));
        g.drawString("Chinese", 300, 300);
        g.drawString("Checkers", 260, 400);

        // Play button
        g.fillRect(playButtonX, playButtonY, 200,100);
        g.setColor(new Color(195, 170, 137)); // page color
        g.fillRect(500 - 90, playButtonY + 10, 180, 80);

        g.setColor(new Color(79, 67, 53));//dark brown
        g.setFont(new Font("Serif", Font.BOLD, 60));
        g.drawString("PLAY", WIDTH/2 - 80, 520);
    }

    public static void main (String[] args) {
        JFrame frame = new JFrame("Chinese Checkers Game!!");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(50, 0);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ChineseCheckers());
        frame.setVisible(true);
    }
}
