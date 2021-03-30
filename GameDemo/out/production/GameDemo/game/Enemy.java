package game;

import city.cs.engine.*;

import java.util.List;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Enemy extends Walker {
    private static final Shape enemyShape = new PolygonShape(
            0.9f,-1.49f,
            1.17f,-0.55f,
            0.59f,1.18f,
            -0.46f,1.49f,
            -0.98f,1.04f,
            -1.16f,-0.01f);

    private static SoundClip deathSound;
    private int walkspeed;

    static {
        try {
            deathSound = new SoundClip("data/deathnoise.mp3");
            System.out.println("Loading death sound");
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public static final BodyImage image =
            new BodyImage("data/enemy.png", 3f);


    public Enemy(World world) {
        super(world, enemyShape);
        addImage(image);
    }

    public void walk(Enemy e) {
        e.startWalking(walkspeed);
    }

    public void setWalkspeed(int walkspeed) {
        this.walkspeed = walkspeed;
    }

    public int getWalkspeed() {
        return walkspeed;
    }

    @Override
    public void destroy()
    {
        deathSound.play();
        super.destroy();
    }

}