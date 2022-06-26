package v1.card;

import java.util.Arrays;

public enum Rank {
    THREE("3", 1), FOUR("4", 2),
    FIVE("5", 3), SIX("6", 4),
    SEVEN("7", 5), EIGHT("8", 6),
    NINE("9", 7), TEN("10", 8),
    J("J", 9), Q("Q", 10),
    K("K", 11), A("A", 12),
    TWO("2", 13);

    private final String displayName;
    private final int value;

    Rank(String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

    public static Rank getRankByDisplayName(String displayName) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getDisplayName().equals(displayName))
                .findFirst().orElse(null);
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getValue() {
        return value;
    }
}
