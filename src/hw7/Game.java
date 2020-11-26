package hw7;

import javax.swing.*;

public class Game {
    private GameBoard board;
    private GamePlayer[] gamePlayers = new GamePlayer[2];
    private int playersTurn = 0;
    private int difficulty = 0;

    public Game() {
        this.board = new GameBoard(this);
    }

    public void initGame() {
        gamePlayers[0] = new GamePlayer(true, '❌');
        gamePlayers[1] = new GamePlayer(false, '◯');
        difficulty = 0;
    }

    //передача хода
    void passTurn() {
        if (playersTurn == 0) playersTurn = 1;
        else playersTurn = 0;
    }

    GamePlayer getCurrentPlayer() {
        return gamePlayers[playersTurn];
    }

    GamePlayer getNextPlayer() {
        return gamePlayers[(playersTurn + 1) % 2];
    }

    void showMessage(String messageText) {
        JOptionPane.showMessageDialog(board, messageText);
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
