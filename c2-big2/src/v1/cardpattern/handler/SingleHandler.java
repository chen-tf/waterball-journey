package v1.cardpattern.handler;

import v1.card.Card;
import v1.cardpattern.CardPattern;
import v1.cardpattern.SingleCardPattern;

import java.util.List;

public class SingleHandler extends CardPatternHandler {

    public SingleHandler(CardPatternHandler next) {
        super(next);
    }

    @Override
    int getValidSize() {
        return 1;
    }

    @Override
    CardPattern getCardPattern(List<Card> cards) {
        return new SingleCardPattern(cards);
    }

    @Override
    boolean isValidCombination(List<Card> cards) {
        return true;
    }
}
