package engine.game.manager;

import engine.action.Resizer;
import engine.game.GameObject;
import engine.gui.BFrame;
import engine.gui.ResizeListener;

import java.awt.*;
import java.io.Serializable;

public class HoverControls implements Runnable, Serializable {
    private boolean on;

    public void start(){
        this.on = true;
        new Thread(this).start();
    }

    public void stop(){
        this.on = false;
    }

    public boolean isOn() {
        return on;
    }

    public void run() {
        while (on) try {
            for (ResizeListener obj : BFrame.gui)
                if (obj instanceof GameObject) {
                    GameObject e = (GameObject) obj;
                    boolean hovering = e.isHovering();
                    e.setHovering(e.getBounds().contains(MouseInfo.getPointerInfo().getLocation()));
                    if (e.isHovering()) {
                        if (!hovering) e.onHoverEnter();
                    } else if (hovering) e.onHoverExit();
                }
            Thread.sleep(50);
        } catch (Exception e) {}
    }
}
