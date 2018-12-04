package framework;

import window.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static window.Game.*;

public class KeyInput extends KeyAdapter {

    Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        for (int i = 0; i < handler.object.size();i++) {
            GameObject tempOpject = handler.object.get(i);

            if(tempOpject.getId() == ObjectId.Player) {
                if(code == KeyEvent.VK_D) {tempOpject.setVelX(5);playerani = 2;}
                if(code == KeyEvent.VK_A) {tempOpject.setVelX(-5);playerani = 3;}
                if(code == KeyEvent.VK_W) {tempOpject.setVelY(-5);playerani = 2;}
                if(code == KeyEvent.VK_S) {tempOpject.setVelY(5);playerani = 3;}
            }
        }
        if(code == KeyEvent.VK_I) {
            for (int i1 = 0; i1<collectsPerChunk; i1++) {
                System.out.println("nr"+i1+": "+collectplaceholder);
            }
        }
        /*if(nearByCollect){
            if(code == KeyEvent.VK_E){
                for(int i = 0; i<collectsPerChunk;i++) {
                    if (collectplaceholder != 0) {
                        handler.object.remove(collectplaceholder);
                        //System.out.println(collectplaceholder[i]);
                        //nearByCollect = false;
                    }
                }

                coins += 0.08;
                System.out.println(coins);
            }
        }*/

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        for (int i = 0; i < handler.object.size();i++) {
            GameObject tempOpject = handler.object.get(i);

            if(tempOpject.getId() == ObjectId.Player) {
                if(code == KeyEvent.VK_D) {tempOpject.setVelX(0);playerani = 2;}
                if(code == KeyEvent.VK_A) {tempOpject.setVelX(0);playerani = 2;}
                if(code == KeyEvent.VK_W) {tempOpject.setVelY(0);playerani = 2;}
                if(code == KeyEvent.VK_S) {tempOpject.setVelY(0);playerani = 2;}
            }
        }
    }
}
