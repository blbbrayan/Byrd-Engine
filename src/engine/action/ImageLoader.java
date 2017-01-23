package engine.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage load(Class<?> classfile, String path){
		URL url = classfile.getResource(path);
		BufferedImage img = null;
		try{
			img = ImageIO.read(url);
		}catch(IOException e){
			e.printStackTrace();
		}
		return img;
	}
	
}
