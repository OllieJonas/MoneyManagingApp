package me.csed2.moneymanager.sound;

import javax.sound.sampled.Clip;

public class NodeSounds {

    private String loadSound;

    private String submitSound;

    public NodeSounds(String loadSound){
        this.loadSound = loadSound;
    }

    public NodeSounds(String loadSound, String submitSound){
        this.loadSound = loadSound;
        this.submitSound = submitSound;
    }

    public Clip getLoadClip(){
        return SoundHandler.loadClip(loadSound);
    }

    public Clip getSubmitClip(){
        return SoundHandler.loadClip(submitSound);
    }
}
