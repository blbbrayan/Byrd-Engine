package engine.game;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Cover implements Serializable {

    private BufferedImage image;
    private String name;
    private String author;
    private String desc;

    public Cover(BufferedImage image, String name, String author, String desc) {
        this.image = image;
        this.name = name;
        this.author = author;
        this.desc = desc;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
