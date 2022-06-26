package v1.cardpattern.handler;

import v1.card.Card;
import v1.card.Rank;
import v1.cardpattern.CardPattern;
import v1.cardpattern.StraightCardPattern;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StraightHandler extends CardPatternHandler {

    public StraightHandler(CardPatternHandler next) {
        super(next);
    }


    @Override
    int getValidSize() {
        return 5;
    }

    @Override
    CardPattern getCardPattern(List<Card> cards) {
        return new StraightCardPattern(cards);
    }

    @Override
    boolean isValidCombination(List<Card> cards) {
        Set<Integer> rankValues = cards.stream().map(card -> card.getRank().getValue()).collect(Collectors.toSet());
        int minRankValue = cards.stream().min(Card::compareTo).get().getRank().getValue();
        int totalContinued = 1;

        // 往下找最多四張
        for (int continued = 1, rankValue = getNextRankValue(minRankValue); continued < getValidSize(); continued++) {
            if (!rankValues.contains(rankValue)) break;
            totalContinued++;
            rankValue = getNextRankValue(rankValue);
        }

        // 往上找最多四張
        for (int continued = 1, rankValue = getPreviousRankValue(minRankValue); continued < getValidSize(); continued++) {
            if (!rankValues.contains(rankValue)) break;
            totalContinued++;
            rankValue = getPreviousRankValue(rankValue);
        }

        // 確認是否可以是連貫的五張牌
        return totalContinued == 5;
    }

    private int getNextRankValue(int rankValue) {
        int nextRank = rankValue + 1;
        return nextRank > Rank.TWO.getValue() ? Rank.THREE.getValue() : nextRank;
    }

    private int getPreviousRankValue(int rankValue) {
        int nextRank = rankValue - 1;
        return nextRank < Rank.THREE.getValue() ? Rank.TWO.getValue() : nextRank;
    }

}
