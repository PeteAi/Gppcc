package framework;

import window.BufferedImageLoader;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet bs, ts;

    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;

    public BufferedImage[] sprite = new BufferedImage[1024];
    public BufferedImage[] plane = new BufferedImage[30];

    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            player_sheet = loader.LoadImage("/Users/Home/Desktop/Java/Rpg/rsc/Sprite_sheet.png");

        } catch (Exception e) {
            e.printStackTrace();
        }
        ts = new SpriteSheet(player_sheet);

        getTextures();

    }
    private void getTextures() {
        int i = 0;
        for(int y = 0;y<32;y++) {
            for(int x = 0;x<32;x++) {
                sprite[i] = ts.grabImage(x+1, y+1,32,32);
                i++;
            }
        }
    }
}
