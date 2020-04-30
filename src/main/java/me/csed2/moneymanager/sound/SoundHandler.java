package me.csed2.moneymanager.sound;

import me.csed2.moneymanager.utils.SoundFileUtils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Optional;

public class SoundHandler implements Runnable {

    public static final Clip BUTTON_PRESS = loadClip("audio/button_press.wav");

    public static Clip loadClip(String s) {
        try {
            Optional<File> fileOptional = Optional.ofNullable(SoundFileUtils.getFileFromString(s));
            if (fileOptional.isPresent()) {

                File file = fileOptional.get();
                AudioInputStream ais = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();

                clip.open(ais);
                return clip;

            } else {
                return null;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public SoundHandler() {
        Thread soundThread = new Thread(this);
        soundThread.start();
    }

    public void run(){

    }

    public void playSound(Clip clip){
        if(clip == null) return;
        clip.setFramePosition(0);
        clip.start();
    }
}
