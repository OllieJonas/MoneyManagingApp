package me.csed2.moneymanager.sound;

import me.csed2.moneymanager.utils.SoundFileUtils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Optional;

public class AudioDispatcher implements Runnable {

    private SoundPack pack;

    public AudioDispatcher(SoundPack pack) {
        this.pack = pack;
    }

    static Clip loadClip(String s) {
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

    public AudioDispatcher() {
        Thread soundThread = new Thread(this);
        soundThread.start();
    }

    @Override
    public void run() {

    }

    public void playSound(Sound sound) {
        playClip(pack.getClip(sound));
    }

    private void playClip(Clip clip){
        if(clip == null) return;
        clip.setFramePosition(0);
        clip.start();
    }
}
