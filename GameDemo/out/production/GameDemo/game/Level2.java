package game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Level2  extends GameLevel {
    private Game game;

    public Level2(Game game) {
        super(game);
        this.game = game;

        setBackground(new ImageIcon("data/bg2.gif").getImage());

        getPlayer().setPosition(new Vec2(8, -10));
        for (int i = 0; i < 5; i++) {
            Enemy enemy = new Enemy(this);
            if (i ==4 ) {
                enemy.setPosition(new Vec2(-7f,-5.5f));
            }
            else {
                enemy.setPosition(new Vec2(-6 + i * 3, -9));
            }
            enemy.addCollisionListener(new EnemyCollide(enemy));
            enemy.setWalkspeed(-1);
        }
        getGoal().setPosition(new Vec2(-8f,1f));

        Shape shape = new BoxShape(11, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        // makes platforms
        Shape platform1Shape = new BoxShape(4, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-8f, -1f));

        StaticBody platform2 = new StaticBody(this, platform1Shape);
        platform2.setPosition(new Vec2(6f, -3f));

        StaticBody platform3 = new StaticBody(this, platform1Shape);
        platform3.setPosition(new Vec2(-6f,-7f));

        Shape border = new BoxShape(0.25f,0.5f);
        StaticBody border1 = new StaticBody(this, border);
        border1.setPosition(new Vec2(-2.25f,-6.5f));


        Shape wallShape = new BoxShape(0.5f, 6f);
        StaticBody wall1 = new StaticBody(this, wallShape);
        wall1.setPosition(new Vec2(-11.5f, -6));

        StaticBody wall2 = new StaticBody(this, wallShape);
        wall2.setPosition(new Vec2(11.5f, -6));

        for(int i=0;i < 3; i++) {
            Coins coins = new Coins(this);
            coins.setPosition(new Vec2(4.5f+i*2f,-1f));
        }
    }
    @Override
    public boolean isComplete() {
        if (game.getCoinCount() == 4)
            return true;
        else
            return false;
    }
}
