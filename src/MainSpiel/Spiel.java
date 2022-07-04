package MainSpiel;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Spiel implements Runnable{
    private SpielFenster spielFenster;
    private SpielPanel spielPanel;
    private Thread spielThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    public Spiel(){
        spielPanel = new SpielPanel();
        spielFenster = new SpielFenster(spielPanel);
        spielPanel.setFocusable(true);
        spielPanel.requestFocus();
        startSpielSchleife();
    }

    public void update() {
        spielPanel.updateSpiel();
    }
    private void startSpielSchleife(){
        spielThread = new Thread(this);
        spielThread.start();
    }
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long letzteZeit = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while(true){
            long jetztZeit = System.nanoTime();

            deltaU += (jetztZeit - letzteZeit) / timePerUpdate;
            deltaF += (jetztZeit - letzteZeit) / timePerFrame;
            letzteZeit = jetztZeit;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
            if (deltaF >= 1) {
                spielPanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames+" | UPS: "+updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
