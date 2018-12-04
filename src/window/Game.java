package window;

import framework.Animate;
import framework.KeyInput;
import framework.ObjectId;
import framework.Texture;
import objects.Collect;
import objects.Player;
import objects.Wall;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    public static int framesopen;
    public static int updatesopen;

    private Thread thread;
    public static int Width, Height, Scale = 1;
    boolean player = true;
    public static int playerani = 2;
    public static int planeani = 0;
    public static boolean nearByCollect = false;
    public static int pickUpPlaneX;
    public static int pickUpPlaneY;
    public static int collectIndex = 0;
    public static int collectamount;
    public static int collectsPerChunk = 13;
    public static int collectplaceholder;
    public static boolean debugmode = true;
    public static float coins = 0.0f;

    static int ChunkX = 2;
    static int ChunkY = 2;
    int ChunkXCurrent = 1;
    int ChunkYCurrent = 1;
    int enabledObjects = 0;
    int walkedChunks = 0;
    static int collectpool = 30;

    private boolean load_chunk = true;


    private BufferedImage level = null;

    //Objects
    static Handler handler;
    Camera cam;
    static Texture tex;


    private void init() {
        Width = getWidth();
        Height = getHeight();

        tex = new Texture();

        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.LoadImage("/Users/Home/Desktop/Java/Rpg/rsc/Map.png");
        //level = loader.LoadImage("H:\\Desktop\\Java\\Rpg\\rsc\\Map.png");

        handler = new Handler();
        cam = new Camera(0, 0);

        this.addKeyListener(new KeyInput(handler));
        //Player
        handler.object.addFirst(new Player(100, 100, true, 2, handler, ObjectId.Player));
        handler.object.get(0).setEnabled(true);
        //ObjectPoolWall
        for (int i = 0; i < 500; i++) {
            handler.addObject(new Wall(0, 0, false, 0, ObjectId.Wall));
        }
        for (int i = 0; i < collectpool; i++) {
            handler.addObject(new Collect(0, 0, false, 0, ObjectId.Collect));
        }
        spawnCollect(0,0);

    }


    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames + " TICKS: " + updates);

                framesopen = frames;
                updatesopen = updates;
                frames = 0;
                updates = 0;
            }

        }
    }

    private void tick() {
        handler.tick();
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ObjectId.Player) {
                cam.tick(handler.object.get(i));
                ChunkX = (int) ((handler.object.get(i).getX()) / 1024);
                ChunkY = (int) ((handler.object.get(i).getY()) / 1024);
            }
        }
        if (!(ChunkXCurrent == ChunkX) || !(ChunkYCurrent == ChunkY)) {
            load_chunk = true;
            for (int i = handler.object.size() - 31; i > 0; i--) {
                handler.object.get(i).setEnabled(false);
            }
            ChunkXCurrent = ChunkX;
            ChunkYCurrent = ChunkY;
        }

    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ////////////Draw here
        //background
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D gd2 = (Graphics2D) g;
        gd2.scale(Scale, Scale);
        gd2.translate(cam.getX(), cam.getY()); //Cam start
        int indexWall = 1;
        if(load_chunk){
            walkedChunks +=1;
        }
        if(walkedChunks>=1) {
            spawnCollect(ChunkX,ChunkY);
            walkedChunks =0;
        }
        for (int yy = ChunkY * 32 - 32; yy < ChunkY * 32 + 64; yy++) {
            for (int xx = ChunkX * 32 - 32; xx < ChunkX * 32 + 64; xx++) {

                if (xx >= 0 && yy >= 0) {
                    int pixel = level.getRGB(xx, yy);
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = (pixel) & 0xff;

                    if (red == 0 && green == 0 && blue == 0) {
                        g.drawImage(tex.sprite[18], xx * 32, yy * 32, 32, 32, null);
                    } else if (red == 255 && green == 255 && blue == 255) {
                        g.setColor(new Color(100, 3, 6));
                        g.fillRect(xx * 32, yy * 32, 32, 32);

                        if (load_chunk) {
                            if ((xx >= ChunkX * 32 - 1) && (xx <= ChunkX * 32 + 33) && (yy >= ChunkY * 32 - 1) && (yy <= ChunkY * 32 + 33)) {
                                handler.object.get(indexWall).setX(xx * 32);
                                handler.object.get(indexWall).setY(yy * 32);
                                handler.object.get(indexWall).setEnabled(true);

                                indexWall += 1;
                            }
                        }
                    }
                }
            }
        }
        load_chunk = false;

        handler.render(g);

        g.setColor(Color.red);
        g.drawRect(pickUpPlaneX, pickUpPlaneY, 32, 32);

        gd2.translate(-cam.getX(), -cam.getY());//Cam end

        if (debugmode) {
            byte space = 15;
            byte tab = 10;
            g.setColor(Color.gray);
            g.fillRect(0, 0, 150, space * 10 + 10);

            g.setColor(Color.black);
            g.drawString(String.valueOf("FPS: " + framesopen), tab, space);
            g.drawString(String.valueOf("Updates/s" + updatesopen), tab, space * 2);
            g.drawString(String.valueOf("Player: " + handler.object.getFirst().getEnabled()), tab, space * 3);
            enabledObjects = 0;
            for (int i = 0; i < handler.object.size(); i++) {
                if (handler.object.get(i).getEnabled()) {
                    enabledObjects += 1;
                }
            }
            g.drawString(String.valueOf("Objects: " + enabledObjects), tab, space * 4);
            g.drawString(String.valueOf("ChunkX: " + ChunkX + "(" + handler.object.getFirst().getX() + ")"), tab, space * 5);
            g.drawString(String.valueOf("ChunkY: " + ChunkY) + "(" + handler.object.getFirst().getY() + ")", tab, space * 6);
            g.drawString(String.valueOf("Money: " + coins), tab, space * 7);
            g.drawString(String.valueOf("walked: " + walkedChunks), tab, space * 8);
            g.drawString(String.valueOf("List: " + handler.object.size()), tab, space * 9);



        }
        ////////////Draw end
        g.dispose();
        bs.show();
    }




    public static void spawnCollect(int Chx, int Chy) {
        for (int i = handler.object.size()-collectpool;i<handler.object.size();i++) {
            Random randx = new Random();
            Random randy = new Random();
            int randomX = randx.nextInt(32);
            int randomY = randy.nextInt(32);

            int X =Chx*1024 + randomX*32;
            int Y =Chy*1024 + randomY*32;
            if (X>0&&X<1024*32&&Y>0&&Y<1024*32) {
                handler.object.get(i).setX(X);
                handler.object.get(i).setY(Y);
                System.out.println("X: "+X);
                System.out.println("Y: "+Y);
                handler.object.get(i).setEnabled(true);
            }
        }
    }


    public static Texture getInstance() {
        return tex;
    }

    public static void main(String args[]) {
        new Window(1040,880,1,"RPG", new Game());
        new Animate(250);
    }
}
