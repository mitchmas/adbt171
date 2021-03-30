package game;

import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static game.Player.image;
import static game.Player.image2;
/**
 * @author      Mitchell Massiah
 * @version     1.0.1
 * @since       Mar 2021
 */
public class PlayerController implements KeyListener, ActionListener {
    /**
     * Uneditable walking speed for the player
     */
    private static final float WALKING_SPEED = 3;
    /**
     * Player to be controlled
     */
    private Player player;
    /**
     * Game instance to control player in
     */
    private Game game;
    /**
     * Boolean value to ensure bullet shoots the way the player is facing.
     */
    private Boolean leftFace;
    /**
     * Boolean value tied to timer to prevent bullet spamming.
     */
    private Boolean onCooldown;
    /**
     * Constructor.
     * <p>
     * Constructor that localises game and player and establishes the initial boolean values to true for left facing and false for bullet cooldown
     */
    public PlayerController(Player s, Game g){
        player = s;
        game = g;
        leftFace = true;
        onCooldown = false;
    }
    /**
     * keyType event tracker
     * <p>
     * Overrides on incident of key being typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }
    /**
     * Player Mover & Shooter
     * <p>
     * Tracks what keys are pressed to ensure the character moves in the correct direction and that the image of the player is flipped upon changing direction.
     * Also starts a timer when a Bullet is shot to make the bullet unable to be spammed in quick succession.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        /*check what key is being pressed and make the character move in the right direction
        unless spacebar is pressed, then shoot bullet if the cooldown is not active.*/
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftFace = true;
            player.startWalking(-WALKING_SPEED);
            player.removeAllImages();
            player.addImage(image);
        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            leftFace = false;
            player.startWalking(WALKING_SPEED);
            player.removeAllImages();
            player.addImage(image2);
        } else if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            player.jump(11.5f);
        } else if (code == KeyEvent.VK_SPACE) {
            //create bullet if cooldown isnt active and map collisions for kill and destroy
            if (onCooldown == false) {
                BaseGun gun = new BaseGun(player.getWorld());
                gun.setPosition(player.getPosition());
                if (leftFace == true) {
                    gun.move(new Vec2(-WALKING_SPEED * 0.5f, 0));
                    gun.setLinearVelocity(new Vec2(-WALKING_SPEED * 7, 0));
                } else if (leftFace == false) {
                    gun.move(new Vec2(WALKING_SPEED * 0.5f, 0));
                    gun.setLinearVelocity(new Vec2(WALKING_SPEED * 7, 0));
                }
                gun.addCollisionListener(new EnemyKill(player, game));
                gun.addCollisionListener(new DestroyOnImpact());
                //establish cooldown on a timer to be active once a bullet is generated and shot.
                onCooldown = true;
                Timer timer = new Timer(500,this);
                timer.setRepeats(false);
                timer.start();
            } else {
                System.out.println("Tennis ball recharging!");
            }
        }
    }
    /**
     * Player stopper.
     * <p>
     * Tracks when a key is released after being pressed down and stops the player accordingly.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        //stop character on key release
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            player.stopWalking();
        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            player.stopWalking();
        }
    }
    /**
     * Update player on new player
     * <p>
     * Called when a player is out of date and needs replacing, replacing the old player model with a new one to be controlled to keep the flow of the game.
     */
    public void playerUpdate(Player player){
        this.player = player;
    }
    /**
     * Timer for cooldown.
     * <p>
     * After the time out from shooting a bullet ends, the boolean is reset to false to allow another bullet to be shot.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        onCooldown = false;
    }
}
