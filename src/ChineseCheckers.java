import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;


public class ChineseCheckers extends JPanel implements MouseListener {

    //frame sizes
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    //home page play button 
    private static int PLAY_BUTTON_X = 400;
    private static int PLAY_BUTTON_Y = 450;
    private static int PLAY_BUTTON_W = 200;
    private static int PLAY_BUTTON_H = 100;

    //win page play again button
    private static int WIN_BUTTON_X = 400 - 20;
    private static int WIN_BUTTON_Y = 450;
    private static int WIN_BUTTON_W = 200 + 40;
    private static int WIN_BUTTON_H = 80;

    private String currentPage = "home";
    private static int turn = 1;
    private static int p1Score = 0;
    private static int p2Score = 0;

    private BufferedImage image;
    private Graphics g;
    private ArrayList<Marble> marbles;
    private Board board;
    private Color p1Color = new Color(177, 12, 12);
    private Color p2Color = new Color(38, 70, 209);
    private Marble selectedMarble = null;
    private boolean hasHopped = false; //helps track hops and steps


    public ChineseCheckers() {
        image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();
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
        } else if (currentPage.equals("win1")) {
            drawWinPage(g, 1);
        } else if (currentPage.equals("win2")) {
            drawWinPage(g, 2);
        }
    }

    // INITILIZES BOARD AND MARBLES

    private void initElements() {
        board = new Board();

        //top triangle (player 1) marbles
        for (int row = 0; row <= 3; row++){
            for (int col = 0; col < board.getHolesInRow(row); col++) {
                int x = board.getHoleX(row, col);
                int y = board.getHoleY(row, col);
                marbles.add(new Marble(row, col, x, y, 17, p1Color));
            }
        }

        //bottom triangle (player 2) marbles
        for (int row = 16; row >= 13; row--){
            for (int col = 0; col < board.getHolesInRow(row); col++) {
                int x = board.getHoleX(row, col);
                int y = board.getHoleY(row, col);
                marbles.add(new Marble(row, col, x, y, 17, p2Color));
            }
        }
    }

    // ------------------------------  HOME PAGE ------------------------------

    public void drawHomePage(Graphics g) {

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


    public void drawGamePage(Graphics g) {
        //Background
        g.setColor(new Color(230, 212, 168)); //light page
        g.fillRect(0, 0, WIDTH, HEIGHT);


        // Title
        g.setColor(new Color(79, 67, 53));//dark brown
        g.setFont(new Font("Serif", Font.BOLD, 60));
        g.drawString("Chinese Checkers", 300, 75);


        // Board
        ImageIcon CCBoardImage = new ImageIcon("CCBoard4.png");
        g.drawImage(CCBoardImage.getImage(), 370, 135, 540,590, null);
        board.drawBoardHoles(g);

        // Redo button 
        ImageIcon redoImage = new ImageIcon("Redo.png");
        g.drawImage(redoImage.getImage(), 955, 10, 30, 30, null);
        
        // End turn button
        g.setColor(new Color(79, 67, 53));
        g.fillRoundRect(40, 380, 150, 50, 10, 10);
        g.setColor(new Color(230, 212, 168));
        g.fillRoundRect(45, 385, 140, 40, 10, 10);
        g.setColor(new Color(79, 67, 53));
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("End Turn", 70, 412);

        // Score Panel
        drawScorePanel(g);

        for (Marble m: marbles) {
            m.draw(g);
        }

        if (selectedMarble != null) {
            g.setColor(Color.black);
            int r = (int) selectedMarble.getDiameter()/2 + 4;
            int x = (int) selectedMarble.getX();
            int y = (int) selectedMarble.getY();

            g.drawOval(x-r, y-r, 2*r, 2*r);
        }
    }

    // ------------------------------ DRAW WIN PAGE ------------------------------
    public void drawWinPage(Graphics g, int winner) {
        // Background
        g.setColor(new Color(230, 212, 168)); //light page
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Title
        g.setColor(new Color(79, 67, 53));//dark brown
        g.setFont(new Font("Serif", Font.BOLD, 120));
		g.drawString("Player " + winner + " Wins!", 130, 350);

        // Play Again
        g.fillRect(WIN_BUTTON_X, WIN_BUTTON_Y, WIN_BUTTON_W, WIN_BUTTON_H);
        g.setColor(new Color(230, 212, 168)); //light page color
        g.fillRect(WIN_BUTTON_X + 10, WIN_BUTTON_Y + 10, WIN_BUTTON_W - 20, WIN_BUTTON_H - 20);

        g.setColor(new Color(79, 67, 53));//dark brown
        g.setFont(new Font("Serif", Font.BOLD, 40));
        g.drawString("Play Again", WIDTH/2 - 95, 505);
    }

    // ------------------------------ DRAW SCORE PANEL ------------------------------

    public void drawScorePanel(Graphics g) {

        ImageIcon image = new ImageIcon("DarkStar.png");

        // border
        g.setColor(new Color(79, 67, 53));//dark brown
        g.fillRoundRect(40, 190, 220, 120, 10, 10);
        g.setColor(new Color(230, 212, 168));
        g.fillRoundRect(40 + 5, 190 + 5, 220 - 10, 120 - 10, 10, 10);

        // players and score
        g.setColor(new Color(79, 67, 53));
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Player 1: " + p1Score + "/10", 70, 230);
        g.drawString("Player 2: " + p2Score + "/10", 70, 280);

        // turn indicator
        if (turn == 1) {
            g.drawImage(image.getImage(), 200, 205, 30,30, null);
        } else if (turn == 2) {
            g.drawImage(image.getImage(), 200, 255, 30,30, null);
        }
    }

    // ------------------------------ MARBLE & HOLE LOGIC ------------------------------
    public boolean marbleIsClicked (Marble m, int clickX, int clickY) {
        int marbleX = (int) m.getX(); 
        int marbleY = (int) m.getY();
        int r = (int) m.getDiameter()/2;

        int dist = (int) Math.pow(Math.pow((clickX-marbleX), 2) + Math.pow((clickY-marbleY),2), 0.5); 
        if (dist <= r) {
            return true;
        } else{return false;}
    }

    public boolean holeIsClicked (int clickX, int clickY, int row, int col) {
        int holeX = board.getHoleX(row, col);
        int holeY = board.getHoleY(row, col);
        int r = board.getHoleSize();

        //return (int) Math.pow(Math.pow(clickX-holeX, 2) + Math.pow(clickY-holeY,2) , 0.5) <= r;

        int dx = clickX-holeX;
        int dy = clickY-holeY;
        return (dx * dx + dy * dy) <= (r * r);
    }

    public boolean holeEmpty (int row, int col) {
        for(Marble m: marbles) {
            if (m.getRow()==row && m.getCol()==col) {
                return false;
            }
        }
        return true;
    }

    public int getColumnnOffset (int fromRow, int toRow) {
        int[] holesPerRow = {1, 2, 3, 4, 
        13, 12, 11, 10, 9, 10, 11, 12, 13, 
        4, 3, 2, 1
        };
        return (holesPerRow[toRow] - holesPerRow[fromRow]) / 2;
    }

    public boolean isValidMove (int fromRow, int fromCol, int toRow, int toCol) {
        if (!board.isValidPosition(toRow, toCol)) {return false;}
        if (Math.abs(toRow-fromRow) > 1) {return false;}

        int fromX = board.getHoleX(fromRow, fromCol);
        int toX = board.getHoleX(toRow, toCol);
        int hSpacing = board.getHSpacing();


        if (toRow == fromRow) {
            //same row - just move left/right by 1;
            return Math.abs(toX-fromX) == hSpacing; 
        } else {
            //adjacent rows - down or up by 1
            return Math.abs(toX-fromX) == hSpacing/2 || Math.abs(toX-fromX) == hSpacing/2 + 1;
        }
    }

    public boolean isValidHop (int fromRow, int fromCol, int toRow, int toCol) {
        if (!board.isValidPosition(toRow, toCol)) {return false;}
        if (Math.abs(toRow-fromRow) > 2) {return false;}

        int fromX = board.getHoleX(fromRow, fromCol);
        int fromY = board.getHoleY(fromRow, fromCol);
        int toX = board.getHoleX(toRow, toCol);
        int toY = board.getHoleY(toRow, toCol);
        int midX = (fromX + toX) /2;
        int midY = (fromY + toY) /2;

        for (int row = 0; row < 17; row++) {
            for (int col = 0; col < board.getHolesInRow(row); col++) {
                int x = board.getHoleX(row, col);
                int y = board.getHoleY(row, col);
                
                if (Math.abs(x-midX) <= 2 && Math.abs(y-midY) <= 2)  {
                    //middle marble is found
                    if(!holeEmpty(row, col)) {
                        return true;
                    }
                }
            }
        }
        return false; //no middle marble
    }

    public boolean checkWin (int player) {
        Color playerColor;
        if (player == 1) {
            playerColor = p1Color; 
        } else {playerColor = p2Color;}

        for (Marble m: marbles) {
            if (m.getColor().equals(playerColor)) {
                if (player == 1 && m.getRow() < 13) {return false;}
                if (player == 2 && m.getRow() > 3) {return false;}
            }
        }
        return true;
    }

    public void updateScore () {
        for (Marble m: marbles) {
            /*if (m.getColor().equals(p1Color) && m.getRow() >= 13) {
                p1Score++;
            } else if (m.getColor().equals(p2Color) && m.getRow() <= 3) {
                p2Score++;
            }*/

            if (m.isReachedGoal()) {continue;} //already counted, so skip
            
            if (m.getColor().equals(p1Color) && m.getRow() >= 13) {
                m.setReachedGoal(true);
                p1Score++;
            } else if (m.getColor().equals(p2Color) && m.getRow() <= 3) {
                m.setReachedGoal(true);
                p2Score++;
            }
        }
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
                initElements();
                repaint();
            }
        } else if (currentPage.equals("game")) {
            System.out.println(aX + " " + aY);
                
            Color currentColor; 
            if (turn==1) {currentColor=p1Color;} else {currentColor=p2Color;}

            // Restart the game
            if (aX > 955 && aX < 985 && aY > 10 && aY < 40) {
                turn = 1; 
                selectedMarble = null;
                hasHopped = false;
                p1Score = 0;
                p2Score = 0;
                marbles.clear();
                initElements();
                repaint();
            }

            // End turn button after hopping
            if (aX > 40 && aX < 190 && aY > 380 && aY < 430 && hasHopped) {
                selectedMarble = null;
                hasHopped = false; 
                if (turn==1) {turn = 2;} else {turn=1;}
                repaint();
            }

            if (selectedMarble != null) {
                for (int r = 0; r < 17; r++) {
                    for (int c = 0; c < board.getHolesInRow(r); c++) {
                        if (holeEmpty(r,c) && holeIsClicked(aX, aY, r, c)) {
                            if (isValidMove(selectedMarble.getRow(), selectedMarble.getCol(), r, c) && !hasHopped) {
                                int newX = board.getHoleX(r, c);
                                int newY = board.getHoleY(r, c);
                                selectedMarble.moveTo(r, c, newX, newY);
                                selectedMarble = null; 
                                hasHopped = false; 

                                if (turn==1) {turn = 2;} else {turn=1;}

                                updateScore();
                                if (checkWin(1)) {currentPage = "win1";}
                                if (checkWin(2)) {currentPage = "win2";}

                                repaint();
                                return;
                            } else if (isValidHop(selectedMarble.getRow(), selectedMarble.getCol(), r, c)) {
                                int newX = board.getHoleX(r, c);
                                int newY = board.getHoleY(r, c);
                                selectedMarble.moveTo(r, c, newX, newY);
                                hasHopped = true;
                                
                                updateScore();
                                if (checkWin(1)) {currentPage = "win1";}
                                if (checkWin(2)) {currentPage = "win2";}

                                repaint();
                                return;
                            }
                        }
                    }
                }
            }

        
            // Select a marble
            for (Marble m: marbles) {
                if (marbleIsClicked(m, aX, aY) && m.getColor().equals(currentColor)) {
                    selectedMarble = m; 
                    repaint();
                    return;
                }
            }
        
        } else if (currentPage.equals("win1") || currentPage.equals("win2")) {
            if (aX >= WIN_BUTTON_X && aX <= WIN_BUTTON_X + WIN_BUTTON_W
                    && aY >= WIN_BUTTON_Y && aY <= WIN_BUTTON_Y + WIN_BUTTON_H) {
                turn = 1; 
                selectedMarble = null;
                hasHopped = false;
                p1Score = 0;
                p2Score = 0;
                marbles.clear();
                initElements();
                currentPage = "game";
                repaint();
            }
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