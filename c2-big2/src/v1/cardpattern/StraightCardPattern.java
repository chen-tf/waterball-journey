package v1.cardpattern;

import v1.card.Card;

import java.util.List;

public class StraightCardPattern extends CardPattern {
    public StraightCardPattern(List<Card> cards) {
        super("順子", cards);
    }

    @Override
    public Card getBiggestCard() {
        return cards.stream().max(Card::compareTo).get();
    }
}
