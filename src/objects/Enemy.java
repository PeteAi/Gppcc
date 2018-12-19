package objects;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Game;
import window.Handler;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Enemy extends GameObject {
    private int width = 32, height = 64;
    private float gravity = 0.5f;
    private final float MaxSpeed = 10;
    private Handler handler;
    private LinkedList<Integer> TaX = new LinkedList<>();
    private LinkedList<Integer> TaY = new LinkedList<>();
    private LinkedList<Boolean> TaBool = new LinkedList<>();

    private int index = 0;

    Texture tex = Game.getInstance();

    public Enemy(float x, float y, boolean enabled, int textureTypeX, int textureTypeY, Handler handler, int metadata, ObjectId id) {
        super(x, y, enabled, textureTypeX, textureTypeY, metadata, id);
        this.handler = handler;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if (enabled) {
            Collision();
            if (Game.state == Game.STATE.GAME) {
                if (falling) {
                    if (velY < MaxSpeed) {
                        velY += gravity;
                    }
                }
                x += velX;
                y += velY;
            }
        }
    }

    private void Collision() {
        if (enabled) {
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ObjectId.EnemyMovement) {
                    if (tempObject.getBounds().intersects(getBounds())) {
                        //System.out.println(move);
                    }
                }
                if (tempObject.getId() == ObjectId.Wall) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        //System.out.println("bound");
                        y = tempObject.getY() - height;
                        velY = 0;
                        falling = false;

                    } else {
                        falling = true;
                    }
                    if (getBoundsTop().intersects(tempObject.getBounds())) {
                        y = tempObject.getY() + 32;
                        jumping = false;
                        velY = 0;
                        //System.out.println("Top");

                    }
                    if (getBoundsRight().intersects(tempObject.getBounds())) {
                        x = tempObject.getX() - 35;
                        //System.out.println("Right");

                    }
                    if (getBoundsLeft().intersects(tempObject.getBounds())) {
                        x = tempObject.getX() + 35;
                        //System.out.println("Left");

                    }
                } else if (tempObject.getId() == ObjectId.EnemyMovement) {
                    if (false) {
                        if (genaralBox().intersects(tempObject.getBounds())) {
                            Random random = new Random();
                            int rand2 = random.nextInt(2);
                            int rand3 = random.nextInt(3);
                            //System.out.println("rand"+rand);

                            switch (tempObject.getMetadata()) {
                                case 0:
                                    setVelY(-15);
                                    break;
                                case 1:
                                    setVelX(2);
                                    break;
                                case 2:
                                    setVelX(-2);
                                    break;
                                case 3:
                                    if (rand3 == 0) {
                                        setVelY(-15);
                                    } else if (rand3 == 1) {
                                        setVelX(2);
                                    } else if (rand3 == 2) {
                                        setVelX(-2);
                                    }
                                    break;
                                case 4:
                                    if (rand2 == 0) {
                                        setVelX(2);
                                    } else if (rand2 == 1) {
                                        setVelX(-2);
                                    }
                                case 5:
                                    if (rand2 == 0) {
                                        setVelX(-2);
                                    } else if (rand2 == 1) {
                                        setVelY(-15);
                                    }
                                    break;
                                case 6:
                                    if (rand2 == 0) {
                                        setVelY(2);
                                    } else if (rand2 == 1) {
                                        setVelY(-15);
                                    }
                            }
                        }
                    }
                }

            }
        }
    }


    @Override
    public void render(Graphics g) {
        if (enabled) {
            g.drawImage(tex.sprite[11][0], (int) x, (int) y, null);
            g.drawImage(tex.sprite[11][1], (int) x, (int) y + 32, null);

            g.setColor(Color.RED);
            Graphics2D gd2 = (Graphics2D) g;
            gd2.setColor(Color.red);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + width / 4, (int) y + height / 2, width / 2, height / 2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle(((int) x + (width / 2) - ((width / 2) / 2)), (int) y, (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle(((int) x + width / 4 * 3), (int) (y + width / 2), (int) width / 4, (int) height / 2);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) (y + height / 4), (int) width / 4, (int) height / 2);
    }

    public Rectangle genaralBox() {
        return new Rectangle((int) x, (int) y, width, height);
    }

}
