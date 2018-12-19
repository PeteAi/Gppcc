package framework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static window.Game.planeani;
import static window.Game.playerani;
import static window.Game.shoot;

public class Animate implements ActionListener {



    public Animate(int delay) {
        Timer timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
