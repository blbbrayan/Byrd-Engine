package engine.game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import engine.action.QuickDraw;
import engine.action.TextReader;
import engine.game.manager.Controller;
import engine.game.manager.GameClickEvent;
import engine.game.manager.GameStateManager;

public abstract class GameState implements Controller{

	private String name;
	private boolean focus;
	private ArrayList<GameObject> renderer;
	
	public GameState(String name){
		setName(name);
		renderer = new ArrayList<>();
	}
	
	public abstract void render(Graphics2D g);
	public abstract void onOpen();
	public abstract void onClose();

	public void toggleGameObjects(boolean add, GameObject[] gameObjects){
		for(GameObject e: gameObjects)
			if(add) {
				GameStateManager.guiListener.onAddGUI(e);
				renderer.add(e);
			}
			else {
				GameStateManager.guiListener.onRemoveGUI(e);
				renderer.remove(e);
			}
	}
	public void toggleGameObjects(boolean add, ArrayList<GameObject> gameObjects){
		for(GameObject e: gameObjects)
			if(add) {
				GameStateManager.guiListener.onAddGUI(e);
				renderer.add(e);
			}
			else {
				GameStateManager.guiListener.onRemoveGUI(e);
				renderer.remove(e);
			}
	}

	public void addGameObject(GameObject e){
		GameStateManager.guiListener.onAddGUI(e);
		renderer.add(e);
	}
	public void removeGameObject(GameObject e){
		GameStateManager.guiListener.onRemoveGUI(e);
		renderer.remove(e);
	}
	public GameObject newGameObject(){
		GameObject e = new GameObject() {
			public void onRender(Graphics2D g) {
				if(this.getBounds() != null)
					QuickDraw.circle(this.getBounds(), false, g);
			}
		};
		GameStateManager.guiListener.onAddGUI(e);
		renderer.add(e);
		return e;
	}

	public void mousePressed(GameClickEvent e) {}

	public void mouseReleased(GameClickEvent e) {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void onFocusGained() {
		this.focus = true;
	}

	public void onFocusLost() {
		this.focus = false;
	}

	public boolean hasFocus() {
		return focus;
	}

	public ArrayList<GameObject> getRenderer() {
		return renderer;
	}
	public void setRenderer(ArrayList<GameObject> renderer) {
		this.renderer = renderer;
	}
	protected void renderObjects(Graphics2D g){
		try {
			for (GameObject e : renderer) {
				e.render(g);
			}
		}catch (Exception e){

		}
	}
}
