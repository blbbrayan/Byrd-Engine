package engine.game.manager;

import engine.game.GameControls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputControls extends KeyAdapter{
		
	GameStateManager gsm;
	
	public InputControls(GameStateManager gsm){
		this.gsm = gsm;
	}
	
	public void keyPressed(KeyEvent e) {
		if(GameControls.get(e.getKeyCode()) != null)
			gsm.keyPressed(GameControls.get(e.getKeyCode()));
	}
	public void keyReleased(KeyEvent e) {
		if(GameControls.get(e.getKeyCode()) != null)
			gsm.keyReleased(GameControls.get(e.getKeyCode()));
	}
	
}
