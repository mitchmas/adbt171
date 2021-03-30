package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
/**
 * @author      Mitchell Massiah
 * @version     1.0.1
 * @since       Mar 2021
 */
public class GoalReach implements CollisionListener {
    /**
     * Level to check if win condition is met or not
     */
    private GameLevel level;
    /**
     * Game instance that is called to switch to the next level when win condition is met.
     */
    private Game game;
    /**
     * Constructor.
     * <p>
     * Constructor that localises game and level as to test against the win condition and successfully change levels if met.
     */
    public GoalReach(GameLevel level, Game game){
        this.level = level;
        this.game = game;
    }
    /**
     * Collide detector
     * <p>
     * First checks whether the player has collided with the end goal and checks whether the win condition has been met.
     * Proceeds to either switch to the next level or gives the user a message if win condition is yet to be met.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof EndGoal && level.isComplete()) {
            e.getOtherBody().destroy();
            game.goToNextLevel(); // checks if criteria for completion is met on collision before changing levels.
        }
        else if (e.getOtherBody() instanceof EndGoal && !level.isComplete()) {
            System.out.println("Hold on! You must collect ALL coins before leaving this area!");
        } // displays error message if criteria is not completed.
    }
}
