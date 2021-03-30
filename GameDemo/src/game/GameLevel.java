package game;

import city.cs.engine.SoundClip;
import city.cs.engine.World;

import java.awt.*;
/**
 * @author      Mitchell Massiah
 * @version     1.0.1
 * @since       Mar 2021
 */
public abstract class GameLevel extends World {
    /**
     * Player for each level
     */
    private Player player;
    /**
     * Goal to reach in each level
     */
    private EndGoal goal;
    /**
     * Count of how many enemies in level
     */
    private int enemyCount;
    /**
     * Game that runs level
     */
    private Game g;
    /**
     * Constructor.
     * <p>
     * Constructor that localises game and establishes a goal for each level to be made and for the instance of game in which level will reside.
     */
    public GameLevel(Game game){
        goal = new EndGoal(this);
        g = game;
    }
    /**
     * Populates the level.
     * <p>
     * Creates a player (Essential for each level that can be changed) and attaches the essential listeners for each level.
     */
    public void populate(Game game) {
        //set a player and map collision listeners to it
        player = new Player(this);
        player.addCollisionListener(new GoalReach(this,game));
        player.addCollisionListener(new CoinPickup(game));
        player.addCollisionListener(new PlayerDeath());
    }
    /**
     * Setter for enemy Count
     * <p>
     * Allows the setting the amount of enemies in each level.
     */
    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }
    /**
     * Setter for Player
     * <p>
     * Allows the setting of the player in each level.
     */
    public void setPlayer(Player player) {this.player = player; }
    /**
     * Getter for Player
     * <p>
     * Allows other classes to retrieve the exact player instance located in the level
     * @return The current instance of player.
     */
    public Player getPlayer(){return player;}
    /**
     * Getter for game instance
     * <p>
     * Allows other classes to retrieve the game instance the levels are being played in.
     * @return The current instance of game
     */
    public Game getGame() {return g;}
    /**
     * Getter for goal
     * <p>
     * Allows other classes to retrieve the goal that is essential for the win condition
     * @return The current instance of the end goal.
     */
    public EndGoal getGoal() {return goal;}
    /**
     * Getter for the enemy Count
     * <p>
     * Allows other classes to retrieve the number of enemies in a given level.
     * @return The quantity of enemies in a level.
     */
    public int getEnemyCount() {return enemyCount;}
    /**
     * Win condition checker
     * <p>
     * The determiner whether the win condition has been met or not, vital for allowing changing between levels.
     * @return True if win condition has been met, false otherwise.
     */
    public abstract boolean isComplete();
    /**
     * Getter for name of level
     * <p>
     * Allows other classes to retrieve the name of the level for saving/loading
     * @return string containing "Level1/2/3/4" depending on level.
     */
    public abstract String getLevelName();
    /**
     * Getter for background image
     * <p>
     * Allows the view to draw the correct corresponding background image for each level when the level is reached.
     * @return Image containing a different gif each level.
     */
    public abstract Image getBackground();
    /**
     * Getter for background music
     * <p>
     * Allows the game to play a different soundtrack for each level when reached.
     * @return Sound file containing a different soundtrack each level.
     */
    public abstract SoundClip getBGM();
}