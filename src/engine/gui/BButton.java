package engine.gui;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import engine.action.Resizer;


public class BButton extends JButton implements ResizeListener{
	private static final long serialVersionUID = 1L;
	
	private Rectangle orgin;
	
	public BButton(int x, int y, int width, int height){
		this.setBounds(x, y, width, height);
		setOrgin(x, y, width, height);
		setBackground(new Color(210, 210, 210));
	}
	public BButton(int x, int y, int width, int height, ActionListener actionListener){
		this.setBounds(x, y, width, height);
		setOrgin(x, y, width, height);
		addActionListener(actionListener);
		setBackground(new Color(210, 210, 210));
	}
	public BButton(int x, int y, int width, int height, BScreen screen, ActionListener actionListener){
		this.setBounds(x, y, width, height);
		setOrgin(x, y, width, height);
		addActionListener(actionListener);
		screen.addGUI(this);
		setBackground(new Color(210, 210, 210));
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
