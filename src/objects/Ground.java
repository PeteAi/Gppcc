package objects;


import framework.GameObject;
import framework.ObjectId;
import framework.Texture;

import java.awt.*;
import java.util.LinkedList;

public class Ground extends GameObject {
    Texture texture;

    public Ground(float x, float y, boolean enabled, int textureTypeX, int textureTypeY, int metadata, ObjectId id) {
        super(x, y, enabled, textureTypeX, textureTypeY, metadata, id);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.sprite[textureTypeX][textureTypeY], (int) x, (int) y, null);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
