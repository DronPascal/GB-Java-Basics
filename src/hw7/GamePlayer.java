package hw7;

public class GamePlayer {
    private char playerSign;
    private boolean realPlayer = true;
    private int intelligence;

    public GamePlayer(boolean isRealPlayer, char playerSign) {
        this.realPlayer = isRealPlayer;
        this.playerSign = playerSign;
        if (!isRealPlayer) intelligence = 0;
    }

    public boolean isRealPlayer() {
        return realPlayer;
    }

    public char getPlayerSign() {
        return playerSign;
    }

    public int getIntelligence() {
        return intelligence;
    }
}
