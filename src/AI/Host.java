package AI;

import processing.core.PVector;

import java.awt.*;

public class Host {
    public static Population test;
    static PVector goal = new PVector(100, 300);
    public static int width, height, x, y;

    public static void setup(int xc, int yc, int widthc, int heightc) {
        width = widthc;
        height = heightc;
        x = xc;
        y = yc;
        test = new Population(1000);//create a new population with 1000 members
    }


    public static void drawAndUpdate(Graphics g) {
        //draw goal
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.setColor(Color.RED);
        g.drawOval((int) goal.x, (int) goal.y, 10, 10);

        //draw obstacle(s)
        g.setColor(Color.BLUE);

        g.fillRect(0, 300, 600, 10);


        if (test.allDotsDead()) {
            //genetic algorithm
            test.calculateFitness();
            test.naturalSelection();
            test.mutateDemBabies();
        } else {
            //if any of the dots are still alive then update and then show them
            test.update();
            test.show(g);
        }

    }
}
