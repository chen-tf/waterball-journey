package v1;

import v1.card.Card;
import v1.card.Deck;
import v1.cardpattern.handler.*;
import v1.player.HumanPlayer;
import v1.player.Player;
import v1.util.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //cards init
        List<Card> cards = Arrays.stream(CommonUtil.nextString().split(" "))
                .map(Card::new)
                .collect(Collectors.toList());

        // deck init
        Deck deck = new Deck(cards);

        // players init
        List<Player> players = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            players.add(new HumanPlayer(i));
        }
        CardPatternHandler cardPatternHandler = new SingleHandler(
                new PairHandler(new FullHouseHandler(new StraightHandler(null))));
        Big2 big2 = new Big2(deck, players, cardPatternHandler);
        big2.start();
        big2.printWinner();
        big2.gameOver();
    }
}
