package engine.sound;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.Serializable;

public class BClip implements Serializable {

    private Clip sound;

    public BClip(Clip sound) {
        this.sound = sound;
    }

    public void start(){
        sound.setFramePosition(0);
        sound.start();
    }

    public void volume(float value){
        ((FloatControl) getSound().getControl(FloatControl.Type.MASTER_GAIN)).setValue(value);
    }

    public float volume(){
        return ((FloatControl) getSound().getControl(FloatControl.Type.MASTER_GAIN)).getValue();
    }

    public void stop(){
        sound.stop();
    }

    public Clip getSound() {
        return sound;
    }

    public void setSound(Clip sound) {
        this.sound = sound;
    }
}
