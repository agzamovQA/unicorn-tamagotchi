package tamagotchi.utils;

public class ProgressBar {
    private static final int BAR_LENGTH = 20;

    public static String build(int value, int max) {
        if (value < 0) value = 0;
        if (value > max) value = max;

        int percent = (value * 100) / max;
        int filled = (value * BAR_LENGTH) / max;
        // append - нужен, чтобы добавлять значения в конце строки
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < BAR_LENGTH; i++) {
            sb.append(i < filled ? "█" : "░");
        }
        sb.append("] ");
        sb.append(String.format("%3d%%", percent));
        return sb.toString();
    }
}
