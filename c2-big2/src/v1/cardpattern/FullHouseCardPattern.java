package v1.cardpattern;

import v1.card.Card;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FullHouseCardPattern extends CardPattern {
    public FullHouseCardPattern(List<Card> cards) {
        super("葫蘆", cards);
    }

    @Override
    public Card getBiggestCard() {
        Map<Integer, Long> summary = cards.stream()
                .map(card -> card.getRank().getValue())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        int represent = 0;
        for (Integer key : summary.keySet()) {
            if (summary.get(key) == 3) {
                represent = key;
                break;
            }
        }
        final int finalRepresent = represent;
        Card representCard = cards.stream().filter(card -> card.getRank().getValue() == finalRepresent)
                .max(Card::compareTo).get();
        return representCard;
    }
}
