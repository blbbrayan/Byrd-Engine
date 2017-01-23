package engine.game;

import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import engine.action.Resizer;
import engine.game.manager.GameStateManager;
import engine.game.manager.HoverListener;
import engine.gui.ResizeListener;

public class GameObject implements Serializable, ResizeListener, HoverListener {
    private static final long serialVersionUID = 1L;

    private Rectangle orgin;
    private Rectangle bounds;
    private boolean visible;
    private boolean hovering;

    public GameObject() {
        this.bounds = new Rectangle(0, 0, 0, 0);
        init();
    }

    public GameObject(int x, int y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);
        init();
    }

    public void init() {
        setVisible(true);
        orgin = getBounds();
    }

    public void render(Graphics2D g) {
        if (visible)
            onRender(g);
    }

    public void onRender(Graphics2D g) {
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.orgin = bounds;
        onResize();
    }

    public void setBounds(int x, int y, int width, int height) {
        this.orgin = new Rectangle(x, y, width, height);
        onResize();
    }

    public void setLocation(int x, int y) {
        orgin = new Rectangle(x, y, orgin.width, orgin.height);
        onResize();
    }

    public void setSize(int width, int height) {
        orgin = new Rectangle(orgin.x, orgin.y, width, height);
        onResize();
    }

    public void move(String direction, int value) {
        direction = direction.toLowerCase();
        ArrayList<String> directions = new ArrayList<>(Arrays.asList(direction.split(" ")));

        for (String d : directions) {
            switch (d) {
                case "left":
                    setX(getX() - value);
                    break;
                case "right":
                    setX(getX() + value);
                    break;
                case "up":
                    setY(getY() - value);
                    break;
                case "down":
                    setY(getY() + value);
                    break;
                case "west":
                    setX(getX() - value);
                    break;
                case "east":
                    setX(getX() + value);
                    break;
                case "north":
                    setY(getY() - value);
                    break;
                case "south":
                    setY(getY() + value);
                    break;
                case "horizontal":
                    setX(getX() + value);
                    break;
                case "vertical":
                    setY(getY() + value);
                    break;
            }
        }
        onResize();
    }

    public int getX() {
        return this.orgin.x;
    }

    public void setX(int x) {
        orgin = new Rectangle(x, orgin.y, orgin.width, orgin.height);
        onResize();
    }

    public int getY() {
        return this.orgin.y;
    }

    public void setY(int y) {
        orgin = new Rectangle(orgin.x, y, orgin.width, orgin.height);
        onResize();
    }

    public int getWidth() {
        return this.orgin.width;
    }

    public void setWidth(int width) {
        orgin = new Rectangle(orgin.x, orgin.y, width, orgin.height);
        onResize();
    }

    public int getHeight() {
        return this.orgin.height;
    }

    public void setHeight(int height) {
        orgin = new Rectangle(orgin.x, orgin.y, orgin.width, height);
        onResize();
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void onResize() {
        this.bounds = new Rectangle(Resizer.resize(orgin));
    }

    public Rectangle getOrgin() {
        return orgin;
    }

    public void setOrgin(Rectangle orgin) {
        this.orgin = orgin;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

    @Override
    public void onHoverEnter() {

    }

    @Override
    public void onHoverExit() {

    }
}