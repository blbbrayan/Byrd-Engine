package engine.game.manager;

public interface Controller {
	
	void keyPressed(String e);
	void keyReleased(String e);
	
	void mousePressed(GameClickEvent e);
	void mouseReleased(GameClickEvent e);

	void onFocusLost();
	void onFocusGained();

}
