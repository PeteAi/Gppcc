package window;

import AI.Host;
import framework.*;
import objects.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static Controller controller;
    public static boolean iscontroller=false;

    private boolean running = false;
    public static int framesopen;
    public static int updatesopen;
    public static int frameHeigth = 880;
    public static int frameWidth = 1040;

    public static Rectangle StartButton;
    public static Rectangle OptionsButton;
    public static Rectangle QuitButton;

    private Thread thread;
    static int Width, Height, Scale = 1;
    public static final int rasterWidth = 32, rasterHeigth = 32;
    public static int playerani = 2;
    public static int planeani = 0;
    public static int collectsPerChunk = 13;
    public static int collectplaceholder;
    public static boolean debugmode = true;
    public static float coins = 0.0f;
    public static byte doppelsprung = 0;
    public static boolean shoot = true;
    public static final int WALLPOOL = 1025;
    public static final int SHOOTERBULLETPOOL = 0;

    public static int moX = 0;
    public static int moY = 0;

    public static boolean dead = false;
    public static boolean windows = true;

    public static Chunkloader chunkloader;

    public static int mouseX = 0;
    public static int mouseY = 0;
    public static boolean doOnce = false;

    public static int updates = 0;

    public enum STATE {
        MENU,
        GAME
    }

    public static STATE state = STATE.MENU;


    public static int levelX = 0;
    public static int levelY = 0;
    public static int ChunkX = 0;
    public static int ChunkY = 0;

    public static int Tick = 0;

    int enabledObjects = 0;
    static int collectpool = 30;


    //Objects
    public static Handler handler;

    Camera cam;
    static Texture tex;
    private Menu menu;


    private void init() {
        try {
            Controllers.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        for(int i = 0;i<Controllers.getControllerCount();i++){
            Controller tempController = Controllers.getController(i);
            if(tempController.getName().equalsIgnoreCase("Wireless Controller")){
                System.out.println("Controller found");
                iscontroller = true;
                controller = tempController;
            }
        }
        Width = getWidth();
        Height = getHeight();

        tex = new Texture();
        menu = new Menu();

        handler = new Handler();

        cam = new Camera(0, 0);

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler, cam));

        //Player
        handler.object.addFirst(new Player(64, 32, true, 2, 0, 0, handler, ObjectId.Player));
        handler.object.add(new Bullet(0, 0, false, 0, 0, 0, handler, ObjectId.Bullet));
        //ObjectPoolWall

        chunkloader = new Chunkloader(handler, "C:\\Users\\Pete Louis Benz\\Desktop\\Java\\Rpg\\rsc\\Map.png");
        chunkloader.loadMap();
        Host.setup(0, 0, 300, 300);
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
        updates = 0;
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
                System.out.println("FPS: " + frames + " TICKS: " + updates);

                framesopen = frames;
                updatesopen = updates;
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick() {
        handler.tick();
        if (chunkloader.update()) {
            System.out.println("New Chunk");
        }
        if (handler.object.getFirst().getId() == ObjectId.Player) {
            cam.tick(handler.object.getFirst());
        }
        if(iscontroller){
            controller.poll();
            framework.Controller.update();
        }
        if (Tick < 60) {
            Tick++;
        } else {
            Tick = 0;
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
        if (state == STATE.GAME) {
            gd2.translate(cam.getX(), cam.getY()); //Cam start
            handler.render(g);
            gd2.translate(-cam.getX(), -cam.getY());//Cam end
            Host.drawAndUpdate(g);
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
                g.drawString(String.valueOf("List: " + handler.object.size()), tab, space * 9);
                g.drawString(String.valueOf("Shoot: " + shoot), tab, space * 10);


            }
        } else if (state == STATE.MENU) {
            menu.render(g);
        }


        ////////////Draw end
        g.dispose();
        bs.show();
    }


    public static void spawnCollect(int Chx, int Chy) {
        for (int i = handler.object.size() - collectpool; i < handler.object.size(); i++) {
            Random randx = new Random();
            Random randy = new Random();
            int randomX = randx.nextInt(32);
            int randomY = randy.nextInt(32);

            int X = Chx * 1024 + randomX * 32;
            int Y = Chy * 1024 + randomY * 32;
            if (X > 0 && X < 1024 * 32 && Y > 0 && Y < 1024 * 32) {
                handler.object.get(i).setX(X);
                handler.object.get(i).setY(Y);
                handler.object.get(i).setEnabled(true);
            }
        }
    }


    public static Texture getInstance() {
        return tex;
    }

    public static void main(String args[]) {
        new Window(frameWidth, frameHeigth, 1, "RPG", new Game());
        new Animate(2500);
    }
}
