package Level;

import MainSpiel.Spiel;
import util.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {
    private Spiel spiel;
    private BufferedImage[] levelSprite;
    private Level levelEins;

    public LevelManager(Spiel spiel) {
        this.spiel = spiel;
        importLevelSprites();
        levelEins = new Level(LoadSave.GetLevelDaten());
    }
    public void importLevelSprites() {
        BufferedImage bild = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = bild.getSubimage(i*32, j*32,32,32);
            }
        }
    }
    public void draw(Graphics g) {
        for (int j = 0; j < Spiel.TILES_HOEHE; j++) {
            for (int i = 0; i < Spiel.TILES_WEITE; i++) {
                int index = levelEins.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index],Spiel.TILES_SIZE*i,Spiel.TILES_SIZE*j,
                        Spiel.TILES_SIZE, Spiel.TILES_SIZE,null);
            }
        }
    }
    public void update() {

    }
    public Level getCurrentLevel() {
        return levelEins;
    }
}
