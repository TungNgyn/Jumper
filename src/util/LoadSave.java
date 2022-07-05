package util;

import MainSpiel.Spiel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class LoadSave {
    public static final String SPIELER_ATLAS = "/Charaktere/Abenteurer.png";
    public static final String LEVEL_ATLAS = "/outside_sprites.png";
    public static final String LEVEL_EINS_DATEN = "/level_one_data.png";

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage bild = null;
        InputStream is = LoadSave.class.getResourceAsStream(fileName);
        try {
            bild = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bild;
    }

    public static int[][] GetLevelDaten() {
        int[][] levelDaten = new int[Spiel.TILES_HOEHE][Spiel.TILES_WEITE];
        BufferedImage bild = GetSpriteAtlas(LEVEL_EINS_DATEN);

        for (int j = 0; j < bild.getHeight(); j++)
            for (int i = 0; i < bild.getWidth(); i++) {
                Color color = new Color(bild.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48)
                    value = 0;
                levelDaten[j][i] = value;
            }
        return levelDaten;
    }
}
