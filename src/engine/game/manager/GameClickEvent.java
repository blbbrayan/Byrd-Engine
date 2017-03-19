package engine.game.manager;

import engine.action.Resizer;
import engine.game.GameObject;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class GameClickEvent implements Serializable{

    private MouseEvent mouseEvent;
    private ArrayList<GameObject> clickedObjects;
    private Point clickLocation;

    public GameClickEvent(ArrayList<GameObject> clickedObjects, MouseEvent mouseEvent) {
        this.clickedObjects = clickedObjects;
        this.mouseEvent = mouseEvent;
        this.clickLocation = Resizer.unsize(mouseEvent.getPoint());
    }

    public MouseEvent getMouseEvent() {
        return mouseEvent;
    }

    public ArrayList<GameObject> getClickedObjects() {
        return clickedObjects;
    }

    public Point getClickLocation() {
        return clickLocation;
    }

    public static Point getMouseLocation(){return Resizer.unsize(MouseInfo.getPointerInfo().getLocation());}

}
