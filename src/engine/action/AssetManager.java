package engine.action;

import engine.sound.BClip;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class AssetManager  implements Serializable {

    public static ArrayList<BufferedImage> sprites = new ArrayList<>();
    public static ArrayList<String> spriteNames = new ArrayList<>();

    public static int amountLoaded(){
        return sprites.size();
    }

    public static BufferedImage get(String fileLocation, String id) {
        for (String e : spriteNames) {
            if (e.equals(id))
                return sprites.get(spriteNames.indexOf(e));
        }
        AssetManager.load(fileLocation, id);
        for (String e : spriteNames) {
            if (e.equals(id))
                return sprites.get(spriteNames.indexOf(e));
        }
        return null;
    }

    public static void clear() {
        sprites = new ArrayList<>();
        spriteNames = new ArrayList<>();
    }

    public static void load(String fileLocation, String id) {
        try {
            sprites.add(ImageIO.read(new File(fileLocation)));
            spriteNames.add(id);
        } catch (Exception e) {
            System.out.println("Byrd Engine - Load Error: \"" + fileLocation + "\" was not loaded");
        }
    }

    public static BClip getClip(String fileLocation) {
        try {
            File f = new File(fileLocation);
            if (f.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(f);
                // load the sound into memory (a Clip)
                Clip clip = AudioSystem.getClip();
                clip.open(sound);
                return new BClip(clip);
            } else {
                System.out.println("Sound file: \"" + fileLocation + "\" does not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
