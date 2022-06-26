package v1.cardpattern.handler;

import v1.card.Card;
import v1.cardpattern.CardPattern;
import v1.cardpattern.NoneCardPattern;

import java.util.List;

public abstract class CardPatternHandler {
    private final CardPatternHandler next;

    public CardPatternHandler(CardPatternHandler next) {
        this.next = next;
    }

    public CardPattern handle(List<Card> cards) {

        if (getValidSize() == cards.size() && isValidCombination(cards)) {
            return getCardPattern(cards);
        }

        if (next == null) {
            return new NoneCardPattern(cards);
        }
        return next.handle(cards);
    }

    abstract int getValidSize();

    abstract CardPattern getCardPattern(List<Card> cards);

    abstract boolean isValidCombination(List<Card> cards);
}
