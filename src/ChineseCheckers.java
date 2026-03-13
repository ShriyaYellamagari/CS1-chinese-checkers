import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class ChineseCheckers extends JPanel implements MouseListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    // "home" or "game"
    private String currentPage = "home";

    // play button bounds
    private static final int PLAY_BTN_X = 380;
    private static final int PLAY_BTN_Y = 450;
    private static final int PLAY_BTN_W = 220;
    private static final int PLAY_BTN_H = 70;

    // game objects
    private Board board;
    private ArrayList<Marble> marbles;

    // game state
    private int currentPlayer = 1;       // whose turn: 1 or 2
    private int player1Score = 0;        // marbles reached goal
    private int player2Score = 0;
    private Marble selectedMarble = null; // marble the player clicked on

    // player colors
    private static final Color P1_COLOR = new Color(180, 50, 50);  // red
    private static final Color P2_COLOR = new Color(50, 100, 180); // blue

    public ChineseCheckers() {
        marbles = new ArrayList<>();
        setFocusable(true);
        addMouseListener(this);
    }

    // called once when PLAY is clicked — sets up board and marbles
    private void initGame() {
        board = new Board(650, 60);
        marbles.clear();
        placeStartingMarbles();
        currentPlayer = 1;
        player1Score = 0;
        player2Score = 0;
        selectedMarble = null;
    }

    // places 10 marbles for each player in their starting triangles
    private void placeStartingMarbles() {
        // Player 1 starts in TOP triangle (rows 0-3)
        for (int row = 0; row <= 3; row++) {
            for (int col = 0; col < Board.HOLES_PER_ROW[row]; col++) {
                int x = board.getHoleX(row, col);
                int y = board.getHoleY(row, col);
                marbles.add(new Marble(row, col, x, y, 18, P1_COLOR));
            }
        }

        // Player 2 starts in BOTTOM triangle (rows 13-16)
        for (int row = 13; row <= 16; row++) {
            for (int col = 0; col < Board.HOLES_PER_ROW[row]; col++) {
                int x = board.getHoleX(row, col);
                int y = board.getHoleY(row, col);
                marbles.add(new Marble(row, col, x, y, 18, P2_COLOR));
            }
        }
    }

    // -----------------------------------------------------------
    // PAINT
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
    private void drawHomePage(Graphics g) {
        g.setColor(new Color(195, 170, 137));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(new Color(79, 67, 53));
        g.setFont(new Font("Serif", Font.BOLD, 120));
        g.drawString("Chinese", 220, 250);
        g.drawString("Checkers", 180, 390);

        // play button
        g.setColor(new Color(79, 67, 53));
        g.fillRoundRect(PLAY_BTN_X, PLAY_BTN_Y, PLAY_BTN_W, PLAY_BTN_H, 20, 20);
        g.setColor(new Color(220, 195, 160));
        g.fillRoundRect(PLAY_BTN_X + 5, PLAY_BTN_Y + 5, PLAY_BTN_W - 10, PLAY_BTN_H - 10, 15, 15);
        g.setColor(new Color(79, 67, 53));
        g.setFont(new Font("Serif", Font.BOLD, 50));
        g.drawString("PLAY", PLAY_BTN_X + 45, PLAY_BTN_Y + 50);
    }

    // -----------------------------------------------------------
    // GAME PAGE
    // -----------------------------------------------------------
    private void drawGamePage(Graphics g) {
        g.setColor(new Color(195, 170, 137));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(new Color(79, 67, 53));
        g.setFont(new Font("Serif", Font.BOLD, 40));
        g.drawString("Chinese Checkers", 270, 45);

        drawScorePanel(g);
        board.draw(g);

        // draw marbles on top of board
        for (Marble m : marbles) {
            m.draw(g);
        }

        // white ring around selected marble
        if (selectedMarble != null) {
            g.setColor(Color.WHITE);
            int r = (int)(selectedMarble.getDiameter() / 2) + 4;
            g.drawOval(
                (int)selectedMarble.getX() - r,
                (int)selectedMarble.getY() - r,
                r * 2, r * 2
            );
        }

        // whose turn label
        g.setFont(new Font("Serif", Font.BOLD, 22));
        g.setColor(currentPlayer == 1 ? P1_COLOR : P2_COLOR);
        g.drawString("Player " + currentPlayer + "'s turn", 40, 320);
    }

    // -----------------------------------------------------------
    // SCORE PANEL
    // -----------------------------------------------------------
    private void drawScorePanel(Graphics g) {
        g.setColor(new Color(79, 67, 53));
        g.drawRoundRect(20, 80, 200, 200, 15, 15);

        g.setFont(new Font("Serif", Font.BOLD, 22));
        g.setColor(new Color(79, 67, 53));
        g.drawString("SCORE:", 75, 115);

        g.setColor(P1_COLOR);
        g.fillOval(35, 130, 18, 18);
        g.setColor(new Color(79, 67, 53));
        g.setFont(new Font("Serif", Font.PLAIN, 20));
        g.drawString("P1: " + player1Score + " / 10", 60, 145);

        g.setColor(P2_COLOR);
        g.fillOval(35, 175, 18, 18);
        g.setColor(new Color(79, 67, 53));
        g.drawString("P2: " + player2Score + " / 10", 60, 190);
    }

    // -----------------------------------------------------------
    // MOUSE LISTENER
    // -----------------------------------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (currentPage.equals("home")) {
            if (mx >= PLAY_BTN_X && mx <= PLAY_BTN_X + PLAY_BTN_W
                    && my >= PLAY_BTN_Y && my <= PLAY_BTN_Y + PLAY_BTN_H) {
                currentPage = "game";
                initGame();
                repaint();
            }
        } else if (currentPage.equals("game")) {
            handleGameClick(mx, my);
        }
    }

    private void handleGameClick(int mx, int my) {
        Color currentColor = (currentPlayer == 1) ? P1_COLOR : P2_COLOR;

        // check if player clicked on one of their own marbles
        for (Marble m : marbles) {
            if (isClickOnMarble(mx, my, m) && m.getColor().equals(currentColor)) {
                selectedMarble = m;
                repaint();
                return;
            }
        }

        // TODO: if a marble is selected and player clicks a hole → move it
        // (next step!)
        repaint();
    }

    private boolean isClickOnMarble(int mx, int my, Marble m) {
        double dx = mx - m.getX();
        double dy = my - m.getY();
        double radius = m.getDiameter() / 2;
        return (dx * dx + dy * dy) <= (radius * radius);
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
        frame.setContentPane(new ChineseCheckers());
        frame.setVisible(true);
    }
}