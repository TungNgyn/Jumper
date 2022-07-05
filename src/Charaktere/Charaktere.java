package Charaktere;

public abstract class Charaktere {
    protected float x, y;
    protected int hoehe, weite;

    public Charaktere(float x, float y, int weite, int hoehe) {
        this.x = x;
        this.y = y;
        this.weite = weite;
        this.hoehe = hoehe;
    }
}
