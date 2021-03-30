package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

public class DestroyOnImpact implements CollisionListener {

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof StaticBody
                || e.getOtherBody() instanceof Enemy
            || e.getOtherBody() instanceof Coins) // Checks all collisions from bullet except those with the player itself
            e.getReportingBody().destroy(); // Destroys the bullet.
    }
}