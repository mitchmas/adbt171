package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private Game game;
    private Image controls;

    public GameView(Game game, int width, int height) {
        super(game.getLevel(), width, height);
        this.game = game; //constructor localises game
        controls = new ImageIcon("data/controls.png").getImage(); //set controls image
    }

    @Override
    protected void paintBackground(Graphics2D g) { g.drawImage(game.getLevel().getBackground(), 0, 0, this);
    } // Draws background using getters made to allow each level to have a different image to display as a background.

    @Override
    protected void paintForeground(Graphics2D g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 20)); // Set font properties
        g.drawString("Coins Collected: " + game.getCoinCount(), 10, 30); //  Displays coin counter
        g.drawString("Total enemies left: " + (game.getLevel().getEnemyCount()), 285, 30); // Displays how many enemies left in a level
        if (game.getCoinCount()==10 && game.getLevel() instanceof Level3) {
            //display warning of non-saveable before progressing to boss stage when win criteria is met for level 3
            g.drawString("WARNING! You will not be able to save ",10,50);
            g.drawString("in the next zone! (Boss Battle)",10,70);
        }
        if (game.getControlPanel().isControlSwitch()) {
            g.drawImage(controls,50,50,this);
        } // if controls button is pressed, displays controls.
    }
}
