package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class EnemyKill implements CollisionListener {

    private Player player;
    private Game game;
    public EnemyKill(Player p, Game g){
        this.player = p;
        this.game = g;
    } // Localises player

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Enemy) { //Checks if bullet collides with enemy
            e.getOtherBody().destroy(); // Destroys enemy object
            player.setKillCount(player.getKillCount()+1); // increments player kill count in the player class, respective for each level
            game.getLevel().setEnemyCount(game.getLevel().getEnemyCount()-1); // decrements enemy count for GUI display
        }
    }
}