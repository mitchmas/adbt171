package game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class Level1  extends GameLevel {
    private Game game;

    public Level1(Game game) {

        super(game);
        this.game = game;
        //set goal position
        getGoal().setPosition(new Vec2(-8.5f, -8));
        //set floor
        Shape shape = new BoxShape(11, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        // make platforms
        Shape platform1Shape = new BoxShape(4, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-6.5f, 6.5f));

        StaticBody platform2 = new StaticBody(this, platform1Shape);
        platform2.setPosition(new Vec2(5, 2.5f));

        Shape wallShape = new BoxShape(0.5f, 10f);
        StaticBody wall1 = new StaticBody(this, wallShape);
        wall1.setPosition(new Vec2(-11.5f, -2));

        StaticBody wall2 = new StaticBody(this, wallShape);
        wall2.setPosition(new Vec2(11.5f, -2));
    }

    @Override
    public void populate(Game game){
        //populate world with bodies and set enemy counter
        super.populate(game);
        getPlayer().setPosition(new Vec2(9.5f, -10));
        for (int i = 0; i < 4; i++) {
            Enemy enemy = new Enemy(this);
            enemy.setPosition(new Vec2(-6 + i * 3, -9));
            enemy.jump(8);
            setEnemyCount(getEnemyCount()+1);
        }
        Coins coins = new Coins(this);
        coins.setPosition(new Vec2(6f, -10));
    }

    @Override
    public boolean isComplete() {return (game.getCoinCount() == 1);}

    @Override
    public String getLevelName() {
        return "Level1";
    }

    @Override
    public Image getBackground() {
        return new ImageIcon("data/bg1.gif").getImage();
    }

    @Override
    public SoundClip getBGM() {
        return game.getGameMusic1();
    }
}