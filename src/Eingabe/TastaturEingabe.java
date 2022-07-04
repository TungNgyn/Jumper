package Eingabe;

import MainSpiel.SpielPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static util.Konstanten.Richtung.*;
import static util.Konstanten.SpielerKonstanten.*;

public class TastaturEingabe implements KeyListener {
    private SpielPanel spielPanel;

    public TastaturEingabe(SpielPanel spielPanel){
        this.spielPanel = spielPanel;
    }

    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                spielPanel.setRichtung(HOCH);
                break;
            case KeyEvent.VK_A:
                spielPanel.setRichtung(LINKS);
                break;
            case KeyEvent.VK_D:
                spielPanel.setRichtung(RECHTS);
                break;
            case KeyEvent.VK_S:
                spielPanel.setRichtung(UNTEN);
                break;
            case KeyEvent.VK_SPACE:
                spielPanel.setAktion(ATTACK_1_R);
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                spielPanel.setBewegung(false);
                break;
        }

    }
}
