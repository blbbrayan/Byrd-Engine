package engine.action;

import java.io.Serializable;

public abstract class ControlledTimer implements Runnable, Serializable {
	
	private boolean running;
	
	private int tps;
	
	public ControlledTimer(int tps){
		this.tps = tps;
	}
	
	public void start() {
		new Thread(this).start();
	}
	
	public void stop(){
		running = false;
	}

	public int getTPS(){
		return tps;
	}

	public void setTPS(int tps){
		this.tps = tps;
	}
	
	public abstract void tick();
	
	public void run(){
		running = true;
		
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / tps;
		long lastTimer = System.currentTimeMillis();
		double deltaTime = 0;
		
		
		while(running){
			long now = System.nanoTime();
			deltaTime += (now-lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldTick = false;
			
			while(deltaTime >= 1){
				//TICK + DeltaTime
				deltaTime -= 1;
				shouldTick = true;
			}
			
			if(shouldTick){
				tick();
			}
			
			//Sleep
			try {Thread.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
			
			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
			}
		}
	}
}
