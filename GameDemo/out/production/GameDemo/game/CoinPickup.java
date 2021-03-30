package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class CoinPickup implements CollisionListener {

    private Game game;
    public CoinPickup(Game game){
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coins) {
            game.addCoins();
            e.getOtherBody().destroy();
        }
    }
}