package window;

import javax.swing.*;
import java.awt.*;

public class Window {

    public Window(int width, int height, int scale, String title, Game game) {
        game.setPreferredSize(new Dimension(width*scale,height*scale));
        game.setMaximumSize(new Dimension(width*scale,height*scale));
        game.setMinimumSize(new Dimension(width*scale,height*scale));

        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

}
