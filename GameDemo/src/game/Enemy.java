package game;

import city.cs.engine.*;

import java.util.List;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Enemy extends Walker {
    private static final Shape enemyShape = new PolygonShape(
            -0.06f,-1.49f,
            0.91f,-1.49f,
            1.17f,-0.57f,
            0.67f,1.15f,
            -0.56f,1.46f,
            -0.99f,0.76f,
            -1.01f,-0.04f); // Sets enemy hitbox

    // establish variables
    private static SoundClip deathSound;
    private int walkspeed;

    static {
        try {
            deathSound = new SoundClip("data/deathnoise.mp3");
            System.out.println("Loading death sound");
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }
    } // Sets the previously created soundclip to a sound file in data, presenting a loading message and sufficient error messages if error.

    //Set image from data
    public static final BodyImage image =
            new BodyImage("data/enemy.png", 3f);


    // Constructor sets image to hitbox when placed in world.
    public Enemy(World world) {
        super(world, enemyShape);
        addImage(image);
    }

    //Set walking method available for all enemies.
    public void walk(Enemy e) {
        e.startWalking(walkspeed);
    }

    // setter allows walkspeed to be changed.
    public void setWalkspeed(int walkspeed) {
        this.walkspeed = walkspeed;
    }

    // getter will display walkspeed value of an enemy
    public int getWalkspeed() {
        return walkspeed;
    }

    @Override
    public void destroy()
    {
        deathSound.play();
        super.destroy();
    } // Upon destroying from world, the enemy will play a death sound.

}