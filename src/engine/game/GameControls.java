package engine.game;

import java.util.ArrayList;

public class GameControls {

    public static ArrayList<GameControls> controls = new ArrayList<>();

    private String name;
    private int code;

    public GameControls(String name, int code){
        setName(name);
        setCode(code);
    }

    public static boolean get(String name){
        for(GameControls e: controls){
            if (e.getName().equals(name))
                return true;
        }
        return false;
    }

    public static String get(int code){
        for(GameControls e: controls){
            if(e.getCode() == code)
                return e.getName();
        }
        return null;
    }

    public static void add(String name, int code){
        controls.add(new GameControls(name, code));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
