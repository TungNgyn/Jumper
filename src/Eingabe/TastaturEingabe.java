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
//            case KeyEvent.VK_W:
//                spielPanel.getSpiel().getSpieler().istOben();
//                spielPanel.getSpiel().getSpieler().setOben(true);
//                break;
            case KeyEvent.VK_A:
                spielPanel.getSpiel().getSpieler().istLinks();
                spielPanel.getSpiel().getSpieler().setLinks(true);
                break;
            case KeyEvent.VK_D:
                spielPanel.getSpiel().getSpieler().istRechts();
                spielPanel.getSpiel().getSpieler().setRechts(true);
                break;
//            case KeyEvent.VK_S:
//                spielPanel.getSpiel().getSpieler().istUnten();
//                spielPanel.getSpiel().getSpieler().setUnten(true);
//                break;
            case KeyEvent.VK_SPACE:
                spielPanel.getSpiel().getSpieler().setJump(true);
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
//            case KeyEvent.VK_W:
//                spielPanel.getSpiel().getSpieler().setOben(false);
//                break;
            case KeyEvent.VK_A:
                spielPanel.getSpiel().getSpieler().setLinks(false);
                break;
//            case KeyEvent.VK_S:
//                spielPanel.getSpiel().getSpieler().setUnten(false);
//                break;
            case KeyEvent.VK_D:
                spielPanel.getSpiel().getSpieler().setRechts(false);
                break;
            case KeyEvent.VK_SPACE:
                spielPanel.getSpiel().getSpieler().setRechts(false);
                break;
        }

    }
}
