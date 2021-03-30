package game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Level1  extends GameLevel {
    private Game game;

    public Level1(Game game) {

        super(game);
        this.game = game;

        setBackground(new ImageIcon("data/bg1.gif").getImage());

        getPlayer().setPosition(new Vec2(9.5f, -10));
        for (int i = 0; i < 4; i++) {
            Enemy enemy = new Enemy(this);
            enemy.setPosition(new Vec2(-6 + i * 3, -9));
            enemy.jump(8);
        }
        getGoal().setPosition(new Vec2(-8.5f, -8));

        Shape shape = new BoxShape(11, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        // makes platforms
        Shape platform1Shape = new BoxShape(4, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-8, 6.5f));

        StaticBody platform2 = new StaticBody(this, platform1Shape);
        platform2.setPosition(new Vec2(5, 2.5f));

        Shape wallShape = new BoxShape(0.5f, 6f);
        StaticBody wall1 = new StaticBody(this, wallShape);
        wall1.setPosition(new Vec2(-11.5f, -6));

        StaticBody wall2 = new StaticBody(this, wallShape);
        wall2.setPosition(new Vec2(11.5f, -6));

        Coins coins = new Coins(this);
        coins.setPosition(new Vec2(6f, -10));
    }

    @Override
    public boolean isComplete() {
        if (game.getCoinCount() == 1)
            return true;
        else
            return false;
    }
}