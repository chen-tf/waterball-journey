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
    private int passCount;

    public Big2(Deck deck, List<Player> players, CardPatternHandler cardPatternHandler) {
        passCount = 0;
        this.deck = deck;
        this.players = new ArrayList<>(players.size());
        this.players.addAll(players);
        Collections.sort(this.players, Comparator.comparingInt(Player::getIndex));
        this.cardPatternHandler = cardPatternHandler;
    }

    public void start() {
        nameThemSelves();
        deck.shuffle();
        deal();
        playRounds();
    }

    private void nameThemSelves() {
        players.forEach(Player::nameMySelf);
    }

    private void deal() {
        Player player = players.get(0);
        while (!deck.isEmpty()) {
            player.addCardToHand(deck.deal());
            player = getNextPlayer(player.getIndex());
        }
    }

    private void playRounds() {
        Player currentPlayer = findPlayerWhoHasClub3();
        do {
            takeARoundIfPassCountEqualTo3();
            takeATurn(currentPlayer);
            currentPlayer = getNextPlayer(currentPlayer.getIndex());
        } while (!isWinnerExist());
    }

    private Player findPlayerWhoHasClub3() {
        return players.stream()
                .filter(player -> containsClub3(player.getHand()))
                .findFirst().orElseThrow();
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

    private void takeATurn(Player player) {
        System.out.printf("輪到%s了\n", player.getName());
        PlayerTurn playerTurn = playerTakeATurn(player);

        if (playerTurn.isPass()) {
            passCount++;
            System.out.printf("玩家 %s PASS\n", player.getName());
        } else {
            becomeNewTopPlayer(player, playerTurn.getPlay());
            printTopPlayInfo();
        }
    }

    private PlayerTurn playerTakeATurn(Player player) {
        PlayerTurn playerTurn = null;
        CardPattern playerCardPattern = null;
        do {
            try {
                List<Card> playCards = player.takeATurn();
                boolean isUserPass = playCards.isEmpty();
                if (isUserPass) {
                    validatePass(player);
                } else {
                    playerCardPattern = cardPatternHandler.handle(playCards);
                    validateCardPattern(player, playerCardPattern);
                }

                playerTurn = new PlayerTurn(isUserPass, playerCardPattern);
            } catch (InvalidPassException e) {
                System.out.println("你不能在新的回合中喊 PASS");
            } catch (InvalidCardPatternException e) {
                player.addCardToHand(playerCardPattern.getCards());
                System.out.println("此牌型不合法，請再嘗試一次。");
            }
        } while (playerTurn == null);

        return playerTurn;
    }

    private void validatePass(Player player) throws InvalidPassException {
        if (isTopPlayNotExist() || isPlayerKeepClub3NotPlayed(player) || isTopPlayer(player)) {
            throw new InvalidPassException();
        }
    }

    private boolean isTopPlayNotExist() {
        return topPlay == null;
    }

    private boolean isPlayerKeepClub3NotPlayed(Player player) {
        return !isClub3Used() && player.handContainsClub3();
    }

    private boolean isTopPlayer(Player player) {
        return topPlayer != null && topPlayer.equals(player);
    }

    private boolean isClub3Used() {
        return topPlayer != null;
    }

    private void validateCardPattern(Player player, CardPattern cardPattern) throws InvalidCardPatternException {
        if (isNoneCardPattern(cardPattern) ||
                !isComparableWithTopPlay(cardPattern) ||
                isSmallerThanTopPlay(cardPattern) ||
                isPlayerKeepClub3NotPlayed(player)) {
            throw new InvalidCardPatternException();
        }
    }

    private boolean isNoneCardPattern(CardPattern cardPattern) {
        return cardPattern instanceof NoneCardPattern;
    }

    private boolean isComparableWithTopPlay(CardPattern cardPattern) {
        if (isTopPlayNotExist()) return true;
        return topPlay.getClass().equals(cardPattern.getClass());
    }

    private boolean isSmallerThanTopPlay(CardPattern cardPattern) {
        if (isTopPlayNotExist()) return false;
        return !cardPattern.isGreaterThan(topPlay);
    }

    private void becomeNewTopPlayer(Player player, CardPattern cardPattern) {
        passCount = 0;
        topPlay = cardPattern;
        topPlayer = player;
    }

    private void printTopPlayInfo() {
        System.out.printf("玩家 %s 打出了 %s", topPlayer.getName(), topPlay.getName());
        for (Card card : topPlay.getCards()) {
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
}
