import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class claude extends JPanel implements MouseListener {

    // frame sizes
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    // tracks which screen we're on: "home" or "game"
    private String currentPage = "home";

    // home page play button bounds (for click detection)
    private static final int PLAY_BTN_X = 400;
    private static final int PLAY_BTN_Y = 450;
    private static final int PLAY_BTN_W = 200;
    private static final int PLAY_BTN_H = 70;

    private ArrayList<Marble> marbles;

    public claude() {
        marbles = new ArrayList<>();
        setFocusable(true);
        addMouseListener(this);
    }

    // -----------------------------------------------------------
    // PAINT — decides which screen to draw
    // -----------------------------------------------------------
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentPage.equals("home")) {
            drawHomePage(g);
        } else if (currentPage.equals("game")) {
            drawGamePage(g);
        }
    }

    // -----------------------------------------------------------
    // HOME PAGE
    // -----------------------------------------------------------
    public void drawHomePage(Graphics g) {
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
    }

    // -----------------------------------------------------------
    // GAME PAGE
    // -----------------------------------------------------------
    public void drawGamePage(Graphics g) {
        // background
        g.setColor(new Color(195, 170, 137));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // title
        g.setColor(new Color(79, 67, 53));
        g.setFont(new Font("Serif", Font.BOLD, 50));
        g.drawString("Chinese Checkers", 300, 60);

        // score panel on the left
        drawScorePanel(g);

        // board in the center/right
        drawBoard(g);

        // draw all marbles
        for (Marble m : marbles) {
            m.draw(g);
        }
    }

    // -----------------------------------------------------------
    // SCORE PANEL
    // -----------------------------------------------------------
    public void drawScorePanel(Graphics g) {
        // box outline
        g.setColor(new Color(79, 67, 53));
        g.setFont(new Font("Serif", Font.BOLD, 22));
        g.drawRoundRect(30, 100, 180, 160, 15, 15);

        // text
        g.drawString("SCORE:", 60, 135);
        g.setFont(new Font("Serif", Font.PLAIN, 20));
        g.drawString("Player 1:  0 / 10", 45, 175);
        g.drawString("Player 2:  0 / 10", 45, 220);
    }

    // -----------------------------------------------------------
    // BOARD (star of david shape — placeholder for now)
    // -----------------------------------------------------------
    public void drawBoard(Graphics g) {
        int centerX = 650;
        int centerY = 420;
        int size = 220; // rough size of each triangle

        // draw a simple star-of-david outline as a placeholder
        // (you'll replace this with proper hex grid later)
        g.setColor(new Color(150, 110, 60)); // board border color
        g.setFont(new Font("Serif", Font.ITALIC, 18));

        // top triangle (pointing up)
        int[] xUp = {
            centerX,
            centerX - size,
            centerX + size
        };
        int[] yUp = {
            centerY - size,
            centerY + size / 2,
            centerY + size / 2
        };
        g.fillPolygon(xUp, yUp, 3);

        // bottom triangle (pointing down)
        int[] xDown = {
            centerX,
            centerX - size,
            centerX + size
        };
        int[] yDown = {
            centerY + size,
            centerY - size / 2,
            centerY - size / 2
        };
        g.fillPolygon(xDown, yDown, 3);

        // label so you know it's a placeholder
        g.setColor(new Color(195, 170, 137));
        g.drawString("Board goes here!", centerX - 70, centerY + 10);
    }

    // -----------------------------------------------------------
    // MOUSE LISTENER
    // -----------------------------------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (currentPage.equals("home")) {
            // check if click is inside the play button
            if (mx >= PLAY_BTN_X && mx <= PLAY_BTN_X + PLAY_BTN_W
                    && my >= PLAY_BTN_Y && my <= PLAY_BTN_Y + PLAY_BTN_H) {
                currentPage = "game"; // SWITCH TO GAME SCREEN
                repaint();
            }
        } else if (currentPage.equals("game")) {
            // TODO: handle marble selection and movement clicks here
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    // -----------------------------------------------------------
    // MAIN
    // -----------------------------------------------------------
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chinese Checkers");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(50, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new claude());
        frame.setVisible(true);
    }
}