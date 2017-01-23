package engine.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.JLabel;

import engine.action.Resizer;

public class BScreen extends JLabel implements ResizeListener{
	private static final long serialVersionUID = 1L;
	
	private Rectangle orgin;
	
	private GUIListener guiListener;
	
	public BScreen(BFrame e){
		setBounds(0, 0, BFrame.screenWidth, BFrame.screenHeight);
		setOrgin(0, 0, BFrame.screenWidth, BFrame.screenHeight);
		setLayout(null);
		e.add(this);
		e.addGui(this);
		guiListener = e;
		setBackground(new Color(110, 110, 110));
	}
	public BScreen(BMenu e){
		setBounds(0, 0, BFrame.screenWidth, BFrame.screenHeight);
		setOrgin(0, 0, BFrame.screenWidth, BFrame.screenHeight);
		setLayout(null);
		e.add(this);
		setBackground(new Color(110, 110, 110));
	}
	public BScreen(int x, int y, int width, int height, BScreen screen){
		setBounds(x, y, width, height);
		setOrgin(x, y, width, height);
		setLayout(null);
		screen.addGUI(this);
		setGuiListener(screen.getGuiListener());
		setBackground(new Color(110, 110, 110));
	}
	public void addGUI(Component gui){
		if(gui instanceof ResizeListener){
			if(guiListener != null){
				guiListener.onAddGUI(gui);
			}else{
				System.out.println("Invalid GUI Listener");
			}
			add(gui);
		}
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
	public GUIListener getGuiListener() {
		return guiListener;
	}
	public void setGuiListener(GUIListener guiListener) {
		this.guiListener = guiListener;
	}
}
