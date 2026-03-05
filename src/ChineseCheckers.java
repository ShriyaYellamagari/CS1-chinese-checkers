//CS1 Winter Wonderland Project
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

public class ChineseCheckers extends JPanel implements MouseListener {

    //frame sizes
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    //home page play button 
    private static int PLAY_BUTTON_X = 400;
    private static int PLAY_BUTTON_Y = 450;
    private static int PLAY_BUTTON_W = 200;
    private static int PLAY_BUTTON_H = 70;

    private String currentPage = "home";

    private BufferedImage image;
    private Graphics g;
    private ArrayList<Marble> marbles;
    private JPanel mousepanel;

    public ChineseCheckers() {
        image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();
        
        marbles = new ArrayList<>();

        setFocusable(true);
        addMouseListener(this);
    }

    // ------------------------------ PAINTS ONTO PANEL - CHOOSES WHICH SCREEN TO DRAW ------------------------------

    public void paintComponent (Graphics g) {

        if (currentPage.equals("home")) {
            drawHomePage(g);
        } else if (currentPage.equals("game")) {
            drawGamePage(g);
        }

    }
    
    // ------------------------------ DRAW HOME PAGE ------------------------------

    public static void drawHomePage(Graphics g) {
        g.setColor(new Color(195, 170, 137)); //page color
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Title
        g.setColor(new Color(79, 67, 53));//dark brown
        g.setFont(new Font("Serif", Font.BOLD, 120));
        g.drawString("Chinese", 300, 300);
        g.drawString("Checkers", 260, 400);

        // Play button
        g.fillRect(PLAY_BUTTON_X, PLAY_BUTTON_Y, 200,100);
        g.setColor(new Color(195, 170, 137)); // page color
        g.fillRect(500 - 90, PLAY_BUTTON_Y + 10, 180, 80);

        g.setColor(new Color(79, 67, 53));//dark brown
        g.setFont(new Font("Serif", Font.BOLD, 60));
        g.drawString("PLAY", WIDTH/2 - 80, 520);

        /*
        // background
        g.setColor(new Color(195, 170, 137));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // title
        g.setColor(new Color(79, 67, 53));
        g.setFont(new Font("Serif", Font.BOLD, 120));
        g.drawString("Chinese", 250, 250);
        g.drawString("Checkers", 210, 380);

        // play button — dark brown border
        g.setColor(new Color(79, 67, 53));
        g.fillRoundRect(PLAY_BTN_X, PLAY_BTN_Y, PLAY_BTN_W, PLAY_BTN_H, 20, 20);
        // inner fill (lighter so it looks like a button)
        g.setColor(new Color(220, 195, 160));
        g.fillRoundRect(PLAY_BTN_X + 5, PLAY_BTN_Y + 5, PLAY_BTN_W - 10, PLAY_BTN_H - 10, 15, 15);
        // play text
        g.setColor(new Color(79, 67, 53));
        g.setFont(new Font("Serif", Font.BOLD, 50));
        g.drawString("PLAY", PLAY_BTN_X + 35, PLAY_BTN_Y + 50);
        */
    }

    // ------------------------------ DRAW GAME PAGE ------------------------------

    public static void drarwGamePage(Graphics g) {
        g.setColor(new Color(195, 170, 137)); //page color
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //title

        // score panel 
        drawScorePanel(g)

        // board
        drawBoard(g)

        //draw all marbles
    }

    // ------------------------------ DRAW SCORE PANEL ------------------------------

    public static void drawScorePanel(Graphics G) {

    }

    // ------------------------------ DRAW BOARD ------------------------------

    public static void drawBoard(Graphics G) {

    }

    // ------------------------------ MOUSE CLICKED ------------------------------
    @Override
    public void mouseClicked (MouseEvent e) {
        int aX = e.getX();
        int aY = e.getY();

        if (currentPage.equals("home")) {
            if (PLAY_BUTTON_X <) {

            }
        }
        

        if ( playButtonX < e.getX() < (playButtonX + 200) && playButtonY < e.getY() < (playButtonY + 200) ) {
            //TODO: SWITCH SCREEN TO NEW PAGE
            repaint();
        }

        /*
        if (currentPage.equals("home")) {
163            // check if click is inside the play button
164            if (mx >= PLAY_BTN_X && mx <= PLAY_BTN_X + PLAY_BTN_W
165                    && my >= PLAY_BTN_Y && my <= PLAY_BTN_Y + PLAY_BTN_H) {
166                currentPage = "game"; // SWITCH TO GAME SCREEN
167                repaint();
168            }
169        } else if (currentPage.equals("game")) {
170            // TODO: handle marble selection and movement clicks here
171        }
        */
    }


    // ------------------------------ MAIN ------------------------------
    public static void main (String[] args) {
        JFrame frame = new JFrame("Chinese Checkers Game!!");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(50, 0);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ChineseCheckers());
        frame.setVisible(true);
    }
}
