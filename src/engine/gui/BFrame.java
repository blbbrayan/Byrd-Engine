package engine.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;

import engine.game.GameObject;

public class BFrame extends JFrame implements GUIListener{
	private static final long serialVersionUID = 1L;
	
	public static double update = 0.6;
	public static String version = ("Byrd Engine version: " + update + " Beta");
	
	private int fsm = 0;
	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	
	public static int appWidth;
	public static int appHeight;
	public static int screenWidth;
	public static int screenHeight;
	public static Rectangle appSize;
	public static Rectangle screenSize;
	
	public static ArrayList<ResizeListener> gui;
	
	public BFrame(String title, int width, int height, boolean resizable){
		setTitle(title);
		System.out.println(title);
		setResizable(resizable);
		setFullscreen(0);
		basicSetup(width, height);
		setup(width, height, this.getWidth(), this.getHeight());
		runAutoResizer();
	}
	public BFrame(int width, int height){
		setResizable(false);
		setFullscreen(1);
		basicSetup(width, height);
		setup(width, height, this.getWidth(), this.getHeight());
		runAutoResizer();
	}
	public BFrame(String title, int width, int height, int fullScreen){
		setTitle(title);
		System.out.println(title);
		setResizable(false);
		setFullscreen(fullScreen);
		basicSetup(width, height);
		setup(width, height, this.getWidth(), this.getHeight());
		runAutoResizer();
	}
	public BFrame(String title, int width, int height, int screenWidth, int ScreenHeight){
		setTitle(title);
		System.out.println(title);
		setResizable(false);
		setFullscreen(0);
		basicSetup(screenWidth, ScreenHeight);
		setup(width, height, screenWidth, ScreenHeight);
		runAutoResizer();
	}
	
	public static void setup(int width, int height, int sWidth, int sHeight){
		screenWidth = sWidth;
		screenHeight = sHeight;
		appWidth = width;
		appHeight = height;
		appSize = new Rectangle(0, 0, appWidth, appHeight);
		screenSize = new Rectangle(0, 0, screenWidth, screenHeight);
	}
	
	private void basicSetup(int width, int height){
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(fsm == 0)
			resetSize(width, height);
		System.out.println(BFrame.version);
		setVisible(true);
		this.gui = new ArrayList<>();
		getContentPane().setBackground(new Color(110, 110, 110));
	}
	
	public void resetSize(int width, int height){
		int sw = width + (width - this.getContentPane().getWidth());
		int sh = height + (height - this.getContentPane().getHeight());
		this.setSize(sw, sh);
		
	}
	
	public int getFSM(){
		return fsm;
	}
	
	private void setFullscreen(int fsnm){
		if(fsm <= 2){
			this.fsm = fsnm;
			switch(fsm){
			case 0:
				setUndecorated(false);
				break;
			case 1:
				setUndecorated(true);
				setExtendedState(JFrame.MAXIMIZED_BOTH);
				break;
			case 2:
				setUndecorated(true);
				device.setFullScreenWindow(this);
				break;
			}
		}else{
			System.err.println("Error: " + fsnm + " not supported");
		}
	}
	public ArrayList<ResizeListener> getGui() {
		return gui;
	}
	public void addGui(Component e){
		this.onAddGUI(e);
	}
	public void addGui(GameObject e){
		this.onAddGUI(e);
	}
	public Rectangle getScreen(){
		return new Rectangle(0, 0, this.getContentPane().getWidth(), this.getContentPane().getHeight());
	}
	public void setScreenSize(int width, int height){
		screenWidth = width;
		screenHeight = height;
		screenSize = new Rectangle(0, 0, width, height);
	}
	
	public void runAutoResizer(){
		new Thread(new AutoResizer()).start();
	}
	
	public void resizeGUI(){
		try{
			for(ResizeListener e: gui){
				e.onResize();
			}
		}catch(Exception e){
			
		}
	}
	
	private class AutoResizer implements Runnable{
		private Rectangle oldSize;
		public void run(){
			oldSize = new Rectangle(0, 0, BFrame.screenWidth, BFrame.screenHeight);
			while(true){
				if(!getScreen().equals(oldSize)){
					oldSize = getScreen();
					setScreenSize(oldSize.width, oldSize.height);
					resizeGUI();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void onAddGUI(Component gui) {
		if(gui instanceof ResizeListener){
				this.gui.add((ResizeListener) gui);
				add(gui);
				if(screenWidth != appWidth || screenHeight != appHeight){
					((ResizeListener) gui).onResize();
				}
		}else{
			System.out.println("Invalid Byrd Component");
		}
	}
	
	@Override
	public void onAddGUI(GameObject e) {
		this.gui.add(e);
		if(screenWidth != appWidth || screenHeight != appHeight){
			e.onResize();
		}
	}

	@Override
	public void onRemoveGUI(Component e) {
		this.gui.remove(e);
	}

	@Override
	public void onRemoveGUI(GameObject e) {
		this.gui.remove(e);
	}


}
