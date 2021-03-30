package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class PlayerDeath implements CollisionListener {

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Enemy) {
            e.getReportingBody().destroy();
            System.out.println("You failed! Hit restart to try again or load from a save!"); //destroy body on collision, print death message.
        }
    }
}
