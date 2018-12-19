package framework;

import window.Game;


public class Controller {
    public static void update() {
        switch (Game.state) {
            case GAME:
                Game.handler.object.getFirst().setVelX((float) getLeftAxisX());
                Game.handler.object.getFirst().setVelY((float) getLeftAxisY());
                break;
            case MENU:
                if (XisPressed()) {
                    Game.state = Game.STATE.GAME;
                }
                break;
        }


    }

    public static double getLeftAxisX() {
        return (double) Game.controller.getAxisValue(3) * 5;
    }

    public static double getLeftAxisY() {
        return (double) Game.controller.getAxisValue(2) * 5;
    }

    public static boolean XisPressed() {
        return Game.controller.isButtonPressed(1);
    }

}
