package v1.cardpattern.handler;

import v1.card.Card;
import v1.cardpattern.CardPattern;
import v1.cardpattern.FullHouseCardPattern;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FullHouseHandler extends CardPatternHandler {

    public FullHouseHandler(CardPatternHandler next) {
        super(next);
    }

    @Override
    CardPattern getCardPattern(List<Card> cards) {
        return new FullHouseCardPattern(cards);
    }

    @Override
    int getValidSize() {
        return 5;
    }

    @Override
    boolean isValidCombination(List<Card> cards) {

        Map<Integer, Long> rankCount = cards.stream()
                .map(card -> card.getRank().getValue())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //檢查是否指有兩種數字
        if (rankCount.size() != 2) return false;

        //牌一定有五張，也指有兩個數字，檢查是否有有某種數字的卡牌不是2張也不是3張
        for (Long count : rankCount.values()) {
            if (count != 2 && count != 3) return false;
        }

        return true;
    }
}
