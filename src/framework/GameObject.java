package framework;


import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    protected float x, y;
    protected ObjectId id;
    protected boolean enabled;
    protected float velX = 0, velY = 0;
    protected boolean falling = true;
    protected boolean jumping = false;
    protected int metadata;
    protected int textureTypeX;
    protected int textureTypeY;


    public GameObject(float x, float y,boolean enabled,int textureTypeX,int textureTypeY,int metadata, ObjectId id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enabled = enabled;
        this.metadata = metadata;
        this.textureTypeX = textureTypeX;
        this.textureTypeY = textureTypeY;
    }

    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();


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
    public boolean isFalling() { return falling; }
    public boolean isJumping() { return jumping; }
    public void setFalling(boolean falling) { this.falling = falling; }
    public void setJumping(boolean jumping) { this.jumping = jumping; }
    public void setVelX(float velX) {
        this.velX = velX;
    }
    public void setVelY(float velY) {
        this.velY = velY;
    }
    public void setEnabled(boolean enabled) {this.enabled = enabled;}
    public void setMetadata(int Mdata) {this.metadata = Mdata;}
    public int getMetadata(){return metadata;}
    public boolean getEnabled() {return enabled;}
    public void setTextureTypeX(int textureTypeX) { this.textureTypeX = textureTypeX; }
    public void setTextureTypeY(int textureTypeY) { this.textureTypeY = textureTypeY; }
    public ObjectId getId() {
        return id;
    }
}
