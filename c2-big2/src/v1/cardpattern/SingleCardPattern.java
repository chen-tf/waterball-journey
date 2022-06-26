package v1.cardpattern;

import v1.card.Card;

import java.util.List;

public class SingleCardPattern extends CardPattern {
    public SingleCardPattern(List<Card> cards) {
        super("單張", cards);
    }

    @Override
    public Card getBiggestCard() {
        return cards.get(0);
    }
}
