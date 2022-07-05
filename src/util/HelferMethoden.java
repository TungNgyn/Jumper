package util;

import MainSpiel.Spiel;

import java.awt.geom.Rectangle2D;

public class HelferMethoden {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelDaten) {
        if (!IsSolid(x, y, levelDaten))
            if (!IsSolid(x + width, y + height, levelDaten))
                if (!IsSolid(x + width, y, levelDaten))
                    if (!IsSolid(x, y + height, levelDaten))
                        return true;
        return false;
    }
    private static boolean IsSolid(float x, float y, int[][] levelDaten) {
        if (x < 0 | x >= Spiel.SPIEL_WEITE)
            return true;
        if (y < 0 | y >= Spiel.SPIEL_HOEHE)
            return true;

        float xIndex = x / Spiel.TILES_SIZE;
        float yIndex = y / Spiel.TILES_SIZE;

        int value = levelDaten[(int) yIndex][(int) xIndex];

        if (value >= 48 | value < 0 | value != 11)
            return true;
        return false;
    }
    public static float GetEntityXPosNextToWall (Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Spiel.TILES_SIZE);
        if (xSpeed > 0) {
            //Rechts
            int tileXPos = currentTile * Spiel.TILES_SIZE;
            int xOffset = (int) (Spiel.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset -1;
        } else
            //Links
            return currentTile * Spiel.TILES_SIZE;
    }
    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Spiel.TILES_SIZE);
        if (airSpeed > 0) {
            //Fallend -> Boden berühren
            int tileYPos = currentTile * Spiel.TILES_SIZE;
            int yOffset = (int) (Spiel.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset -1;
        } else
            //Springen
            return currentTile * Spiel.TILES_SIZE;
    }
    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelDaten) {
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelDaten))
            if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelDaten))
                return false;
        return true;
    }

}
