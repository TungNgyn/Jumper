package MainSpiel;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class SpielFenster {
    private JFrame spielFrame;

    public SpielFenster(SpielPanel spielPanel){
        spielFrame = new JFrame();

        spielFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spielFrame.add(spielPanel);
        spielFrame.setResizable(false);
        spielFrame.pack();
        spielFrame.setVisible(true);
        spielFrame.setLocationRelativeTo(null);
        spielFrame.addWindowFocusListener(new WindowFocusListener() {
            public void windowGainedFocus(WindowEvent e) {
                spielPanel.getSpiel().fensterFokusWeg();
            }
            public void windowLostFocus(WindowEvent e) {

            }
        });
    }
}
