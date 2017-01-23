package engine.gui;

import java.awt.Rectangle;

import javax.swing.JComboBox;

import engine.action.Resizer;

public class BDropDown extends JComboBox<Object> implements ResizeListener{
	private static final long serialVersionUID = 1L;
	
	private Rectangle orgin;
	
	public BDropDown(int x, int y, int width, int height){
		setBounds(x, y, width, height);
		setOrgin(x, y, width, height);
	}
	public BDropDown(int x, int y, int width, int height, BScreen screen){
		setBounds(x, y, width, height);
		setOrgin(x, y, width, height);
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
