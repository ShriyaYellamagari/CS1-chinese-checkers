import  java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;


public class MouseKeyInput extends JPanel implements MouseListener, MouseWheelListener, KeyListener {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    private BufferedImage image;
    private Graphics g;
    private Timer timer;
    private Ball ball;
    private ArrayList<Ball> balls;
    private JPanel mousepanel;
    private JLabel statusbar;
    private Color bgColor = Color.BLACK;
    private int count;

    public MouseKeyInput() {

        image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();

        statusbar = new JLabel("default");
        add(statusbar, BorderLayout.NORTH);

        balls = new ArrayList<>();

        ball = new Ball();
        ball.setRandomSpeed(15);
        ball.setDiameter((int)(Math.random()*100)+25);
        ball.setX((int)(Math.random()*100)+250);
        ball.setY((int)(Math.random()*100)+250);

        int r = (int)(Math.random()*253) + 1;
        int g = (int)(Math.random()*253) + 1;
        int b = (int)(Math.random()*253) + 1;
        ball.setColor(new Color(r, g, b));

        //set up and start the Timer
        timer = new Timer(10, new TimerListener());
        timer.start();
        setFocusable(true);
        addMouseListener(this);
        addKeyListener(this);
        addMouseWheelListener(this);
        count = 0;
    }

    @Override
    public void mouseClicked (MouseEvent e) {
        statusbar.setText(String.format("Clicked at %d, %d", e.getX(), e.getY()));
        balls.add(new Ball());
        count ++;
        balls.get(count-1).setRandomSpeed(15);
        balls.get(count-1).setDiameter((int)(Math.random()*100)+25);
        balls.get(count-1).setLocation(e.getX(), e.getY());

        int r = (int)(Math.random()*253) + 1;
        int g = (int)(Math.random()*253) + 1;
        int b = (int)(Math.random()*253) + 1;
        balls.get(count-1).setColor(new Color(r, g, b));
    }


    @Override
    public void mousePressed(MouseEvent e) {
        ball.setXSpeed(ball.getXSpeed() + 25);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ball.setRandomSpeed(15);
        int r = (int)(Math.random()*253) + 1;
        int g = (int)(Math.random()*253) + 1;
        int b = (int)(Math.random()*253) + 1;
        ball.setColor(new Color(r, g, b));
        repaint();
    }

    @Override
    public void mouseExited (MouseEvent e) {
        bgColor = Color.BLACK;
    }

    @Override
    public void mouseEntered (MouseEvent e) {
        int r = (int)(Math.random()*253) + 1;
        int g = (int)(Math.random()*253) + 1;
        int b = (int)(Math.random()*253) + 1;
        bgColor = new Color(r, g, b);
    }

    //scrolling
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotation = e.getWheelRotation();

        if (rotation < 0) {
            for (int i = 0; i < balls.size(); i++) {
                balls.get(i).setColor(Color.RED);
                ball.setColor(Color.RED);
            }
        } else {
            for (int i = 0; i < balls.size(); i++) {
//                int r = (int)(Math.random()*253) + 1;
//                int g = (int)(Math.random()*253) + 1;
//                int b = (int)(Math.random()*253) + 1;
//                balls.get(i).setColor(new Color(r, g, b));
//                ball.setColor(new Color(r, g, b));
                ball.setColor(Color.GRAY);
                balls.get(i).setColor(Color.GRAY);

            }
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'u' || key == 'U') {
            ball.setDiameter(ball.getDiameter() + 10);
            for (int i = 0; i < balls.size(); i++) {
                double dia = balls.get(i).getDiameter();
                balls.get(i).setDiameter(dia + 10);
            }
        }

        if (key == 'd' || key == 'D') {
            if (ball.getDiameter() > 10) {
                ball.setDiameter(ball.getDiameter() - 10);
            }

            for (int i = 0; i < balls.size(); i++) {
                if (balls.get(i).getDiameter() > 10) {
                    double dia = balls.get(i).getDiameter();
                    balls.get(i).setDiameter(dia - 10);
                }
            }
        }

        statusbar.setText("Key typed: " + key);
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            for (int i = 0; i < balls.size(); i++) {
                balls.get(i).setXSpeed(balls.get(i).getXSpeed() + 5);
                balls.get(i).setYSpeed(balls.get(i).getYSpeed() + 5);
            }
            statusbar.setText("Key pressed: Up") ;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            for (int i = 0; i < balls.size(); i++) {
                balls.get(i).setXSpeed(balls.get(i).getXSpeed() - 5);
                balls.get(i).setYSpeed(balls.get(i).getYSpeed() - 5);
            }
            statusbar.setText("Key pressed: Down") ;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (int i = 0; i < balls.size(); i++) {
            //balls.get(i).setRandomSpeed(15);
            int r = (int)(Math.random()*253) + 1;
            int g = (int)(Math.random()*253) + 1;
            int b = (int)(Math.random()*253) + 1;
            balls.get(i).setColor(new Color(r, g, b));
            repaint();
        }
    }

    //TimerListener class that is called repeatedly by the timer
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            g.setColor(bgColor);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            ball.draw(g);
            for (int i = 0; i < balls.size(); i++) {
                balls.get(i).draw(g);
                balls.get(i).move(600, 600);
            }

            ball.move(600, 600);
            repaint();
        }

    }

    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation Shell");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MouseKeyInput());
        frame.setVisible(true);

    }

}