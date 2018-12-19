package objects;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Game;
import window.Handler;

import java.awt.*;

import java.util.LinkedList;

import static window.Game.dead;

public class ShooterBullet extends GameObject {
    private Handler handler;
    private Texture tex = Game.getInstance();
    private LinkedList<Integer> TaX = new LinkedList<>();
    private LinkedList<Integer> TaY = new LinkedList<>();
    private LinkedList<Boolean> TaBool = new LinkedList<>();

    private int index = 0;


    public ShooterBullet(float x, float y, boolean enabled, int textureTypeX, int textureTypeY, Handler handler, int metadata, ObjectId id) {
        super(x, y, enabled, textureTypeX, textureTypeY, metadata, id);
        this.handler = handler;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if (dead) {
            TaX.clear();
            TaY.clear();
            index = 0;
        }


        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ObjectId.Wall) {
                if (tempObject.getBounds().intersects(getBounds())) {
                    enabled = false;
                }
            }
        }


    }

    @Override
    public void render(Graphics g) {

        if (enabled) {
            g.drawImage(tex.sprite[5][0], (int) x, (int) y, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
