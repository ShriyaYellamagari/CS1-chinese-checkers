import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class fonts {

    public static void main(String[] args) {
        Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        for (Font f : fonts) {
            System.out.println(f.getName());
        }

    }

}