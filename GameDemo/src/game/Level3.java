package game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class Level3  extends GameLevel {
    private Game game;

    public Level3(Game game) {
        super(game);
        this.game = game;
        //set goal
        getGoal().setPosition(new Vec2(-9f,5f));
        //set floor
        Shape shape = new BoxShape(11, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -11.5f));
        // make platforms
        Shape platform1Shape = new BoxShape(8, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-3f, -7f));

        StaticBody platform2 = new StaticBody(this, platform1Shape);
        platform2.setPosition(new Vec2(3f, -2f));

        StaticBody platform3 = new StaticBody(this, platform1Shape);
        platform3.setPosition(new Vec2(-3f,3f));

        Shape border = new BoxShape(0.25f,0.5f);
        StaticBody border1 = new StaticBody(this, border);
        border1.setPosition(new Vec2(2f,3.5f));
        StaticBody border2 = new StaticBody(this,border);
        border2.setPosition(new Vec2(2f,-6.5f));

        Shape wallShape = new BoxShape(0.5f, 9.5f);
        StaticBody wall1 = new StaticBody(this, wallShape);
        wall1.setPosition(new Vec2(-11.5f, -4));

        StaticBody wall2 = new StaticBody(this, wallShape);
        wall2.setPosition(new Vec2(11.5f, -4));
    }

    @Override
    public void populate(Game game) {
        //populate world with bodies and map collisions/set enemy counter
        super.populate(game);
        getPlayer().setPosition(new Vec2(8, -10));
        for (int i = 0; i < 10; i++) {
            Enemy enemy = new Enemy(this);
            if (i >=8) {
                enemy.setPosition(new Vec2(-6f + (i - 8) * 3, 5.5f));
            }
            else if (i >=5) {
                enemy.setPosition(new Vec2(-9f+(i-5)*3,-4.5f));
            }
            else {
                enemy.setPosition(new Vec2(-9 + i * 3, -9));
            }
            enemy.addCollisionListener(new EnemyCollide(enemy));
            enemy.setWalkspeed(-2);
            setEnemyCount(getEnemyCount()+1);
        }
        for(int i=0;i<6;i++) {
            Coins coins = new Coins(this);
            coins.setPosition(new Vec2(i*2,0));
        }
    }

    @Override
    public boolean isComplete() {
        return (game.getCoinCount() == 10);
    }

    @Override
    public String getLevelName() {
        return "Level3";
    }

    @Override
    public Image getBackground() {
        return new ImageIcon("data/bg3.gif").getImage();
    }

    @Override
    public SoundClip getBGM() {
        return game.getGameMusic3();
    }
}