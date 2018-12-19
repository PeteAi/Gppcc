package window;

import java.awt.*;

import static window.Game.frameHeigth;
import static window.Game.frameWidth;
import static window.Game.tex;

public class Menu {
    private int X;
    private int Y;
    private int scale;

    public Menu() {
        scale = frameHeigth / 280;
        X = (int) ((frameWidth / (2.0 * scale)) - 64);
        Y = (int) ((frameHeigth / (7.0 * scale)) - 32);
        Game.StartButton = new Rectangle(X * scale, Y * scale, 104 * scale, 64 * scale);
        Game.OptionsButton = new Rectangle(X * scale, (Y + 96) * scale, 104 * scale, 64 * scale);
        Game.QuitButton = new Rectangle(X * scale, (Y + 192) * scale, 104 * scale, 64 * scale);
    }

    public void render(Graphics g) {
        Graphics2D gd2 = (Graphics2D) g;

        gd2.scale(scale, scale);

        for (int i = 0; i < 3; i++) {
            int sX = 0;
            int sY = 0;
            for (int yy = 0; yy < 34; yy += 32) {
                for (int xx = 0; xx < 97; xx += 32) {
                    g.drawImage(tex.sprite[9 + sX][2 + sY], X + xx, Y + yy + i * 96, null);
                    g.drawImage(tex.sprite[9 + sX][4 + sY], X + xx, Y + yy, null);
                    g.drawImage(tex.sprite[9 + sX][6 + sY], X + xx, Y + yy + 96, null);
                    g.drawImage(tex.sprite[9 + sX][8 + sY], X + xx, Y + yy + 192, null);
                    sX += 1;
                }
                sY += 1;
                sX -= 4;

            }
        }

    }
}
