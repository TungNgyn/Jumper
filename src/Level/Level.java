package Level;

public class Level {
    private int[][] levelDaten;

    public Level(int[][] levelDaten) {
        this.levelDaten = levelDaten;
    }

    public int getSpriteIndex(int x, int y) {
        return levelDaten[y][x];
    }
}
