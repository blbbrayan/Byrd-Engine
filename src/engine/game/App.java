package engine.game;

import engine.action.TextReader;
import engine.game.manager.GameStateManager;
import engine.gui.BFrame;

import java.awt.*;
import java.io.File;
import java.util.Random;

public class App extends BFrame {

    public static GameStateManager gsm;
    public static GameCanvas canvas;

    public static String appName;

    public App(String name, int width, int height, int screenType) {
        super(name, width, height, screenType);
        appName = name;
        gsm = new GameStateManager(this);
        canvas = new GameCanvas(60, this) {
            public void draw(Graphics2D graphics2D) {
                gsm.getState().render(graphics2D);
            }
        };
    }
    public App(String name, int width, int height, int screenType, int fps) {
        super(name, width, height, screenType);
        appName = name;
        gsm = new GameStateManager(this);
        canvas = new GameCanvas(fps, this) {
            public void draw(Graphics2D graphics2D) {
                gsm.getState().render(graphics2D);
            }
        };
    }

    public static void clearErrors(){
        File index = new File(App.appName + "errors");
        String[]entries = index.list();
        for(String s: entries){
            File currentFile = new File(index.getPath(),s);
            currentFile.delete();
        }
    }

    public static void throwError(String e){
        if(App.appName != null)
            TextReader.write(App.appName + "errors/" + new Random().nextInt(), e.toString());
    }

    public static void addControl(String name, int key){
        GameControls.add(name, key);
    }
    public static void addState(GameState e){
        gsm.addState(e);
    }
    public static void setState(String stateName){
        gsm.setState(stateName);
    }

    public static void start(){
        canvas.start();
    }
    public static void pause(){
        canvas.stop();
    }
    public static void resume(){
        canvas.start();
    }

}

