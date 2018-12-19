package window;

import framework.GameObject;
import framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();

    private GameObject tempObject;

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);
            tempObject.tick(object);
        }
    }
    public void render(Graphics g) {

        for (int i = 2; i < object.size(); i++) {
            tempObject = object.get(i);
            tempObject.render(g);
        }
        object.get(0).render(g);
        object.get(1).render(g);
        if(tempObject.getId()== ObjectId.Goal){
            tempObject.render(g);
        }
    }
    public void addObject(GameObject object) {
        this.object.add(object);
    }

}
