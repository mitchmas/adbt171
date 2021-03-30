package game;

import city.cs.engine.SoundClip;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;


public class Game {

    private GameLevel level;
    private GameView view;
    private SoundClip gameMusic1;
    private SoundClip gameMusic2;
    private SoundClip bossMusic;
    private PlayerController controller;
    private int coinCount;

    public void addCoins() {
        coinCount++;
        System.out.println("Wealth Acquired! Total coins: " + coinCount);
    }

    public int getCoinCount() {
        return coinCount;
    }

    public Game() {

        level = new Level1(this);
        view = new GameView(this, 500, 500);
        view.setZoom(20);


        try {
            gameMusic1 = new SoundClip("data/song1.mp3");   // Open an audio input stream
            gameMusic2 = new SoundClip("data/song2.mp3");
            bossMusic = new SoundClip("data/song3.mp3");
            gameMusic1.loop();  // Set it to continous playback (looping)
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }

        controller = new PlayerController(level.getPlayer());
        view.addKeyListener(controller);
        view.addMouseListener(new GiveFocus(view));

        // add the view to a frame (Java top level window)
        final JFrame frame = new JFrame("Amazing Shooter DEMO");
        frame.add(view);
        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        ControlPanel controlPanel = new ControlPanel();
        frame.add(controlPanel.getMainPanel(), BorderLayout.CENTER);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);

        level.start();
    }

    public GameLevel getLevel() {
        return level;
    }

    public void goToNextLevel() {

        if (level instanceof Level3) {
            System.out.println("Well done! Game complete.");
            System.exit(0);
        } else {
            //stop the current level
            level.stop();
            if (level instanceof Level1) {
                level = new Level2(this);
                gameMusic1.stop();
                gameMusic2.loop();
            } else if (level instanceof Level2) {
                level = new Level3(this);
                gameMusic2.stop();
                bossMusic.loop();
            }
            view.setWorld(level);
            //change control set to new level player
            controller.playerUpdate(level.getPlayer());
            //start the simulation in the new level
            level.start();
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
