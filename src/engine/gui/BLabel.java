package engine.gui;
import java.awt.Rectangle;

import javax.swing.JLabel;

import engine.action.Resizer;


public class BLabel extends JLabel implements ResizeListener{
	private static final long serialVersionUID = 1L;
	
	private Rectangle orgin;
	
	public BLabel(String text,int x,int y,int width,int height){
		this.setBounds(x, y, width, height);
		setOrgin(x, y, width, height);
		this.setText(text);
	}
	public BLabel(String text,int x,int y,int width,int height, BScreen screen){
		this.setBounds(x, y, width, height);
		setOrgin(x, y, width, height);
		this.setText(text);
		screen.addGUI(this);
	}
	public void onResize(){
		setBounds(Resizer.resize(orgin));
	}
	public Rectangle getOrgin() {
		return orgin;
	}
	public void setOrgin(Rectangle orgin) {
		this.orgin = orgin;
	}
	public void setOrgin(int x, int y, int width, int height) {
		this.orgin = new Rectangle(x, y, width, height);
	}
}
