package v1;

import v1.cardpattern.CardPattern;

public class PlayerTurn {
    private boolean isPass;
    private CardPattern play;

    public PlayerTurn(boolean isPass, CardPattern play) {
        this.isPass = isPass;
        this.play = play;
    }

    public boolean isPass() {
        return isPass;
    }

    public CardPattern getPlay() {
        return play;
    }
}
