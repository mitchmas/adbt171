package game;

import city.cs.engine.SoundClip;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 * @author      Mitchell Massiah
 * @version     1.0.1
 * @since       Mar 2021
 */
public class Game {
    /**
     * level to run and switch
     */
    private GameLevel level;
    /**
     * view to establish.
     */
    private GameView view;
    /**
     * sound file for level
     */
    private SoundClip gameMusic1;
    /**
     * sound file for level
     */
    private SoundClip gameMusic2;
    /**
     * sound file for level
     */
    private SoundClip gameMusic3;
    /**
     * sound file for level
     */
    private SoundClip bossMusic;
    /**
     * Controller to connect to player
     */
    private PlayerController controller;
    /**
     * Counter for coin currency/win condition
     */
    private int coinCount;
    /**
     * Panel to equip to view.
     */
    private ControlPanel controlPanel;
    /**
     * Panel to equip to view.
     */
    private SavePanel savePanel;
    /**
     * Panel to equip to view.
     */
    private LoadPanel loadPanel;

    /**
     * Increments the variable coinCount.
     * <p>
     * Called often when a coin is collected to ensure the variable coinCount matches up with the total number of coins collected by the user.
     */
    public void addCoins() {
        coinCount++; // Increments coin count
        System.out.println("Coin added, total: " + coinCount); //Display user friendly message
    }

    /**
     * Setter for the variable coinCount.
     */
    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }
    /**
     * Getter for the variable coinCount.
     */
    public int getCoinCount() {
        return coinCount;
    }
    /**
     * Getter for the variable gameMusic1.
     * @return Soundclip of level 1 music.
     */
    public SoundClip getGameMusic1() {
        return gameMusic1;
    }
    /**
     * Getter for the variable gameMusic2.
     * @return Soundclip of level 2 music.
     */
    public SoundClip getGameMusic2() {
        return gameMusic2;
    }
    /**
     * Getter for the variable gameMusic3.
     * @return Soundclip of level 3 music.
     */
    public SoundClip getGameMusic3() {
        return gameMusic3;
    }
    /**
     * Getter for the variable bossMusic.
     * @return Soundclip of boss level music.
     */
    public SoundClip getBossMusic() {
        return bossMusic;
    }
    /**
     * Establishes view, packs the frame and sets the level and starts the game.
     * <p>
     * Initialises the level, view, all BGM sounds and the controller before packing it all together and running the established level with the BGM.
     */
    public Game() {

        level = new Level1(this);
        level.populate(this); // Establish first level and populate
        view = new GameView(this, 500, 500);
        view.setZoom(20); // Establish and set view


        try {
            gameMusic1 = new SoundClip("data/song1.mp3");   // Open an audio input stream
            gameMusic2 = new SoundClip("data/song2.mp3");
            gameMusic3 = new SoundClip("data/song3.mp3");
            bossMusic = new SoundClip("data/bossmusic.mp3");
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e); // Print the error that has been caught, will play game with no background music
        }


        //Generates a controller for the player and allows mouse and key interaction to play game
        controller = new PlayerController(level.getPlayer(),this);
        view.addKeyListener(controller);
        view.addMouseListener(new GiveFocus(view));

        // add the view to a frame (Java top level window)
        final JFrame frame = new JFrame("Lil Shooter DEMO");
        frame.add(view, BorderLayout.CENTER);
        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // Add several panels to the game view to allow usage of buttons
        controlPanel = new ControlPanel(this);
        frame.add(controlPanel.getMainPanel(), BorderLayout.NORTH);
        savePanel = new SavePanel(this);
        frame.add(savePanel.getSavePanel(), BorderLayout.WEST);
        loadPanel = new LoadPanel(this);
        frame.add(loadPanel.getLoadPanel(), BorderLayout.EAST);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // make the frame visible
        frame.setVisible(true);
        // attempt to initialise the background music
        level.getBGM().loop();
        // start the level
        level.start();
    }

    /**
     * Loading game handler.
     * <p>
     * Stop the current level and loads in the new level called when reading the level from a file, then starts the level.
     * @param  level takes in a level to stop the current level to replace with.
     */
    public void setLevel(GameLevel level) {
        // stop current level and accompanying music
        this.level.getBGM().stop();
        this.level.stop();
        // set the level to the new level read from file with accompanying view and background music
        this.level = level;
        level.getBGM().loop();
        view.setWorld(level);
        //map controller to new player generated in the read level
        controller.playerUpdate(level.getPlayer());
        //start the read level
        level.start();
    }
    /**
     * Getter for GameLevel
     * <p>
     * Connects the game to a level at all times and allows to be called.
     * @return returns the current level the game is set to.
     */
    public GameLevel getLevel() {
        return level;
    }
    /**
     * Getter for ControlPanel
     * <p>
     * Returns the control panel for connections between the view, the game and the control panel.
     * @return returns the instance of the control panel.
     */
    public ControlPanel getControlPanel() {
        return controlPanel;
    }
    /**
     * Switches level to proceeding level of game.
     * <p>
     * Takes the current level and switches to the next level in the sequence, stopping the old one for replacement.
     * Loads new level in succession.
     */
    public void goToNextLevel() {
        //check if they have beaten the game
        if (level instanceof Level4) {
            System.out.println("Well done! Game complete.");
            System.exit(0);
        } else {
            //stop the current level
            level.getBGM().stop();
            level.stop();
            // switch to next level in sequence
            if (level instanceof Level1) {
                level = new Level2(this);
            } else if (level instanceof Level2) {
                level = new Level3(this);
            } else if (level instanceof Level3) {
                level = new Level4(this);
            }
            //populate level with changing bodies
            level.populate(this);
            // set background music and view
            level.getBGM().loop();
            view.setWorld(level);
            //change control set to new level player
            controller.playerUpdate(level.getPlayer());
            //start the simulation in the new level
            level.start();
        }
    }
    /**
     * Full reset of progress
     * <p>
     * Sets progress back to zero for when character loses and loads from very beginning.
     */
    public void resetLevels() {
        System.out.println("Restarting game...");
        // set counter back to 0
        coinCount = 0;
        // stop level and music
        level.getBGM().stop();
        level.stop();
        // set to initial level and generate w/ sound, view and controller
        level = new Level1(this);
        level.populate(this);
        level.getBGM().loop();
        view.setWorld(level);
        controller.playerUpdate(level.getPlayer());
        //start level and print a notice
        level.start();
        System.out.println("Game restart successful!");
    }
    /**
     * Main
     * <p>
     * Allows the game to actually run.
     */
    public static void main(String[] args) {
        new Game();
    }
}
