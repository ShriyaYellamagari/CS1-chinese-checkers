//required import statements
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class BouncingBall extends JPanel {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    //required global variables
    private BufferedImage image;
    private Graphics g;
    private Timer timer;
    private Ball[] balls;

    //Constructor required by BufferedImage
    public BouncingBall() {
        //set up Buffered Image and Graphics objects
        image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();

        balls = new Ball[50];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball();
            balls[i].setRandomSpeed(15);
            balls[i].setDiameter((int)(Math.random()*100)+25);
            balls[i].setX((int)(Math.random()*100)+250);
            balls[i].setY((int)(Math.random()*100)+250);


            int r = (int)(Math.random()*253) + 1;
            int g = (int)(Math.random()*253) + 1;
            int b = (int)(Math.random()*253) + 1;
            balls[i].setColor(new Color(r, g, b));
        }

        //set up and start the Timer
        timer = new Timer(10, new TimerListener());
        timer.start();

    }

    //TimerListener class that is called repeatedly by the timer
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            for (int i = 0; i < balls.length; i++) {
                balls[i].draw(g);
                balls[i].move(600, 600);
            }

            repaint();
        }

    }

    //do not modify this
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    //main method with standard graphics code
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation Shell");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new BouncingBall());
        frame.setVisible(true);
    }

}