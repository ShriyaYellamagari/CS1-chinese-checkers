import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.*;
import javax.swing.ImageIcon;


public class ChineseCheckers extends JPanel implements MouseListener {

    //frame sizes
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    //home page play button 
    private static int PLAY_BUTTON_X = 400;
    private static int PLAY_BUTTON_Y = 450;
    private static int PLAY_BUTTON_W = 200;
    private static int PLAY_BUTTON_H = 100;

    private String currentPage = "home";
    private static int turn = 1;
    private static int player1Score = 0;
    private static int player2Score = 0;

    private BufferedImage image;
    private Graphics g;
    private Timer timer;
    private ArrayList<Marble> marbles;
    private JLabel statusbar;

    public ChineseCheckers() {
        image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();
        //TODO: DELETE 33-34 ?
        marbles = new ArrayList<>();

        setFocusable(true);
        addMouseListener(this);
    }

    // ------------------------------ CHOOSES WHICH SCENE TO DRAW ------------------------------
    @Override
    public void paintComponent (Graphics g) {
        //super.paintComponent(g);
        if (currentPage.equals("home")) {
            drawHomePage(g);
        } else if (currentPage.equals("game")) {
            drawGamePage(g);
        }
    }

    // ------------------------------  HOME PAGE ------------------------------

    public static void drawHomePage(Graphics g) {

        // Background
        g.setColor(new Color(195, 170, 137)); //page color
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Title
        g.setColor(new Color(79, 67, 53));//dark brown
        g.setFont(new Font("Serif", Font.BOLD, 120));
        g.drawString("Chinese", 300, 300);
        g.drawString("Checkers", 260, 400);

        // Play button
        g.fillRect(PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_W, PLAY_BUTTON_H);
        g.setColor(new Color(195, 170, 137)); // page color
        //g.fillRect(500 - 90, PLAY_BUTTON_Y + 10, 180, 80);
        g.fillRect(PLAY_BUTTON_X + 10, PLAY_BUTTON_Y + 10, PLAY_BUTTON_W - 20, PLAY_BUTTON_H - 20);

        g.setColor(new Color(79, 67, 53));//dark brown
        g.setFont(new Font("Serif", Font.BOLD, 60));
        g.drawString("PLAY", WIDTH/2 - 80, 520);

    }

    // ------------------------------ GAME PAGE ------------------------------

    public static void drawGamePage(Graphics g) {
        g.setColor(new Color(230, 212, 168)); //light page
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Title
        g.setColor(new Color(79, 67, 53));//dark brown
        g.setFont(new Font("Serif", Font.BOLD, 60));
        g.drawString("Chinese Checkers", 300, 75);


        drawScorePanel(g);

        // board
        drawBoard(g);

        //draw all marbles
    }

    // ------------------------------ DRAW SCORE PANEL ------------------------------

    public static void drawScorePanel(Graphics g) {

        ImageIcon image = new ImageIcon("DarkStar.png");

        // border
        g.setColor(new Color(79, 67, 53));//dark brown
        g.fillRect(40, 180, 240, 180);
        g.setColor(new Color(230, 212, 168));
        g.fillRect(40 + 5, 180 + 5, 240 - 10, 180 - 10);


        // players and score
        g.setColor(new Color(79, 67, 53));
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Player 1: " + player1Score, 70, 230);
        g.drawString("Player 2: " + player2Score, 70, 280);

        // turn indicator
        if (turn == 1) {
            g.drawImage(image.getImage(), 190, 205, 30,30, null);
        } else if (turn == 2) {
            g.drawImage(image.getImage(), 190, 255, 30,30, null);
        }
    }

    // ------------------------------ DRAW BOARD ------------------------------
    public static void drawBoard(Graphics g) {
        ImageIcon image = new ImageIcon("ChineseCheckersBoard3.png");
        g.drawImage(image.getImage(), 330, 135, 590,590, null);

        //Board board = new Board();
    }

    // ------------------------------ MOUSE CLICKED ------------------------------

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        int aX = e.getX();
        int aY = e.getY();

        if (currentPage.equals("home")) {
            if (aX >= PLAY_BUTTON_X && aX <= PLAY_BUTTON_X + PLAY_BUTTON_W
                    && aY >= PLAY_BUTTON_Y && aY <= PLAY_BUTTON_Y + PLAY_BUTTON_H) {
                currentPage = "game";
                repaint();
            }
        } else if (currentPage.equals("game")) {
            //TODO: ADD MARBLE MOVING LOGIC
            System.out.println(aX + " " + aY);

            //TODO: MAKE SCORE BOARD CHANGE BASED ON MARBLE/GAME LOGIC
        }
    }


    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {    }

    // ------------------------------ MAIN ------------------------------
    public static void main (String[] args) {
        JFrame frame = new JFrame("Chinese Checkers Game!");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(50, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ChineseCheckers());
        frame.setVisible(true);
    }
}