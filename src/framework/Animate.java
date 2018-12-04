package framework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static window.Game.planeani;
import static window.Game.playerani;

public class Animate implements ActionListener {


    public Animate(int delay) {
        Timer timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(playerani <=3){
            playerani += 1;
            //System.out.println(playerani);
        } else {
            playerani = 2;
        }
        if(planeani <=0){
            planeani +=1;
        }else{
            planeani = 0;
        }
    }
}
