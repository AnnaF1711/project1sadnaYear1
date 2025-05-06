package org.example;
import javax.sound.sampled.*;
import java.net.URL;

public class SoundPlayer {
    private Clip storeMusic;
    private Clip siren;

    public SoundPlayer() {//כשניצור אובייקט פלייר יהיו בתוכו 2 האודיו האלה
        this.storeMusic = loadClip("/storeMusic.wav");
        this.siren = loadClip("/sirenAlarm.wav");
    }

    // טוען את הסאונדים ומאחסן אותם בקליפים
    public Clip loadClip(String path) {
        try {
            URL clipURL = getClass().getResource(path);
            if(clipURL == null) {
                System.err.println("Incorrect clipURL: File not found.");
                return null;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(clipURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);//נותן גישה לקליפ
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void playClip(Clip clip) {
        if (clip != null) {
            clip.setFramePosition(0); // הקליפ מתחיל מההתחלה
            clip.start();
        }
    }

    public void setVolume(Clip clip, float volume) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
        }
    }

    public void playStoreMusic() {
        setVolume(this.storeMusic, -20.0f); // עוצמת קול מאוד חלשה
        playClip(this.storeMusic); // הפעלת המוזיקה של החנות
    }

    // מתודה להפעיל את הסירנה
    public void playSiren() {
        setVolume(this.siren, -20.0f); // עוצמת קול מאוד חלשה
        playClip(this.siren); // הפעלת הסירנה
    }
}
