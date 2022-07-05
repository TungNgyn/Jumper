package MainSpiel;

import Charaktere.Spieler;
import Level.LevelManager;

import java.awt.*;

public class Spiel implements Runnable{
    private SpielFenster spielFenster;
    private SpielPanel spielPanel;
    private Thread spielThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Spieler spieler;
    private LevelManager levelManager;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2f;
    public final static int TILES_WEITE = 26;
    public final static int TILES_HOEHE = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int SPIEL_WEITE = TILES_SIZE * TILES_WEITE;
    public final static int SPIEL_HOEHE = TILES_SIZE * TILES_HOEHE;

    public Spiel(){
        initKlassen();

        spielPanel = new SpielPanel(this);
        spielFenster = new SpielFenster(spielPanel);
        spielPanel.setFocusable(true);
        spielPanel.requestFocus();

        startSpielSchleife();
    }

    private void initKlassen() {
        spieler = new Spieler(200,200,(int) (32 * SCALE), (int) (32 * SCALE));
        levelManager = new LevelManager(this);
    }
    private void startSpielSchleife(){
        spielThread = new Thread(this);
        spielThread.start();
    }
    public void update() {
        levelManager.update();
        spieler.update();
    }
    public void render(Graphics g) {
        levelManager.draw(g);
        spieler.render(g);
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
    public void fensterFokusWeg() {
        spieler.resetRichtungBooleans();
    }
    public Spieler getSpieler() {
        return spieler;
    }
}
