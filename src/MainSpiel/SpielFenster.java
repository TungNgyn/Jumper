package MainSpiel;

import javax.swing.*;

public class SpielFenster {
    private JFrame spielFrame;

    public SpielFenster(SpielPanel spielPanel){
        spielFrame = new JFrame();

        spielFrame.setSize(610,490);
        spielFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spielFrame.setLocationRelativeTo(null);
        spielFrame.pack();
        spielFrame.setResizable(false);
        spielFrame.add(spielPanel);
        spielFrame.setVisible(true);
    }
}
