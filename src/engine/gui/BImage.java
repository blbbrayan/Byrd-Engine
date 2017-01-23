package engine.gui;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import engine.action.Resizer;

public class BImage extends JLabel implements ResizeListener{
	private static final long serialVersionUID = 1L;
	
	private Rectangle orgin;
	private ImageIcon orginImage;
	
	public BImage(){}
	public BImage(BScreen screen){
		screen.addGUI(this);
	}
	public BImage(ImageIcon e, int x, int y){
		setBounds(x, y, e.getIconWidth(), e.getIconHeight());
		setOrgin(getBounds());
		setOrginImage(e);
		setIcon(e);
	}
	public BImage(ImageIcon e, int x, int y, BScreen screen){
		setBounds(x, y, e.getIconWidth(), e.getIconHeight());
		setOrgin(getBounds());
		setOrginImage(e);
		setIcon(e);
		screen.addGUI(this);
	}
	public BImage(ImageIcon e, int x, int y, int width, int height){
		setBounds(x, y, width, height);
		setOrgin(getBounds());
		setOrginImage(Resizer.resizeImage(e, width, height));
		setIcon(orginImage);
	}
	public BImage(ImageIcon e, int x, int y, int width, int height , BScreen screen){
		setBounds(x, y, width, height);
		setOrgin(getBounds());
		setOrginImage(Resizer.resizeImage(e, width, height));
		setIcon(orginImage);
		screen.addGUI(this);
	}
	public void setup(ImageIcon orginImage, int x, int y){
		setOrginImage(orginImage);
		setOrgin(new Rectangle(x, y, orginImage.getIconWidth(), orginImage.getIconHeight()));
		setIcon(orginImage);
		setBounds(orgin);
	}
	public Rectangle getOrgin() {
		return orgin;
	}
	public void setOrgin(Rectangle orgin) {
		this.orgin = orgin;
	}
	public ImageIcon getOrginImage() {
		return orginImage;
	}
	public void setOrginImage(ImageIcon orginImage) {
		this.orginImage = orginImage;
	}
	@Override
	public void onResize() {
		setBounds(Resizer.resize(orgin));
		new Thread(new Runnable(){
			public void run(){
				setIcon(Resizer.resizeImage(orginImage, getBounds().width, getBounds().height));
			}
		}).start();	
	}
	
}
