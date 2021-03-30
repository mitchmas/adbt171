package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private Game game;

    public GameView(Game game, int width, int height) {
        super(game.getLevel(), width, height);
        this.game = game;
    }

    @Override
    protected void paintBackground(Graphics2D g) { g.drawImage(game.getLevel().getBackground(), 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        g.setFont(new Font("TimesRoman",Font.BOLD, 20));
        g.drawString("Coins Collected: "+game.getCoinCount(),10,30); }
}
