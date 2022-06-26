package v1.player;

import v1.card.Card;
import v1.util.CommonUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HumanPlayer extends Player {
    public HumanPlayer(int index) {
        super(index);
    }

    @Override
    public List<Card> takeATurn() {
        CommonUtil.printHands(hand);
        Integer[] cardIndexes =
                Arrays.stream(CommonUtil.nextString().split(" "))
                        .map(Integer::parseInt).toArray(Integer[]::new);

        if (cardIndexes[0] == -1) return Collections.emptyList();

        List<Card> cards = Arrays.stream(cardIndexes).map(hand::get).collect(Collectors.toList());
        hand.removeAll(cards);

        return cards;
    }

    @Override
    public void nameMySelf() {
        name = CommonUtil.nextString();
    }

}
