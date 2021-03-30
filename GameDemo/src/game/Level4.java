package game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static game.RandomPicker.getRandom;

public class Level4  extends GameLevel implements ActionListener {
    private Game game;
    private Timer timer;
    private int enemySpawn;

    public Level4(Game game) {
        super(game);
        this.game = game;
        // set number of enemies to spawn for boss fight
        enemySpawn = 16;
        //set goal
        getGoal().setPosition(new Vec2(-9f, 8f));
        //set floor
        Shape shape = new BoxShape(11, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        //make platforms
        Shape platform1Shape = new BoxShape(4, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-7f, 6.5f));

        StaticBody platform2 = new StaticBody(this, platform1Shape);
        platform2.setPosition(new Vec2(7f, -7));

        StaticBody platform3 = new StaticBody(this, platform1Shape);
        platform3.setPosition(new Vec2(-7f, -7));

        Shape smallPlatform = new BoxShape(2, 0.5f);
        StaticBody platform4 = new StaticBody(this, smallPlatform);
        platform4.setPosition(new Vec2(0, -1.5f));

        StaticBody platform5 = new StaticBody(this, smallPlatform);
        platform5.setPosition(new Vec2(6, 5));

        Shape wallShape = new BoxShape(0.5f, 11f);
        StaticBody wall1 = new StaticBody(this, wallShape);
        wall1.setPosition(new Vec2(-11.5f, -1f));

        StaticBody wall2 = new StaticBody(this, wallShape);
        wall2.setPosition(new Vec2(11.5f, -1f));
    }

    @Override
    public void populate(Game game) {
        //populate world with bodies and map collisions/set enemy counter
        super.populate(game);
        setEnemyCount(16);
        getPlayer().setPosition(new Vec2(0, -9.5f));
        for (int i = 0; i < 4; i++) {
            Enemy enemy = new Enemy(this);
            if (i < 2) {
                enemy.setPosition(new Vec2(-9.5f + i * 3, -9));
                enemy.setWalkspeed(2);
            } else if (i >= 2) {
                enemy.setPosition(new Vec2(9.5f - (i - 2) * 3, -9));
                enemy.setWalkspeed(-2);
            }
            //decrement on each enemy spawned
            enemySpawn--;
            enemy.addCollisionListener(new EnemyCollide(enemy));
        }
        //start timer on level creation/population
        timer = new Timer(3000, this);
        timer.setInitialDelay(3000);
        timer.start();
    }

    @Override
    public boolean isComplete() {
        return (game.getCoinCount() == 11);
    }

    @Override
    public String getLevelName() {
        return "Level4";
    }

    @Override
    public Image getBackground() {
        return new ImageIcon("data/bg4.gif").getImage();
    }

    @Override
    public SoundClip getBGM() {
        return game.getBossMusic();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //check whether the correct number of enemies have spawned to be able to stop
        if (enemySpawn == 0) {
            timer.stop();
            //set final coin to win game
            Coins coins = new Coins(this);
            coins.setPosition(new Vec2(6.5f,7));
        } else {
            // get random spawn location for enemies
            int rng = getRandom();
            Enemy enemy = new Enemy(this);
            if (rng == 0) {
                enemy.setPosition(new Vec2(0f, 8.5f));
            } else if (rng == 1) {
                enemy.setPosition(new Vec2(6.5f, 7));
            } else if (rng == 2) {
                enemy.setPosition(new Vec2(-9.5f, -9.5f));
            } else if (rng == 3) {
                enemy.setPosition(new Vec2(9.5f, -9.5f));
            }
            //set different speed depending on how many enemies have been killed already
            if (getPlayer().getKillCount() < 10) {
                enemy.setWalkspeed(2);
            } else if (getPlayer().getKillCount() >= 10) {
                enemy.setWalkspeed(3);
            }
            enemySpawn--;
            enemy.addCollisionListener(new EnemyCollide(enemy));
        }
    }
}