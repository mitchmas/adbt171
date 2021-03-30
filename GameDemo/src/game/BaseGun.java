package game;

import city.cs.engine.*;
import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class BaseGun extends DynamicBody {

    private static final Shape bulletShape = new CircleShape(.5f); // Sets shape of bullet's hitbox

    private static final BodyImage image =
            new BodyImage("data/tball.png", 1f); // Sets image from data

    private static SoundClip gunShot;

    static {
        try {
            gunShot = new SoundClip("data/gunshot.mp3");
            System.out.println("Loading gunshot sound");
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }
    } // Sets the previously created soundclip to a sound file in data, presenting a loading message and sufficient error messages if error.

    public BaseGun(World w) {
        super(w,bulletShape);
        addImage(image);
        gunShot.play();
    } //Constructor for bullet, adds image to hitbox.
}
