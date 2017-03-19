package engine.gui;

import engine.game.GameObject;

import java.awt.*;

public interface GUIListener {

	void onAddGUI(Component e);
	void onAddGUI(GameObject e);

	void onRemoveGUI(Component e);
	void onRemoveGUI(GameObject e);
	
}
