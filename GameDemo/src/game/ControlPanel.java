package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel {
    private JButton restartButton;
    private JPanel mainPanel;
    private JButton controlsButton;
    private JButton muteButton;
    private Game game;
    private boolean controlSwitch;
    private boolean soundSwitch;

    public ControlPanel(Game g) {
        this.game = g;
        restartButton.addActionListener(new ActionListener() {
            @Override
            //reset game when pressed
            public void actionPerformed(ActionEvent e) {
                g.resetLevels();
            }
        });
        controlsButton.addActionListener(new ActionListener() {
            @Override
            //switches on and off when pressed to display controls
            public void actionPerformed(ActionEvent e) {
                if (controlSwitch == true) {
                    controlSwitch = false;
                }
                else if (controlSwitch == false) {
                    controlSwitch = true;
                }
            }
        });
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mutes/unmutes volume when pressed
                if (soundSwitch == true) {
                    g.getGameMusic1().setVolume(1);
                    g.getGameMusic2().setVolume(1);
                    g.getGameMusic3().setVolume(1);
                    g.getBossMusic().setVolume(1);
                    soundSwitch = false;
                }
                else if (soundSwitch == false) {
                    g.getGameMusic1().setVolume(0.0001);
                    g.getGameMusic2().setVolume(0.0001);
                    g.getGameMusic3().setVolume(0.0001);
                    g.getBossMusic().setVolume(0.0001);
                    soundSwitch = true;
                }
            }
        });
    }

    public boolean isControlSwitch() {
        return controlSwitch;
    }

    public JPanel getMainPanel() {
        return mainPanel;

    }
}
