package com.mpJavaGame.sfx;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundEffect {

    private Clip clip;

    public SoundEffect(String soundFilePath) {
        init(soundFilePath);
    }

    private void init(String soundFilePath) {
        try {
            URL url = this.getClass().getResource(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts playing the Sound Effect.
     *
     * @param loop if true, the Sound Effect will be continuously playing until the stop() method is called.
     */
    public void play(Boolean loop) {
        if (clip.isRunning())
            clip.stop();
        clip.setFramePosition(0);
        clip.start();
        if (loop)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the Sound Effect and resets it to the beginning.
     */
    public void stop() {
        clip.stop();
        clip.setFramePosition(0);
    }
}
