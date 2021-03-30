package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

public class Player extends Walker {
    private static final Shape playerShape = new PolygonShape(
            0.06f,1.5f,
            -0.43f,1.4f,
            -1.46f,0.01f,
            -1.11f,-1.18f,
            0.7f,-1.44f,
            1.16f,-1.26f,
            1.49f,0.39f);

    public static final BodyImage image =
            new BodyImage("data/boy.png", 3f);
    public static final BodyImage image2 =
            new BodyImage("data/boyFlip.png", 3f);

    public int killCount;
    private int coinCount;
    private static SoundClip playerLoss;

    static {
        try {
            playerLoss = new SoundClip("data/playerdeath.mp3");
            System.out.println("Loading death sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    public Player(World world) {
        super(world, playerShape);
        addImage(image);
    }

    public void addKills(){
        killCount++;
        System.out.println("Enemy defeated, killcount: "+killCount);
    }

    @Override
    public void destroy() {
        playerLoss.play();
        super.destroy();
    }

}