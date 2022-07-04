package util;

public class Konstanten {

    public static class Richtung{
        public static final int LINKS = 0;
        public static final int HOCH = 2;
        public static final int RECHTS = 3;
        public static final int UNTEN = 4;
    }

    public static class SpielerKonstanten{
        public static final int IDLE_R = 0;
        public static final int RUNNING_R = 1;
        public static final int ATTACK_1_R = 2;
        public static final int ATTACK_2_R = 3;
        public static final int ATTACK_3_R = 4;
        public static final int JUMP_R = 5;
        public static final int HIT_R = 6;
        public static final int GROUND_R = 7;
        public static final int IDLE_L = 8;
        public static final int RUNNING_L = 9;
        public static final int ATTACK_1_L = 10;
        public static final int ATTACK_2_L = 11;
        public static final int ATTACK_3_L = 12;
        public static final int JUMP_L = 13;
        public static final int HIT_L = 14;
        public static final int GROUND_L = 15;

        public static int getSpriteAnzahl(int spieler_aktion){
            switch (spieler_aktion){
                case IDLE_R:
                case IDLE_L:
                    return 13;
                case ATTACK_1_R:
                case ATTACK_2_R:
                case ATTACK_3_R:
                case ATTACK_1_L:
                case ATTACK_2_L:
                case ATTACK_3_L:
                    return 10;
                case RUNNING_R:
                case RUNNING_L:
                    return 8;
                case GROUND_R:
                case GROUND_L:
                    return 7;
                case JUMP_R:
                case JUMP_L:
                    return 6;
                case HIT_R:
                case HIT_L:
                default:
                    return 4;
            }
        }
    }
}
