package engine.gui;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JTextArea;

import engine.action.Resizer;

public class BTextArea extends JTextArea implements ResizeListener{
	private static final long serialVersionUID = 1L;
	
	private Rectangle orgin;
	
	public BTextArea(int locationX, int locationY, int sizeX, int sizeY){
		setBounds(locationX,locationY, sizeX, sizeY);
		setOrgin(new Rectangle(locationX, locationY, sizeX, sizeY));
		setEditable(false);
		setLineWrap(true);
		setWrapStyleWord(true);
		setBackground(new Color(220, 220, 220));
	}
	public BTextArea(int locationX, int locationY, int sizeX, int sizeY, BScreen screen){
		setBounds(locationX,locationY, sizeX, sizeY);
		setOrgin(new Rectangle(locationX, locationY, sizeX, sizeY));
		setEditable(false);
		setLineWrap(true);
		setWrapStyleWord(true);
		screen.addGUI(this);
		setBackground(new Color(220, 220, 220));
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
	
	public void addText(String text){
		append(text + "\n");
	}
	public void clearText(){
		this.setText("");
	}
}
