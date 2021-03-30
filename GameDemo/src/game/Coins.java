package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Coins extends DynamicBody {

    private static final Shape coinShape = new CircleShape(1); // sets coin hitbox

    private static final BodyImage image =
            new BodyImage("data/coin.png", 2f); // Sets image in data.

    private static SoundClip coinSound;

    static {
        try {
            coinSound = new SoundClip("data/coinsound.mp3");
            System.out.println("Loading coin acquire sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    } // Sets the previously created soundclip to a sound file in data, presenting a loading message and sufficient error messages if error.

    public Coins(World w) {
        super(w,coinShape);
        addImage(image);
    } // Coin constructor, when created into world wraps image on hitbox.

    @Override
    public void destroy()
    {
        coinSound.play();
        super.destroy();
    } // Upon being destroyed, the soundclip is played.

}
