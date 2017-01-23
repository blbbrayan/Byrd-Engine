package engine.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import engine.action.ImageLoader;

import javax.imageio.ImageIO;

public class Animation implements Serializable{
	private static final long serialVersionUID = 1L;

	private String name;

	private ArrayList<BufferedImage> frames;
	private AnimationType animationType;
	private int index;
	private int frameSpeed;
	private boolean resized;
	private boolean animate;
	
	private boolean swing;
	private int iteration;

	public Animation(String name, ArrayList<BufferedImage> frames){
		init();
		setName(name);
		setFrames(frames);
	}

	public Animation(String name, String path, String fileType){
		init();
		setName(name);
		File f = new File(path);
		if(f.isDirectory()){
			File[] files = f.listFiles();
			for(File e: files){
				if(e.isFile()){
					if(e.getName().contains(fileType)){
						try{
							ImageIO.read(e);
						}catch (Exception exc){
							System.out.println("Animation Reader Error: error with file " + e.getName());
						}
					}
				}
			}
		}else{
			System.out.println("Animation Reader Error: specified path must be a folder");
		}
		setFrames(frames);
	}
	
	public void init(){
		setIndex(0);
		setAnimationType(AnimationType.none);
		animate = false;
		swing = false;
		frameSpeed = 1;
		iteration = 0;
		resized = true;
	}
	
	public void runAnimation(){
		animate = true;
		index = 0;
		iteration = 0;
	}
	
	public void stopAnimation(){
		animate = false;
		index = 0;
		iteration = 0;
	}
	
	public BufferedImage getAnimationFrame() {
			if(frames.size() > 0){
				if(animate){
					iteration++;
					if(iteration >= frameSpeed){
						switch(animationType){
							case loop:
								index++;
								if(index >= frames.size())
									index = 0;
								break;
							case none:
								index++;
								if(index >= frames.size()){
									index = 0;
									animate = false;
								}
								break;
							case swing:
								if(swing)
									index--;
								else
									index++;
								if(index >= frames.size()){
									index--;
									swing = true;
								}
								else if(index <= -1){
									index++;
									swing = false;
								}
								break;
						}
					iteration = 0;
				}
					return frames.get(index);
			}
				return null;
		}
			return null;
	}
	
	public boolean isAnimating(){
		return this.animate;
	}
	public ArrayList<BufferedImage> getFrames() {
		return frames;
	}
	public void setFrames(ArrayList<BufferedImage> frames) {
		this.frames = frames;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public AnimationType getAnimationType() {
		return animationType;
	}
	public void setAnimationType(AnimationType animationType) {
		this.animationType = animationType;
	}
	public int getFrameSpeed() {
		return frameSpeed;
	}
	public void setFrameSpeed(int frameSpeed) {
		if(frameSpeed > 0)
			this.frameSpeed = frameSpeed;
	}
	public boolean isResized() {
		return resized;
	}
	public void setResized(boolean resized) {
		this.resized = resized;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
