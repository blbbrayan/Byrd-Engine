package engine.action;

import engine.gui.BFrame;

public class Grid {

    public static int w60(int percent, boolean stretch){
        if(!stretch)
            return (int)(BFrame.appWidth * ((double)percent/60));
        return (int)(BFrame.screenWidth * ((double)percent/60));
    }
    public static int w100(int percent, boolean stretch){
        if(!stretch)
            return (int)(BFrame.appWidth * ((double)percent/100));
        return (int)(BFrame.screenWidth * ((double)percent/100));
    }
    public static int w(int percent, int amount, boolean stretch){
        if(!stretch)
            return (int)(BFrame.appWidth * ((double)percent/amount));
        return (int)(BFrame.screenWidth * ((double)percent/amount));
    }
    public static int h60(int percent, boolean stretch){
        if(!stretch)
            return (int)(BFrame.appHeight * ((double)percent/60));
        return (int)(BFrame.screenHeight * ((double)percent/60));
    }
    public static int h100(int percent, boolean stretch){
        if(!stretch)
            return (int)(BFrame.appHeight * ((double)percent/100));
        return (int)(BFrame.screenHeight * ((double)percent/100));
    }
    public static int h(int percent, int amount, boolean stretch){
        if(!stretch)
            return (int)(BFrame.appHeight * ((double)percent/amount));
        return (int)(BFrame.screenHeight * ((double)percent/amount));
    }

}
