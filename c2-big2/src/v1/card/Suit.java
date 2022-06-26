package v1.card;

import java.util.Arrays;

public enum Suit {
    Club("C", 1), Diamond("D", 2),
    Heart("H", 3), Spade("S", 4);

    private final String displayName;
    private final int value;

    Suit(String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

    public static Suit getSuitByDisplayName(String displayName) {
        return Arrays.stream(Suit.values())
                .filter(suit -> suit.getDisplayName().equals(displayName))
                .findFirst().orElse(null);
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getValue() {
        return value;
    }
}
