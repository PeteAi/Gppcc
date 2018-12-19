package framework;

import window.BufferedImageLoader;
import window.Game;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet ts;


    private BufferedImage player_sheet = null;

    public static BufferedImage[][] sprite = new BufferedImage[32][32];
    public BufferedImage[] plane = new BufferedImage[30];

    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            if (Game.windows) {
                player_sheet = loader.LoadImage("C:/Users/Pete Louis Benz/Desktop/Java/Rpg/rsc/Sprite_sheet.png");
            } else {
                player_sheet = loader.LoadImage("/Users/Home/Desktop/Java/Rpg/rsc/Sprite_sheet.png");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        ts = new SpriteSheet(player_sheet);

        getTextures();

    }
    private void getTextures() {
        for(int y = 0;y<32;y++) {
            for(int x = 0;x<32;x++) {
                sprite[x][y] = ts.grabImage(x+1, y+1,32,32);
            }
        }
    }
}
