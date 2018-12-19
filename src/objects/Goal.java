package objects;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Game;
import window.Handler;

import java.awt.*;
import java.util.LinkedList;

import static window.Game.levelX;
import static window.Game.levelY;

public class Goal extends GameObject {
    Texture tex = Game.getInstance();
    private int endHolewidth = 1300;
    private int endholeVel = 1;
    private int endholeIndex = 0;
    private Handler handler;
    private double c;
    private double X;
    private double Y;
    private double a;
    private double b;
    private boolean end = false;

    public Goal(float x, float y, boolean enabled, int texturTypeX, int textureTypeY, Handler handler, int metadata,
                ObjectId id) {
        super(x, y, enabled, textureTypeY, textureTypeY, metadata, id);
        this.handler = handler;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if (Game.dead) {
            end = false;
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ObjectId.Goal) {
                    handler.object.remove(i);
                } else if (tempObject.getId() == ObjectId.Shooter) {
                    handler.object.remove(i);
                }
            }
            handler.object.get(1).setEnabled(false);
            endHolewidth = 1300;
            endholeIndex = 0;
            Game.dead = false;
            System.out.println("LeX: " + levelX);
            System.out.println("LeY: " + levelY);
        }

        if (end) {
            end = true;
            X = handler.object.getFirst().getX() + 16;
            Y = handler.object.getFirst().getY() + 32;
            a = X - (x + 16);
            b = Y - (y + 16);
            c = Math.sqrt(a * a + b * b);
            endHolewidth -= endholeVel;
            endholeIndex += 1;
            if ((c - 64) > endHolewidth) {

                end = true;

                Game.dead = true;

            }

        }

    }

    @Override
    public void render(Graphics g) {
        if (enabled) {

        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 64);
    }
}
