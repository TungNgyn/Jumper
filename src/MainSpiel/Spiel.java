package MainSpiel;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Spiel implements Runnable{
    private SpielFenster spielFenster;
    private SpielPanel spielPanel;
    private Thread spielThread;
    private final int FPS_SET = 120;

    private BufferedImage importImg;

    public Spiel(){
        spielPanel = new SpielPanel();
        spielFenster = new SpielFenster(spielPanel);
        spielPanel.setFocusable(true);
        spielPanel.requestFocus();
        startSpielSchleife();
    }

    private void startSpielSchleife(){
        spielThread = new Thread(this);
        spielThread.start();
    }
    private void importImg(){
        InputStream is = getClass().getResourceAsStream("player_sprites.png");
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long letzterFrame = System.nanoTime();
        long jetzt = System.nanoTime();

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while(true){
            jetzt = System.nanoTime();
            if((jetzt-letzterFrame) >= timePerFrame){
                spielPanel.repaint();
                letzterFrame = jetzt;
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }
}
