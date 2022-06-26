package v1.cardpattern;

import v1.card.Card;

import java.util.List;

public class PairCardPattern extends CardPattern {

    public PairCardPattern(List<Card> cards) {
        super("對子", cards);
    }

    @Override
    public Card getBiggestCard() {
        return cards.stream().max(Card::compareTo).get();
    }
}
