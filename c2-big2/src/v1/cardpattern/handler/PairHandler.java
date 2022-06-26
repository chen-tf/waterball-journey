package v1.cardpattern.handler;

import v1.card.Card;
import v1.cardpattern.CardPattern;
import v1.cardpattern.PairCardPattern;

import java.util.List;

public class PairHandler extends CardPatternHandler {

    public PairHandler(CardPatternHandler next) {
        super(next);
    }

    @Override
    int getValidSize() {
        return 2;
    }

    @Override
    CardPattern getCardPattern(List<Card> cards) {
        return new PairCardPattern(cards);
    }

    @Override
    boolean isValidCombination(List<Card> cards) {
        return cards.get(0).getRank().equals(cards.get(1).getRank());
    }
}
