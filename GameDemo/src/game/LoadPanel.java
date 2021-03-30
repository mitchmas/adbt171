package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadPanel {
    private JButton load1;
    private JPanel loadPanel;
    private JButton load3;
    private JButton load2;
    private Game game;

    public LoadPanel(Game g) {
        this.game = g;
        load1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //load from save 1 and print error if not found
                try {
                    GameLevel level = GameSaveLoad.load(game,"data/save.txt");
                    game.setLevel(level);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        load2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //load from save 2 and print error if not found
                try {
                    GameLevel level = GameSaveLoad.load(game,"data/save2.txt");
                    game.setLevel(level);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        load3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //load from save 3 and print error if not found
                try {
                    GameLevel level = GameSaveLoad.load(game,"data/save3.txt");
                    game.setLevel(level);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public JPanel getLoadPanel() {
        return loadPanel;
    }
}
