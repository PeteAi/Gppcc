package objects;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Game;
import window.Handler;

import java.awt.*;
import java.util.LinkedList;

import static window.Game.rasterHeigth;
import static window.Game.rasterWidth;
import static window.Game.shoot;

public class Shooter extends GameObject {
    private Handler handler;
    private int index = 503;
    Texture texture = Game.getInstance();

    public Shooter(float x, float y, boolean enabled, int textureTypeX, int textureTypeY, Handler handler, int metadata, ObjectId id) {
        super(x, y, enabled, textureTypeX, textureTypeY, metadata, id);
        this.handler = handler;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if (false) {
            if (index < 562) {
                index += 1;
                handler.object.get(index).setEnabled(true);
                handler.object.get(index).setX(x);
                handler.object.get(index).setY(y);
                if (metadata == 0) {
                    handler.object.get(index).setVelX(-1);
                } else if (metadata == 1) {
                    handler.object.get(index).setVelX(1);

                }
                shoot = false;
            } else {
                index = 503;

            }
        }
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(texture.sprite[3][0], (int) x, (int) y, null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, rasterWidth, rasterHeigth);
    }
}
