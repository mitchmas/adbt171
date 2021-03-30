package game;

import city.cs.engine.*;
import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class BaseGun extends DynamicBody {

    private static final Shape bulletShape = new CircleShape(.5f);

    private static final BodyImage image =
            new BodyImage("data/tball.png", 1f);

    public BaseGun(World w) {
        super(w,bulletShape);
        addImage(image);
    }


}
