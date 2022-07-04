package Eingabe;

import MainSpiel.SpielPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TastaturEingabe implements KeyListener {
    private SpielPanel spielPanel;

    public TastaturEingabe(SpielPanel spielPanel){
        this.spielPanel = spielPanel;
    }

    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                System.out.println("W");
                spielPanel.changeYDelta(-5);
                break;
            case KeyEvent.VK_A:
                System.out.println("A");
                spielPanel.changeXDelta(-5);
                break;
            case KeyEvent.VK_S:
                System.out.println("S");
                spielPanel.changeYDelta(5);
                break;
            case KeyEvent.VK_D:
                System.out.println("D");
                spielPanel.changeXDelta(5);
                break;
        }

    }
}
