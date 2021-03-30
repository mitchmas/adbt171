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
    //set multiple images to be used in controller to flip player on directional change.
    public static final BodyImage image =
            new BodyImage("data/boy.png", 3f);
    public static final BodyImage image2 =
            new BodyImage("data/boyFlip.png", 3f);

    private int killCount;
    private static SoundClip playerLoss;

    static {
        try {
            playerLoss = new SoundClip("data/playerdeath.mp3");
            System.out.println("Loading player death sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    } // Sets the previously created soundclip to a sound file in data, presenting a loading message and sufficient error messages if error.

    // Constructor sets image to hitbox when placed in world.
    public Player(World world) {
        super(world, playerShape);
        killCount = 0;
        addImage(image);
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
        System.out.println("+1 Kills this level, total of "+killCount);
    }

    public int getKillCount() {
        return killCount;
    }

    public void addKilels(){
        killCount++;
        System.out.println("Enemy defeated, killcount: "+killCount);
    }

    @Override
    public void destroy() {
        //play sound and destroy body on death
        playerLoss.play();
        super.destroy();
    }

}