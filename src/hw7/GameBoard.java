package hw7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {
    static int dimension = 3;
    static int cellSize = 150;
    private char[][] gameField;
    private GameButton[] gameButtons;

    private Game game;

    static final char nullSymbol = '\u0000';

    public GameBoard(Game currentGame) {
        this.game = currentGame;
        initField();
    }

    private void initField() {
        setBounds(cellSize * dimension, cellSize * dimension, 290, 300);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField();
            }
        });
        JButton aiLevelButton = new JButton("Сложность: Легкая");
        aiLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gameDifficulty = game.getDifficulty();
                aiLevelButton.setLabel(gameDifficulty == 0 ? "Сложность: Тяжелая" : "Сложность: Легкая");
                game.setDifficulty((gameDifficulty+1)%2);
            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.add(aiLevelButton);
        controlPanel.setSize(cellSize * dimension, 150);

        JPanel gameFieldPanel = new JPanel();
        gameFieldPanel.setLayout(new GridLayout(dimension, dimension));
        gameFieldPanel.setSize(cellSize * dimension, cellSize * dimension);

        gameField = new char[dimension][dimension];
        gameButtons = new GameButton[dimension * dimension];

        //инициализация игрового поля
        for (int i = 0; i < (dimension * dimension); i++) {
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;
        }

        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    void emptyField() {
        for (int i = 0; i < (dimension * dimension); i++) {
            gameButtons[i].setText("");
            int x = i / GameBoard.dimension;
            int y = i % GameBoard.dimension;

            gameField[x][y] = nullSymbol;
        }
    }

    Game getGame() {
        return game;
    }

    boolean isTurnable(int x, int y) {
        boolean result = false;
        if (x >= 0 && y >= 0 && gameField[x][y] == nullSymbol)
            result = true;
        return result;
    }

    void updateGameField(int x, int y) {
        gameField[x][y] = game.getCurrentPlayer().getPlayerSign();
    }

    boolean checkWin() {
        boolean result = false;

        char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();

        if (isGameOver(playerSymbol)) {
            result = true;
        }

        return result;
    }

    boolean isFull() {
        boolean result = true;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                if (gameField[i][j] == nullSymbol)
                    result = false;
        return result;
    }

    boolean isGameOver(char c) {
        int d1 = 0, d2 = 0;  //один раз посчитаем кол-во X/O на главной и побочной диагоналях
        for (int i = 0; i < dimension; i++) {
            if (gameField[i][i] == c) d1++;
            if (gameField[dimension - i - 1][i] == c) d2++;

            int h = 0, v = 0;  // dimension раз посчитаем кол-во X/O на горизонтальных и вертикальных линиях
            for (int j = 0; j < dimension; j++) {
                if (gameField[i][j] == c) h++;
                if (gameField[j][i] == c) v++;
            }
            if (h == dimension || v == dimension) return true;
        }
        if (d1 == dimension || d2 == dimension) return true;
        return false;
    }

    public GameButton getButton(int buttonIndex) {
        return gameButtons[buttonIndex];
    }

    public char[][] getGameField() {
        return gameField;
    }

    public static int getDimension() {
        return dimension;
    }
}
