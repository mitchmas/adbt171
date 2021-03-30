package game;

import city.cs.engine.*;

import java.util.List;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class EndGoal extends StaticBody {
    private static final Shape goalShape = new PolygonShape(
            -0.601f,-0.857f,
            0.718f,-0.857f,
            0.947f,0.257f,
            0.067f,0.821f,
            -0.818f,0.216f);

    private static SoundClip winSound;

    static {
        try {
            winSound = new SoundClip("data/winsound.mp3");
            System.out.println("Loading victory sound");
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public static final BodyImage image =
            new BodyImage("data/endgoal.png", 2f);


    public EndGoal(World world) {
        super(world, goalShape);
        addImage(image);
    }

    @Override
    public void destroy()
    {
        winSound.play();
        super.destroy();
    }

}