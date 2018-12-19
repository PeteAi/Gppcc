package objects;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import org.lwjgl.Sys;
import window.Game;

import java.awt.*;
import java.util.LinkedList;

public class Wall extends GameObject {

    Texture tex = Game.getInstance();

    public Wall(float x, float y,boolean enabled, int textureTypeX,int textureTypeY,int metadata, ObjectId id) {
        super(x, y,enabled,textureTypeX,textureTypeY,metadata, id);
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        g.drawImage(tex.sprite[textureTypeX][textureTypeY], (int) x, (int) y, 32, 32, null);
        System.out.println("hi");
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

    public void setTextureTypeX(int textureTypeX) { this.textureTypeX = textureTypeX; }

    public void setTextureTypeY(int textureTypeY) { this.textureTypeY = textureTypeY; }

    public ObjectId getId() {
        return id;
    }
}
