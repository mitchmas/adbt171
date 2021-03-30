package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class CoinPickup implements CollisionListener {

    private Game game;
    public CoinPickup(Game game){
        this.game = game;
    } //sets the inputted game as a local variable

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coins) { // Checks if colliding with a coin object
            game.addCoins(); //increments a total coin counter of one, using a setter method in game
            e.getOtherBody().destroy(); // destroys the coin
        }
    }
}