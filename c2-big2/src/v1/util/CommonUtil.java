package v1.util;

import v1.card.Card;
import v1.card.Rank;
import v1.card.Suit;

import java.util.List;
import java.util.Scanner;

public class CommonUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static String nextString() {
        return scanner.nextLine();
    }


    public static boolean containsClub3(List<Card> cards) {
        return cards.stream()
                .anyMatch(card -> card.getRank().equals(Rank.THREE)
                        && card.getSuit().equals(Suit.Club));
    }

    public static void printHands(List<Card> hand) {
        StringBuilder header = new StringBuilder();
        StringBuilder cards = new StringBuilder();
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            int width = card.toString().length() + 1;
            header.append(String.format("%-" + width + "s", i));
            cards.append(String.format("%-" + width + "s", hand.get(i)));
        }
        System.out.println(header);
        System.out.println(cards);
    }
}
