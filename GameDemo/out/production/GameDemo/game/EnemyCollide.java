package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

public class EnemyCollide implements CollisionListener {

    private Enemy enemy;

    public EnemyCollide(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void collide(CollisionEvent e) {
            enemy.setWalkspeed(-enemy.getWalkspeed());
            enemy.startWalking(enemy.getWalkspeed());
    }
}
