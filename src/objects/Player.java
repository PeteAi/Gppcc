package objects;

import framework.Chunkloader;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Game;
import window.Handler;

import java.awt.*;
import java.util.LinkedList;

import static window.Game.*;

public class Player extends GameObject {
    private float width = 32,height = 64;

    private Handler handler;

    Texture tex = Game.getInstance();


    public Player(float x, float y, boolean enabled, int textureTypeX, int textureTypeY, int metadata, Handler handler, ObjectId id) {
        super(x, y,enabled,textureTypeX,textureTypeY,metadata, id);
        this.handler = handler;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if (enabled) {
            x += velX;
            y += velY;
            Collision(object);
        }
    }

    private void Collision(LinkedList<GameObject> object) {
        for (int yy = ChunkY * 32; yy < ChunkY * 32 + 32; yy++) {
            for (int xx = ChunkX * 32; xx < ChunkX * 32 + 32; xx++) {
                GameObject tempObject = Chunkloader.Map[xx][yy];
                if (tempObject.getId() == ObjectId.Wall && tempObject.getEnabled() == true) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        y = tempObject.getY() - height - 2;
                        //System.out.println("Botton");
                    }
                    if (getBoundsTop().intersects(tempObject.getBounds())) {
                        y = tempObject.getY() + 32;
                        //velY = 0;
                        //System.out.println("Top");
                    }
                    if (getBoundsRight().intersects(tempObject.getBounds())) {
                        x = tempObject.getX() - 35;
                        //System.out.println("Right");
                    }
                    if (getBoundsLeft().intersects(tempObject.getBounds())) {
                        x = tempObject.getX() + 35;
                    }
                } else if (tempObject.getId() == ObjectId.Enemy && tempObject.getEnabled() == true) {
                    if (tempObject.getBounds().intersects(getBounds())) {
                        dead = true;

                    }
                } else if (tempObject.getId() == ObjectId.ShooterBullet && tempObject.getEnabled()) {
                    if (tempObject.getBounds().intersects(getBounds())) {
                        dead = true;

                    }
                }

            }
        }
    }




    public void render(Graphics g) {

        g.drawImage(tex.sprite[9][0],(int)x,(int)y,32,32,null);
        g.drawImage(tex.sprite[9][1],(int)x,(int)y+32,32,32,null);


        /*gd2.setColor(Color.red);
        gd2.draw(getBounds());
        gd2.draw(getBoundsTop());
        gd2.draw(getBoundsRight());
        gd2.draw(getBoundsLeft());
        */

    }
    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int)(y+height/2), (int)width/2, (int)height/2);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int)y, (int)width/2, (int)height/2);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x+width/4*3), (int)(y+height/4), (int)width/4, (int)height/2);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)(y+height/4), (int)width/4, (int)height/2);
    }

}
