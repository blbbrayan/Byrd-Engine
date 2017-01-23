package engine.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class GameSprite extends GameObject{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Animation> animations;
	private int animationIndex;
	private int idle;
	
	public GameSprite(){
		super();
		init();
	}
	
	public void init(){
		setAnimations(new ArrayList<>());
		setAnimationIndex(0);
		setIdle(0);
	}
	
	public boolean isIdle(){
		for(Animation animation: animations)
			if(animation.isAnimating() && animation != animations.get(idle))
				return false;
		return true;
	}

	public void setIdle(String name){
		this.idle = getAnimations().indexOf(getAnimation(name));
	}

	public void setIdle(int animationIndex){
		this.idle = animationIndex;
	}
	
	public void stopAll(){
		for(Animation animation: animations){
			animation.stopAnimation();
		}
	}

	public Animation getAnimation(String name){
		for(Animation e: animations)
			if(e.getName().equals(name))
				return e;
		System.out.println("Animation name does not exist");
		return null;
	}

	public void runAnimation(String name){
		stopAll();
		setAnimationIndex(animations.indexOf(getAnimation(name)));
		getAnimations().get(animationIndex).runAnimation();
	}

	public void runAnimation(int animationIndex){
		stopAll();
		setAnimationIndex(animationIndex);
		getAnimations().get(animationIndex).runAnimation();
	}
	
	public void draw(Graphics2D g) {
		if(isVisible()){
			if(animations.size() > 0){
				BufferedImage frame;
				if(isIdle() && (animationIndex != idle || !animations.get(idle).isAnimating()))
					runAnimation(idle);
				frame = animations.get(animationIndex).getAnimationFrame();
				if(frame != null)
					if(animations.get(animationIndex).isResized())
						g.drawImage(frame, getX(), getY(), getWidth(), getHeight(), null);
					else
						g.drawImage(frame, getX(), getY(), frame.getWidth(), frame.getHeight(), null);
			}
		}
	}

	public ArrayList<Animation> getAnimations() {
		return animations;
	}

	public void setAnimations(ArrayList<Animation> animations) {
		this.animations = animations;
	}
	
	public void addAnimation(Animation animation){
		this.animations.add(animation);
	}

	public int getAnimationIndex() {
		return animationIndex;
	}

	public void setAnimationIndex(int animationIndex) {
		this.animationIndex = animationIndex;
	}

}
