package framework;

import window.BufferedImageLoader;

import objects.*;
import window.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static window.Game.*;

public class Chunkloader {

    public static GameObject[][] Map = new GameObject[1024][1024];
    private static BufferedImage level = null;
    private Handler handler;

    public Chunkloader(Handler handler, String file) {
        this.handler = handler;
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.LoadImage(file);
    }

    public static void renderChunk(Graphics g) {
        for (int yy = ChunkY * 32; yy < ChunkY * 32 + 32; yy++) {
            for (int xx = ChunkX * 32; xx < ChunkX * 32 + 32; xx++) {
                GameObject tempObject = Chunkloader.Map[xx][yy];
                tempObject.render(g);
            }
        }
    }

    public void loadMap() {
        for (int yy = 0; yy < 1024; yy++) {
            for (int xx = 0; xx < 1024; xx++) {
                int pixel = level.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                GameObject tempObject = null;

                if (red <= 2 && green != 0) {
                    tempObject = new Wall(xx * 32, yy * 32, true, red, green, 0, ObjectId.Wall);
                } else if (red == 13) {
                    tempObject = new EnemyMove(xx * 32, yy * 32, true, 0, 0, green, ObjectId.EnemyMovement);
                } else if (red == 3 || red == 4) {
                    tempObject = new Ground(xx * 32, yy * 32, true, red, green, 0, ObjectId.Ground);
                }
                Map[xx][yy] = tempObject;
            }

        }
    }

    public boolean update() {
        int CX = ChunkX;
        int CY = ChunkY;
        ChunkX = (int) handler.object.getFirst().getX() / 1024;
        ChunkY = (int) handler.object.getFirst().getY() / 1024;

        if (CX != ChunkX || CY != ChunkY) {
            return true;
        } else {
            return false;
        }
    }

}
