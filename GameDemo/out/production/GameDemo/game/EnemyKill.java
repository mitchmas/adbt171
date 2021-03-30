package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class EnemyKill implements CollisionListener {

    private BaseGun gun;
    private Player player;
    public EnemyKill(BaseGun g, Player p){
        this.gun = g;
        this.player = p;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Enemy) {
            e.getOtherBody().destroy();
            player.addKills();
        }
    }
}