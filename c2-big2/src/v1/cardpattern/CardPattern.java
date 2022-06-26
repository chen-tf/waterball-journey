package v1.cardpattern;

import v1.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class CardPattern {
    private final String name;
    protected List<Card> cards;

    public CardPattern(String name, List<Card> cards) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public abstract Card getBiggestCard();

    public String getName() {
        return name;
    }

    public boolean isGreaterThan(CardPattern cardPattern) {
        return this.getBiggestCard().compareTo(cardPattern.getBiggestCard()) > 0;
    }

}
