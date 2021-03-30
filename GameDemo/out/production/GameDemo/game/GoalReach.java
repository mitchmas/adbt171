package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class GoalReach implements CollisionListener {

    private GameLevel level;
    private Game game;
    public GoalReach(GameLevel level, Game game){
        this.level = level;
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof EndGoal && level.isComplete()) {
            e.getOtherBody().destroy();
            game.goToNextLevel();
        }
        else if (e.getOtherBody() instanceof EndGoal && level.isComplete() == false) {
            System.out.println("Hold on! You must collect ALL coins before leaving this area!");
        }
    }
}
