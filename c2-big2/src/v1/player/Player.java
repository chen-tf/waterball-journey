package v1.player;

import v1.card.Card;

import java.util.ArrayList;
import java.util.List;

import static v1.util.CommonUtil.containsClub3;

public abstract class Player {
    protected int index;
    protected String name;
    protected List<Card> hand;

    public Player(int index) {
        this.index = index;
        this.hand = new ArrayList<>(13);
    }

    public int getIndex() {
        return index;
    }

    public void removeCardFromHand(List<Card> cards) {
        hand.removeAll(cards);
    }

    public abstract List<Card> takeATurn();

    public boolean handContainsClub3() {
        return containsClub3(hand);
    }

    public boolean isHandEmpty() {
        return hand.isEmpty();
    }

    public List<Card> getHand() {
        return hand;
    }

    public abstract void nameMySelf();

    public void addCardToHand(Card card) {
        hand.add(card);
        hand.sort(Card::compareTo);
    }

    public void addCardToHand(List<Card> cards) {
        hand.addAll(cards);
        hand.sort(Card::compareTo);
    }

    public String getName() {
        return name;
    }

}
