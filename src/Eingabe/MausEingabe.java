package Eingabe;

import MainSpiel.SpielPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MausEingabe implements MouseListener, MouseMotionListener {
    private SpielPanel spielPanel;

    public MausEingabe(SpielPanel spielPanel){
        this.spielPanel = spielPanel;
    }
    public void mouseClicked(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
    public void mouseDragged(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
        spielPanel.setRectPos(e.getX(),e.getY());
    }
}
