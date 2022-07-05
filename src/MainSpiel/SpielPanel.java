package MainSpiel;

import Eingabe.MausEingabe;
import Eingabe.TastaturEingabe;

import javax.swing.*;
import java.awt.*;

import static MainSpiel.Spiel.SPIEL_HOEHE;
import static MainSpiel.Spiel.SPIEL_WEITE;

public class SpielPanel extends JPanel {

    private MausEingabe mausEingabe;
    private Spiel spiel;

    public SpielPanel(Spiel spiel){
        mausEingabe = new MausEingabe(this);
        this.spiel = spiel;

        setPanelSize();
        addKeyListener(new TastaturEingabe(this));
        addMouseMotionListener(mausEingabe);
        addMouseListener(mausEingabe);
    }
    private void setPanelSize(){
        Dimension size = new Dimension(SPIEL_WEITE,SPIEL_HOEHE);
        setPreferredSize(size);
    }
    public void updateSpiel() {

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        spiel.render(g);
    }
    public Spiel getSpiel() {
        return spiel;
    }
}