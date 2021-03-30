package game;

import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static game.Player.image;
import static game.Player.image2;

public class PlayerController implements KeyListener {

    private static final float WALKING_SPEED = 3;

    private Player player;

    public PlayerController(Player s){
        player = s;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            player.startWalking(-WALKING_SPEED);
            player.removeAllImages();
            player.addImage(image);
        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            player.startWalking(WALKING_SPEED);
            player.removeAllImages();
            player.addImage(image2);
        } else if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            player.jump(10);
        } else if (code == KeyEvent.VK_SPACE) {
            BaseGun gun = new BaseGun(player.getWorld());
            gun.setPosition(player.getPosition());
            gun.move(new Vec2(-WALKING_SPEED * 0.5f, 0));
            gun.setLinearVelocity(new Vec2(-WALKING_SPEED * 7, 0));
            gun.addCollisionListener(new EnemyKill(gun,player));
            gun.addCollisionListener(new DestroyOnImpact());
            }
        }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            player.stopWalking();
        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            player.stopWalking();
        }
    }

    public void playerUpdate(Player player){
        this.player = player;
    }

}
