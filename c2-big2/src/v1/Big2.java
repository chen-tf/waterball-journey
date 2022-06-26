package v1;

import v1.card.Card;
import v1.card.Deck;
import v1.cardpattern.CardPattern;
import v1.cardpattern.NoneCardPattern;
import v1.cardpattern.handler.CardPatternHandler;
import v1.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static v1.util.CommonUtil.containsClub3;

public class Big2 {
    private final List<Player> players;
    private final Deck deck;
    private final CardPatternHandler cardPatternHandler;
    private Player topPlayer;
    private CardPattern topPlay;
    private int passCount = 0;

    public Big2(Deck deck, List<Player> players, CardPatternHandler cardPatternHandler) {
        this.deck = deck;
        this.players = new ArrayList<>(players.size());
        this.players.addAll(players);
        Collections.sort(this.players, Comparator.comparingInt(Player::getIndex));
        this.cardPatternHandler = cardPatternHandler;
    }

    public void start() {
        // player naming
        players.forEach(Player::nameMySelf);

        // deck deal
        deck.shuffle();
        deal();

        Player currentPlayer = players.stream()
                .filter(player -> containsClub3(player.getHand()))
                .findFirst().get();

        do {
            boolean validPass;
            boolean validPlay;
            do {

                takeARoundIfPassCountEqualTo3();
                System.out.printf("輪到%s了\n", currentPlayer.getName());

                validPass = true;
                validPlay = true;
                List<Card> playCards = currentPlayer.takeATurn();
                CardPattern playerCardPattern = cardPatternHandler.handle(playCards);
                boolean isPass = playCards.isEmpty();

                if (isPass) {
                    validPass = isValidPass(currentPlayer);
                    if (validPass) {
                        System.out.printf("玩家 %s PASS\n", currentPlayer.getName());
                        passCount++;
                    } else {
                        System.out.println("你不能在新的回合中喊 PASS");
                    }
                } else {
                    validPlay = isValidPlay(playerCardPattern);
                    if (validPlay) {

                        passCount = 0;
                        topPlay = playerCardPattern;
                        topPlayer = currentPlayer;

                        printPlayerCardPattern(currentPlayer, playerCardPattern);
                    } else {
                        currentPlayer.addCardToHand(playerCardPattern.getCards());
                        System.out.println("此牌型不合法，請再嘗試一次。");
                    }
                }
            } while (!validPass || !validPlay);

            currentPlayer = getNextPlayer(currentPlayer.getIndex());
        } while (!isWinnerExist());
    }

    private boolean isClub3Used() {
        return topPlayer != null;
    }

    private boolean isValidPass(Player player) {
        if (topPlay == null) {
            return false;
        }

        if (!isClub3Used() && player.handContainsClub3()) {
            return false;
        }

        return topPlayer == null || !topPlayer.equals(player);
    }

    private boolean isValidPlay(CardPattern playerCardPattern) {
        if (playerCardPattern instanceof NoneCardPattern) {
            return false;
        } else if (topPlay != null) {

            if (!topPlay.getClass().equals(playerCardPattern.getClass())) {
                return false;
            }

            if (!playerCardPattern.isGreaterThan(topPlay)) {
                return false;
            }
        }

        return isClub3Used() || containsClub3(playerCardPattern.getCards());
    }

    private void printPlayerCardPattern(Player player, CardPattern playerCardPattern) {
        System.out.printf("玩家 %s 打出了 %s", player.getName(), playerCardPattern.getName());
        for (Card card : playerCardPattern.getCards()) {
            System.out.printf(" %s", card);
        }
        System.out.print("\n");
    }

    private boolean isWinnerExist() {
        if (topPlayer == null) return false;
        return topPlayer.isHandEmpty();
    }

    public void printWinner() {
        System.out.printf("遊戲結束，遊戲的勝利者為 %s\n", this.topPlayer.getName());
    }

    public void gameOver() {
        System.exit(0);
    }

    private void deal() {
        Player player = players.get(0);
        while (!deck.isEmpty()) {
            player.addCardToHand(deck.deal());
            player = getNextPlayer(player.getIndex());
        }
    }

    private Player getNextPlayer(int index) {
        return players.get((index + 1) % 4);
    }

    private void takeARoundIfPassCountEqualTo3() {
        if (passCount == 3) {
            topPlay = null;
            passCount = 0;
            System.out.println("新的回合開始了");
        }
    }
}
