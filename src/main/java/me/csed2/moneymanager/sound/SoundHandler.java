package me.csed2.moneymanager.sound;

import me.csed2.moneymanager.utils.SoundFileUtils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundHandler implements Runnable{

    public static final Clip BUTTON_PRESS = loadClip("audio/button_press.wav");

    public static Clip loadClip(String s){
        try {
            File file = SoundFileUtils.getFileFromString(s);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            return clip;
        }catch(NullPointerException e){
            //Allow null sounds to return null
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public SoundHandler(){
        Thread soundThread = new Thread(this);
        soundThread.run();
    }

    public void run(){

    }

    public void playSound(Clip clip){
        if(clip == null) return;

        clip.setFramePosition(0);
        clip.start();
    }
}
