package engine.action;

import engine.gui.BFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Resizer  implements Serializable {
	
	public static int resize(int oldInt, int newInt, int obj){
		if(oldInt != 0 && newInt != 0)
			return ((int)(obj * ((double)newInt / (double)oldInt)));
		return ((int)(obj * 0));
	}

	public static Rectangle resize(Rectangle obj){
		Rectangle oldSize = BFrame.appSize;
		Rectangle newSize = BFrame.screenSize;
		Rectangle newObj = new Rectangle();
		newObj.x = resize(oldSize.width, newSize.width, obj.x);
		newObj.y = resize(oldSize.height, newSize.height, obj.y);
		newObj.width = resize(oldSize.width, newSize.width, obj.width);
		newObj.height = resize(oldSize.height, newSize.height, obj.height);
		return newObj;
	}

	public static Point resize(Point obj){
		Rectangle oldSize = BFrame.appSize;
		Rectangle newSize = BFrame.screenSize;
		return new Point(resize(oldSize.width, newSize.width, obj.x), resize(oldSize.height, newSize.height, obj.y));
	}

	public Rectangle unsize(Rectangle obj){
		Rectangle oldSize = BFrame.appSize;
		Rectangle newSize = BFrame.screenSize;
		Rectangle newObj = new Rectangle();
		newObj.x = resize(newSize.width, oldSize.width, obj.x);
		newObj.y = resize(newSize.height, oldSize.height, obj.y);
		newObj.width = resize(newSize.width, oldSize.width, obj.width);
		newObj.height = resize(newSize.height, oldSize.height, obj.height);
		return newObj;
	}

	public static Point unsize(Point obj){
		Rectangle oldSize = BFrame.appSize;
		Rectangle newSize = BFrame.screenSize;
		return new Point(resize(newSize.width, oldSize.width, obj.x), resize(newSize.height, oldSize.height, obj.y));
	}
	
	public static ImageIcon resizeImage(ImageIcon imageIcon, int width, int height){
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_FAST);
		imageIcon = new ImageIcon(newimg);  // transform it back
		return imageIcon;
	}
	
	public static ImageIcon flipImage(ImageIcon icon) {
		BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), Transparency.TRANSLUCENT);
		Graphics g = img.createGraphics();
		icon.paintIcon(null, g, 0,0);
		g.dispose();
        int w = img.getWidth();  
        int h = img.getHeight();  
        BufferedImage flippedImage = new BufferedImage(w, h, Transparency.TRANSLUCENT);  
        g = flippedImage.createGraphics();  
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);  
        g.dispose();  
        return new ImageIcon(flippedImage);  
    }
	
	public static BufferedImage flipImage(BufferedImage img){
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
            AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(img, null);
	}
	
}
