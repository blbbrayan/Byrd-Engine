package engine.gui;

import java.awt.Component;

import engine.game.GameObject;

public interface GUIListener {

	void onAddGUI(Component e);
	void onAddGUI(GameObject e);

	void onRemoveGUI(Component e);
	void onRemoveGUI(GameObject e);
	
}
