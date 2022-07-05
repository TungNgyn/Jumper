package Charaktere;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Charaktere {
    protected float x, y;
    protected int hoehe, weite;
    protected Rectangle2D.Float hitbox;

    public Charaktere(float x, float y, int weite, int hoehe) {
        this.x = x;
        this.y = y;
        this.weite = weite;
        this.hoehe = hoehe;
    }

    protected void drawHitbox(Graphics g) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }
    protected void initHitbox(float x, float y, float weite, float hoehe) {
        hitbox = new Rectangle2D.Float(x, y, weite, hoehe);
    }
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }
}
