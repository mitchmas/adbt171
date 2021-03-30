package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Coins extends DynamicBody {

    private static final Shape coinShape = new CircleShape(1);

    private static final BodyImage image =
            new BodyImage("data/coin.png", 2f);

    private static SoundClip coinSound;

    public static BodyImage getImage() {
        return image;
    }

    static {
        try {
            coinSound = new SoundClip("data/coinsound.mp3");
            System.out.println("Loading coin acquire sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Coins(World w) {
        super(w,coinShape);
        addImage(image);
    }

    @Override
    public void destroy()
    {
        coinSound.play();
        super.destroy();
    }

}
