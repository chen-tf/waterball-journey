package v1.card;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(String cardStr) {
        this.suit = Suit.getSuitByDisplayName(cardStr.substring(0, 1));
        this.rank = Rank.getRankByDisplayName(cardStr.substring(1)
                .replace("[", "").replace("]", ""));
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int compareTo(Card card) {
        int rankCompare = rank.getValue() - card.getRank().getValue();
        if (rankCompare != 0) return rankCompare;
        return suit.getValue() - card.getSuit().getValue();
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", suit.getDisplayName(), rank.getDisplayName());
    }
}
