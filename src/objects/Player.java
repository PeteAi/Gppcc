package objects;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Game;
import window.Handler;

import java.awt.*;
import java.util.LinkedList;

import static window.Game.*;

public class Player extends GameObject {
    private float width = 32,height = 32;
    private Handler handler;

    Texture tex = Game.getInstance();
    private int textureType;


    public Player(float x, float y,boolean enabled, int textureType, Handler handler, ObjectId id) {
        super(x, y,enabled, id);
        this.handler = handler;
        this.textureType = textureType;
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

        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ObjectId.Wall && tempObject.getEnabled()==true) {
                    if(getBounds().intersects(tempObject.getBounds())) {
                        y = tempObject.getY()-height;
                        //System.out.println("Botton");
                    }
                    if(getBoundsTop().intersects(tempObject.getBounds())) {
                        y = tempObject.getY()+height;
                        //System.out.println("Top");
                    }
                    if(getBoundsRight().intersects(tempObject.getBounds())) {
                        x = tempObject.getX()-width;
                        //System.out.println("Right");
                    }
                    if(getBoundsLeft().intersects(tempObject.getBounds())) {
                        x = tempObject.getX()+width;
                        //System.out.println("Left");
                    }
            }else if (tempObject.getId() == ObjectId.Collect && tempObject.getEnabled()==true) {

                double a1 = (x+16)-(tempObject.getX()+16);
                double b1 = (y+16)-(tempObject.getY()+16);
                double abstand = Math.sqrt(a1*a1+b1*b1);

                if(abstand<20) {
                    handler.object.get(i).setEnabled(false);
                    coins += 0.08f;
                }
            }
        }
    }




    public void render(Graphics g) {
        Graphics2D gd2 = (Graphics2D) g;

        g.drawImage(tex.sprite[playerani],(int)x,(int)y,32,32,null);

        /*gd2.setColor(Color.red);
        gd2.draw(getBounds());
        gd2.draw(getBoundsTop());
        gd2.draw(getBoundsRight());
        gd2.draw(getBoundsLeft());*/


    }
    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int)(y+height/2), (int)width/2, (int)height/2);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int)y, (int)width/2, (int)height/2);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x+width-5), (int)y+5, 5, (int)height-10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y+5, 5, (int)height-10);
    }

}
