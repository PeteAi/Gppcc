package objects;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Game;
import window.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Collect extends GameObject {
    private float width = 32,height = 32;
    private Handler handler;

    Texture tex = Game.getInstance();
    private int textureType;

    public Collect(float x, float y,boolean enabled, int textureType, ObjectId id) {
        super(x, y,enabled, id);
        this.textureType = textureType;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }
    public void render(Graphics g) {
        if (enabled) {
            Graphics2D gd2 = (Graphics2D) g;
            if(textureType == 0) {
                g.drawImage(tex.sprite[36],(int)x,(int)y,32,32,null);
                g.setColor(Color.YELLOW);
                g.fillRect((int)x,(int)y,(int)width, (int)height);
            }
            if(textureType == 1) {
                g.drawImage(tex.plane[3],(int)x,(int)y,null);
            }

        }
    }
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width, (int) height);
    }

}
