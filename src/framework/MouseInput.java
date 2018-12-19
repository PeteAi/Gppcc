package framework;

import window.Camera;
import window.Handler;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static window.Game.*;

public class MouseInput extends MouseAdapter {
    private Handler handler;
    private Camera cam;

    public MouseInput(Handler handler, Camera cam) {
        this.handler = handler;
        this.cam = cam;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (state == STATE.GAME) {
            mouseX = (int) (e.getX() + cam.getX() * -1);
            mouseY = (int) (e.getY() + cam.getY() * -1);

            moX = mouseX;
            moY = mouseY;
            doOnce = true;
            handler.object.get(1).setEnabled(true);
            handler.object.get(1).setX(handler.object.get(0).x);
            handler.object.get(1).setY(handler.object.get(0).y);

            System.out.println("Bullet");
        } else if (state == STATE.MENU) {
            Point point = new Point(e.getX(), e.getY());
            if (StartButton.contains(point)) {
                state = STATE.GAME;
                System.out.println(state);
            }
            if (OptionsButton.contains(point)) {
                state = STATE.GAME;
                System.out.println(state);
            }
            if (QuitButton.contains(point)) {
                System.exit(0);
            }
        }
    }
}
