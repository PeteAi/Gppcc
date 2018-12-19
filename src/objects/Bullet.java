package objects;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Handler;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static window.Game.*;

public class Bullet extends GameObject {
    Handler handler;
    private BufferedImage[] bullet = new BufferedImage[8];
    private Point point = new Point(0, 0);
    private double angle;

    public Bullet(float x, float y, boolean enabled, int textureTypeX, int textureTypeY, int metadata, Handler handler, ObjectId id) {
        super(x, y, enabled, textureTypeX, textureTypeY, metadata, id);
        this.handler = handler;

    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if (enabled) {
            if (doOnce) {
                double a = moX - handler.object.getFirst().getX();
                double b = moY - handler.object.getFirst().getY();
                double c = Math.sqrt(a * a + b * b);

                velX = (float) (a / c) * 2;
                velY = (float) (b / c) * 2;

                point.x = moX;
                point.y = moY;

                angle = Math.toRadians(getAngle(point));
                //hitBoxY = Math.sin(angle)*(104);
                //hitBoxX = Math.sqrt(104*104-hitBoxY*hitBoxY)-104;

                int sX = 0;
                int sY = 0;
                int index = 0;
                for (int yy = 0; yy < 4; yy++) {
                    for (int xx = 0; xx < 2; xx++) {
                        bullet[index] = Texture.sprite[9 + sX][2 + sY];
                        sX += 1;
                        index += 1;
                    }
                    sY += 1;
                    sX -= 4;

                }
                doOnce = false;
            }
            x += velX;
            y += velY;
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ObjectId.Enemy) {
                    if (CheckColision(tempObject.getBounds(), 104)) {
                        if (tempObject.getId() == ObjectId.Enemy) {
                            handler.object.remove(i);
                            System.out.println("HIT");
                        }
                    }
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {
        Graphics2D gd2 = (Graphics2D) g;
        if (enabled) {

            AffineTransform transform = AffineTransform.getTranslateInstance((int) x, (int) y);
            transform.rotate(angle, 32, 32);
            gd2.drawImage(joinBufferedImage(bullet), transform, null);

        }
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    public static BufferedImage joinBufferedImage(BufferedImage[] img) {
        int width = 128;
        int height = 64;

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gd2 = newImage.createGraphics();
        int sX = 0;
        int sY = 0;
        for (int yy = 0; yy < 2; yy++) {
            for (int xx = 0; xx < 5; xx++) {
                gd2.drawImage(Texture.sprite[9 + sX][2 + sY], xx * 32, yy * 32, null);
                sX += 1;
            }
            sY += 1;
            sX -= 5;

        }
        gd2.dispose();
        return newImage;
    }

    public float getAngle(Point target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - y, target.x - x));

        if (angle < 0) {
            angle += 360;
        }

        return angle;
    }

    public boolean CheckColision(Rectangle rectangle, double circle) {
        Rectangle rect = new Rectangle((int) x - 32, (int) y - 32, 96, 96);
        boolean re = false;
        if (rect.intersects(rectangle)) {

            Point[] rectpoints = new Point[4];
            rectpoints[0] = new Point(rectangle.x, rectangle.y);
            rectpoints[1] = new Point(rectangle.x + rectangle.width, rectangle.y);
            rectpoints[2] = new Point(rectangle.x, rectangle.y + rectangle.height);
            rectpoints[3] = new Point(rectangle.x + rectangle.width, rectangle.y + rectangle.height);

            for (int i = 0; i < 4; i++) {
                double a = (rectpoints[i].x + (rectangle.width / 2)) - x;
                double b = (rectpoints[i].y + (rectangle.height / 2)) - y;
                double c = Math.sqrt(a * a + b * b);
                if (c < circle) {
                    re = true;
                } else {
                    re = false;
                }
            }

        } else {
            re = false;
        }
        return re;
    }
}
