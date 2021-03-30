package game;

import city.cs.engine.World;

import java.awt.*;

public abstract class GameLevel extends World {
    private Player player;
    private Enemy enemy;
    private EndGoal goal;
    private Image background;
    public GameLevel(Game game){
        player = new Player(this);
        goal = new EndGoal(this);
        player.addCollisionListener(new GoalReach(this,game));
        player.addCollisionListener(new CoinPickup(game));
        player.addCollisionListener(new PlayerDeath());
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public Player getPlayer(){return player;}
    public EndGoal getGoal() {return goal;}
    public Image getBackground() {return background;}
    public abstract boolean isComplete();
}