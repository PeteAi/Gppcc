package objects;

import framework.GameObject;
import framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class EnemyMovement extends GameObject {
    public EnemyMovement(float x, float y, boolean enabled, int texturTypeX, int textureTypeY, int metadata, ObjectId id) {
        super(x, y, enabled, textureTypeY, textureTypeY, metadata, id);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        g.drawRect((int) x, (int) y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
