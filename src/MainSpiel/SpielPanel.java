package MainSpiel;

import Eingabe.MausEingabe;
import Eingabe.TastaturEingabe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SpielPanel extends JPanel {
    private MausEingabe mausEingabe;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private Color color = new Color(70,160,190);
    private Random random;

    private ArrayList<Vierecke> viereck = new ArrayList<>();

    public SpielPanel(){
        random = new Random();
        mausEingabe = new MausEingabe(this);
        addKeyListener(new TastaturEingabe(this));
        addMouseMotionListener(mausEingabe);
        addMouseListener(mausEingabe);
    }
    private void setPanelSize(){
        Dimension size = new Dimension(1200,800);
        setPreferredSize(size);
    }
    public void changeXDelta(int wert){
        this.xDelta += wert;
    }
    public void changeYDelta(int wert){
        this.yDelta += wert;
    }
    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }
    public void neuesViereck(int x, int y){
        viereck.add(new Vierecke(x,y));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (Vierecke vierecke : viereck){
            vierecke.updateViereck();
            vierecke.draw(g);
        }

        updateViereck();

        g.setColor(color);
        g.fillRect((int) xDelta, (int) yDelta, 200, 50);
    }
    public void updateViereck(){
        xDelta += xDir;
        if ((xDelta > 400)|(xDelta < 0)){
            xDir *= -1;
            color = getRndColor();
        }
        yDelta += yDir;
        if ((yDelta > 400)|(yDelta < 0)){
            yDir *= -1;
            color = getRndColor();
        }
    }
    private Color getRndColor(){
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r,g,b);
    }

    public class Vierecke {
        int x, y, w, h;
        int xDir = 1, yDir = 1;
        Color color;

        public Vierecke(int x, int y){
            this.x = x;
            this.y = y;
            w = random.nextInt(50);
            h = w;
            color = newColor();
        }

        public void updateViereck(){
            this.x += xDir;
            this.y += yDir;

            if (((x + w) > 400) | (x < 0)){
                xDir *= -1;
                color = newColor();
            }
            if (((y + h) > 400) | (y < 0)){
                yDir *= -1;
                color = newColor();
            }
        }

        private Color newColor(){
            return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(x,y,w,h);
        }
    }
}