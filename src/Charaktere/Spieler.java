package Charaktere;

import util.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static MainSpiel.Spiel.*;
import static util.HelferMethoden.*;
import static util.Konstanten.SpielerKonstanten.*;

public class Spieler extends Charaktere {
    private BufferedImage[][] animationen;
    private int animTick, animIndex, animSpeed = 25;
    private int spielerAktion = IDLE_R;
    private boolean bewegung = false, angreifen = false;
    private boolean links, oben, rechts, unten, jump;
    private float spielerSpeed = 2.0f;
    private int[][] levelDaten;
    private float xDrawOffset = 21 * SCALE;
    private float yDrawOffset = 4 * SCALE;

    //Sprung
    private float airSpeed = 0f;
    private float gravity = 0.04f * SCALE;
    private float jumpSpeed = -2.25f * SCALE;
    private float fallSpeedNachKollision = 0.5f * SCALE;
    private boolean inLuft = false;

    public Spieler(float x, float y, int weite, int hoehe) {
        super(x, y, weite, hoehe);
        animationLaden();
        initHitbox(x,y,25*SCALE,25*SCALE);
    }
    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();
    }
    public void render(Graphics g) {
        g.drawImage(animationen[spielerAktion][animIndex],
                (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), weite, hoehe,null);
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
        if (inLuft) {
            if (airSpeed < 0) {
                if (rechts){
                    spielerAktion = JUMP_R;
                } else {
                    spielerAktion = JUMP_L;
                }
            }
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
        float xSpeed = 0;

        if (jump) {
            jump();
        }
        if (!links && !rechts && !inLuft) {
            return;
        }

        if (links) {
            xSpeed -= spielerSpeed;
        }
        if (rechts) {
            xSpeed += spielerSpeed;
        }
        if (!inLuft) {
            if (!IsEntityOnFloor(hitbox, levelDaten)){
                inLuft = true;
            }
        }

        if (inLuft) {
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelDaten)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0) {
                    resetInLuft();
                } else {
                    airSpeed = fallSpeedNachKollision;
                }
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }
        bewegung = true;
    }
    private void jump() {
        if (inLuft)
            return;
        inLuft = true;
        airSpeed = jumpSpeed;
    }
    private void resetInLuft() {
        inLuft = false;
        airSpeed = 0;
    }
    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelDaten)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
        }

    }
    private void animationLaden() {
        BufferedImage bild = LoadSave.GetSpriteAtlas(LoadSave.SPIELER_ATLAS);
        animationen = new BufferedImage[16][13];

        for(int j = 0; j < animationen.length; j++)
            for(int i = 0; i < animationen.length; i++)
                animationen[j][i] = bild.getSubimage(i*32,j*32, 32,32);
    }
    public void levelDatenLaden(int[][] levelDaten) {
        this.levelDaten = levelDaten;
        if(!IsEntityOnFloor(hitbox,levelDaten))
            inLuft = true;
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
    public void setJump(boolean jump) {
        this.jump = jump;
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
