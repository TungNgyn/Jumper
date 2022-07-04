package MainSpiel;

import Eingabe.MausEingabe;
import Eingabe.TastaturEingabe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static util.Konstanten.Richtung.*;
import static util.Konstanten.SpielerKonstanten.*;

public class SpielPanel extends JPanel {
    private MausEingabe mausEingabe;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage bild;
    private BufferedImage[][] animationen;
    private int animTick, animIndex, animSpeed = 20;
    private int spielerAktion = 0;
    private int spielerRichtung = -1;
    private boolean bewegung = false;


    public SpielPanel(){
        mausEingabe = new MausEingabe(this);

        importBild();
        animationLaden();

        setPanelSize();
        addKeyListener(new TastaturEingabe(this));
        addMouseMotionListener(mausEingabe);
        addMouseListener(mausEingabe);
    }
    public void setRichtung(int richtung){
        this.spielerRichtung = richtung;
        bewegung = true;
    }
    public void setBewegung(boolean bewegung){
        this.bewegung = bewegung;
    }
    private void animationLaden() {
        animationen = new BufferedImage[13][16];

        for(int j = 0; j < animationen.length; j++)
            for(int i = 0; i < animationen.length; i++)
             animationen[i][j] = bild.getSubimage(i*32,j*32,32,32);
    }
    private void updateAnimationTick() {
        animTick++;
        if(animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if(animIndex >= getSpriteAnzahl(spielerAktion)) {
                animIndex = 0;
            }
        }
    }
    private void importBild(){
        InputStream is = getClass().getResourceAsStream("/Charaktere/Abenteurer.png");

        try {
            bild = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setPanelSize(){
        Dimension size = new Dimension(1200,800);
        setPreferredSize(size);
    }
    private void setAnimation() {
        if (bewegung) {
            spielerAktion = RUNNING_R;
        }
        else {
            spielerAktion = IDLE_R;
        }
    }
    public void setAktion(int aktion){
        this.spielerAktion = aktion;
    }
    private void updatePos(){
        if (bewegung) {
            switch (spielerRichtung){
                case LINKS:
                    xDelta -= 5;
                    break;
                case HOCH:
                    yDelta -= 5;
                    break;
                case RECHTS:
                    xDelta += 5;
                    break;
                case UNTEN:
                    yDelta += 5;
                    break;
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(animationen[animIndex][spielerAktion],(int)xDelta,(int)yDelta,128,128,null);
    }
    public void updateSpiel() {
        updateAnimationTick();
        setAnimation();
        updatePos();
    }
}