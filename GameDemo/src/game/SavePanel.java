package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SavePanel {
    private JPanel savePanel;
    private JButton save3;
    private JButton save1;
    private JButton save2;
    private Game game;

    public SavePanel(Game g) {
        this.game = g;
        save1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save into file 1 or create file if nonexistent
                try {
                    GameSaveLoad.save(game.getLevel(),"data/save.txt");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        save2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save into file 2 or create file if nonexistent
                try {
                    GameSaveLoad.save(game.getLevel(),"data/save2.txt");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        save3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save into file 3 or create file if nonexistent
                try {
                    GameSaveLoad.save(game.getLevel(),"data/save3.txt");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public JPanel getSavePanel() {
        return savePanel;
    }
}
