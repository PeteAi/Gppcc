package objects;

import framework.GameObject;
import framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class EnemyMove extends GameObject {
    public EnemyMove(float x, float y, boolean enabled, int textureTypeX, int textureTypeY, int metadata, ObjectId id) {
        super(x, y, enabled, textureTypeX, textureTypeY, metadata, id);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.red);
        //g.drawRect((int)x,(int)y,32,32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
