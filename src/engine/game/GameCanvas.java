package engine.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import engine.action.ControlledTimer;
import engine.action.Resizer;
import engine.gui.BFrame;
import engine.gui.BScreen;
import engine.gui.GUIListener;
import engine.gui.ResizeListener;

public abstract class GameCanvas extends JPanel implements ResizeListener{
	private static final long serialVersionUID = 1L;
	
	private ControlledTimer timer;
	
	private Graphics2D graphics2D;
	private BufferedImage img;
	
	private Rectangle orgin;
	
	public static GUIListener listener;
	
	public GameCanvas(int fps, BFrame e){
		this.setBounds(0, 0, BFrame.screenWidth, BFrame.screenHeight);
		setOrgin(0, 0, BFrame.screenWidth, BFrame.screenHeight);
		e.addGui(this);
		setBackground(new Color(210, 210, 210));
		setup(fps);
		listener = e;
	}
	public GameCanvas(int fps, int x, int y, int width, int height, BScreen screen){
		this.setBounds(x, y, width, height);
		setOrgin(x, y, width, height);
		screen.addGUI(this);
		setBackground(new Color(210, 210, 210));
		setup(fps);
		listener = screen.getGuiListener();
	}
	
	
	public void setup(int fps){
		img = new BufferedImage(BFrame.screenWidth, BFrame.screenHeight, BufferedImage.TYPE_INT_RGB);
		graphics2D = (Graphics2D) img.getGraphics();
		setTimer(new ControlledTimer(fps){
		public void tick(){
			controlledDraw();
		}
		});
	}

	public abstract void draw(Graphics2D g);
	
	public void controlledDraw(){
		graphics2D.clearRect(0, 0, getWidth(), getHeight());
		draw(getG2D());
		Graphics g2 = getGraphics();
		if(img != null)
			g2.drawImage(img, 0, 0, null);
		g2.dispose();
	}
	
	public void start(){
		timer.start();
	}
	
	public void stop(){
		timer.stop();
	}
	
	public ControlledTimer getTimer() {
		return timer;
	}

	public void setTimer(ControlledTimer timer) {
		this.timer = timer;
	}

	public Graphics2D getG2D() {
		return graphics2D;
	}

	public void setGraphics2D(Graphics2D graphics2d) {
		graphics2D = graphics2d;
	}
	
	public BufferedImage getImg(){
		return img;
	}
	
	public void onResize(){
		setBounds(Resizer.resize(orgin));
		img = new BufferedImage(BFrame.screenWidth, BFrame.screenHeight, BufferedImage.TYPE_INT_RGB);
		graphics2D = (Graphics2D) img.getGraphics();
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
	
	public void resize() {
		setBounds(Resizer.resize(getBounds()));
		img = new BufferedImage(BFrame.screenWidth, BFrame.screenHeight, BufferedImage.TYPE_INT_RGB);
		graphics2D = (Graphics2D) img.getGraphics();
	}
	
	public void addGUI(GameObject gui){
		listener.onAddGUI(gui);
	}
	
}
