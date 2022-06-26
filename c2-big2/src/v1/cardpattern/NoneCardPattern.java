package v1.cardpattern;

import v1.card.Card;

import java.util.List;

public class NoneCardPattern extends CardPattern {
    public NoneCardPattern(List<Card> cards) {
        super("None", cards);
    }

    @Override
    public Card getBiggestCard() {
        return null;
    }
}
