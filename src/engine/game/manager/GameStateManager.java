package engine.game.manager;

import engine.game.GameState;
import engine.gui.BFrame;
import engine.gui.GUIListener;
import engine.gui.ResizeListener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> states;

    private int selectedState;
    private Controller controller;

    public static HoverControls hoverControls;
    public static GUIListener guiListener;

    public GameStateManager(BFrame e) {
    	guiListener = e;
        hoverControls = new HoverControls();
        hoverControls.start();
        states = new ArrayList<>();
        selectedState = -1;
        e.addKeyListener(new InputControls(this));
        e.requestFocus();
        e.addMouseListener(new MouseControls(this));
        FocusListener focusListener = new FocusListener() {
            public void focusGained(FocusEvent event) {
               GameStateManager.this.focusGained();
            }

            public void focusLost(FocusEvent event) {
                GameStateManager.this.focusLost();
                e.requestFocus();
            }
        };
        e.addFocusListener(focusListener);
    }

    public void focusGained(){
        if (getController() != null)
            getController().onFocusGained();
    }

    public void focusLost(){
        if (getController() != null)
            getController().onFocusLost();
    }

    public void mousePressed(GameClickEvent e) {
        if (getController() != null)
            getController().mousePressed(e);
    }

    public void mouseReleased(GameClickEvent e) {
        if (getController() != null)
            getController().mouseReleased(e);
    }

    public void keyPressed(String e) {
        if (getController() != null)
            getController().keyPressed(e);
    }

    public void keyReleased(String e) {
        if (getController() != null)
            getController().keyReleased(e);
    }

    public int getSelectedState() {
        return selectedState;
    }

    public GameState getState() {
        return states.get(selectedState);
    }

    public GameState getState(String name) {
        for (GameState e : states) {
            if (e.getName().equals(name))
                return e;
        }
        return null;
    }

    public ArrayList<GameState> getStates() {
        return states;
    }

    public void addState(GameState state) {
        this.states.add(state);
    }

    public void closeState() {
        if (selectedState != -1)
            getState().onClose();
        selectedState = -1;
        setController(null);
    }

    public void setState(String name) {
        if (selectedState != -1)
            getState().onClose();
        selectedState = states.indexOf(getState(name));
        setController(getState());
        getState().onOpen();
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


}
