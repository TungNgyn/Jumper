package Charaktere;

import util.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static MainSpiel.Spiel.SCALE;
import static util.Konstanten.SpielerKonstanten.*;

public class Spieler extends Charaktere {
    private BufferedImage[][] animationen;
    private int animTick, animIndex, animSpeed = 25;
    private int spielerAktion = IDLE_R;
    private boolean bewegung = false, angreifen = false;
    private boolean links, oben, rechts, unten;
    private float spielerSpeed = 2.0f;

    public Spieler(float x, float y, int weite, int hoehe) {
        super(x, y, weite, hoehe);
        animationLaden();
    }
    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();
    }
    public void render(Graphics g) {
        g.drawImage(animationen[spielerAktion][animIndex],
                (int) x, (int) y, (int) (32 * 2 * SCALE), (int) (32 * 2 * SCALE),null);
    }
    private void updateAnimationTick() {
        animTick++;
        if(animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if(animIndex >= getSpriteAnzahl(spielerAktion)) {
                animIndex = 0;
                angreifen = false;
            }
        }
    }
    private void setAnimation() {
        int startAnimation = spielerAktion;
        
        if (bewegung) {
            if(rechts){
                spielerAktion = RUNNING_R;
            } else if (links){
                spielerAktion = RUNNING_L;
            }
        } else {
            if (rechts){
                spielerAktion = IDLE_R;
            } else if (links)
                spielerAktion = IDLE_L;
        }
        if (angreifen) {
            if (rechts) {
                spielerAktion = ATTACK_1_R;
            } else if (links)
                spielerAktion = ATTACK_1_L;
        }
        if (startAnimation != spielerAktion) {
            resetAnimation();
        }
    }
    private void resetAnimation() {
        animTick = 0;
        animIndex = 0;
    }
    private void updatePos(){
        bewegung = false;

        if (links && !rechts) {
            x -= spielerSpeed;
            bewegung = true;
        } else if (rechts && !links) {
            x += spielerSpeed;
            bewegung = true;
        }
        if (oben && !unten) {
            y -= spielerSpeed;
            bewegung = true;
        } else if (unten && !oben) {
            y += spielerSpeed;
            bewegung = true;
        }

    }
    private void animationLaden() {
        InputStream is = getClass().getResourceAsStream(LoadSave.SPIELER_ATLAS);
        try {
            BufferedImage bild = ImageIO.read(is);
            animationen = new BufferedImage[13][16];

            for(int j = 0; j < animationen.length; j++)
                for(int i = 0; i < animationen.length; i++)
                    animationen[j][i] = bild.getSubimage(i*32,j*32, 32,32);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //region Boolean
    public void resetRichtungBooleans(){
        links = false;
        rechts = false;
        oben = false;
        unten = false;
    }
    public void setAngreifen(boolean angreifen) {
        this.angreifen = angreifen;
    }
    public boolean istLinks(){
        return links;
    }
    public void setLinks(boolean links){
        this.links = links;
    }
    public boolean istRechts(){
        return rechts;
    }
    public void setRechts(boolean rechts){
        this.rechts = rechts;
    }
    public boolean istOben(){
        return oben;
    }
    public void setOben(boolean oben){
        this.oben = oben;
    }
    public boolean istUnten(){
        return unten;
    }
    public void setUnten(boolean unten){
        this.unten = unten;
    }
    //endregion
}
