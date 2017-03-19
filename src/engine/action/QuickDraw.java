package engine.action;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class QuickDraw  implements Serializable {
	
	public static void rect(Rectangle r, boolean fill, Graphics2D g){
		if(!fill)
			g.drawRect(r.x, r.y, r.width, r.height);
		else
			g.fillRect(r.x, r.y, r.width, r.height);
	}
	public static void circle(Rectangle r, boolean fill, Graphics2D g){
		if(!fill)
			g.drawOval(r.x, r.y, r.width, r.height);
		else
			g.fillOval(r.x, r.y, r.width, r.height);
	}
	public static void image(BufferedImage img, Rectangle r, Graphics2D g){
		g.drawImage(img, r.x, r.y, r.width, r.height, null);
	}

	public static void drawString(int size, int x, int y, String text, Graphics2D g){
		g.setFont(new Font("Arial", Font.BOLD, size));
		g.drawString(text, x, y + size);
	}

	public static void drawStringStack(int size, int x, int y, int space, String[] text, Graphics2D g){
		g.setFont(new Font("Arial", Font.BOLD, size));
		for(int i = 0; i < text.length; i++) {
			g.drawString(text[i], x, y + ((size+space) * (i+1)));
		}
	}
	
}
