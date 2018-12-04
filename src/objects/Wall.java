package objects;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Game;

import java.awt.*;
import java.util.LinkedList;

public class Wall extends GameObject {

    Texture tex = Game.getInstance();
    private int textureType;

    public Wall(float x, float y,boolean enabled, int texturType, ObjectId id) {
        super(x, y,enabled, id);
        this.textureType = texturType;
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        if (enabled) {
            Graphics2D gd2 = (Graphics2D) g;
            g.drawImage(tex.sprite[0], (int)x, (int)y, 32, 32, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public ObjectId getId() {
        return id;
    }
}
