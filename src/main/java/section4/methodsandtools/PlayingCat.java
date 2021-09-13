package section4.methodsandtools;

public class PlayingCat {
    public static final int lowerLimit = 25;

    public static boolean isCatPlaying(boolean summer, int temperature) {
        int upperLimit = summer ? 45 : 35;
        return temperature >= lowerLimit && temperature <= upperLimit;
    }
}
