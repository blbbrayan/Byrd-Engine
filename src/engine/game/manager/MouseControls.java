package engine.game.manager;

import engine.action.Resizer;
import engine.game.GameObject;
import engine.gui.BFrame;
import engine.gui.GUIListener;
import engine.gui.ResizeListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;

public class MouseControls implements MouseListener, Serializable {
	
	GameStateManager gsm;

	
	public MouseControls(GameStateManager gsm){
		this.gsm = gsm;
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		ArrayList<GameObject> clickedObjects = new ArrayList<>();
		for(ResizeListener e: BFrame.gui){
			if(e instanceof GameObject){
				GameObject obj = (GameObject) e;
				if(obj.getBounds().contains(arg0.getPoint())){
					clickedObjects.add(obj);
				}
			}
		}
		gsm.mousePressed(new GameClickEvent(clickedObjects, arg0));
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		ArrayList<GameObject> clickedObjects = new ArrayList<>();
		for(ResizeListener e: BFrame.gui){
			if(e instanceof GameObject){
				GameObject obj = (GameObject) e;
				if(obj.getBounds().contains(arg0.getPoint())){
					clickedObjects.add(obj);
				}
			}
		}
		gsm.mouseReleased(new GameClickEvent(clickedObjects, arg0));
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {}
	
}
