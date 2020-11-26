package hw7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;

    public GameActionListener(int row, int cell, GameButton gameButton) {
        this.row = row;
        this.cell = cell;
        this.button = gameButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();
        if (board.isTurnable(row, cell)) {
            updateByPlayersData(board);

            if (board.isFull()) {
                board.getGame().showMessage("Ничья!");
                board.emptyField();
                board.getGame().passTurn();
            } else if (!board.getGame().getCurrentPlayer().isRealPlayer()){
                updateByAIData(board);
            }
        } else {
            board.getGame().showMessage("Некорректный ход!");
        }
    }

    private void updateByPlayersData(GameBoard board) {
        board.updateGameField(row, cell);
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Вы выйграли!");
            board.emptyField();
        } else {
            board.getGame().passTurn();
        }
    }

    private void updateByAIData(GameBoard board) {
        int weakX = -1, weakY = -1;  //координаты предстоящего хода (самое слабое место противника)
        Random random = new Random();

        if (board.getGame().getDifficulty() == 0) {
            do {
                weakX = random.nextInt(GameBoard.dimension);
                weakY = random.nextInt(GameBoard.dimension);
            } while (!board.isTurnable(weakX, weakY));
        }
        else if (board.getGame().getDifficulty() == 1) {
            char[][] map = board.getGameField();
            int SIZE = board.getDimension();
            char myC = board.getGame().getCurrentPlayer().getPlayerSign();
            char enemyC = board.getGame().getNextPlayer().getPlayerSign();
            /*
             * 1) проверяем всё поле. Если можем победить, завершаем игру
             */
            int myd1 = 0, myd2 = 0, d1i = -1, d2i = -1, d2j = -1;  //один раз посчитаем кол-во НАШИХ ячеек на главной и побочной диагоналях
            for (int i = 0; i < SIZE; i++) {
                if (map[i][i] == myC) myd1++;  //поиск на главной диагонали
                else if (board.isTurnable(i, i)) d1i = i;  //запоминаем координаты ячейки только если можем ее занять
                if (map[SIZE - i - 1][i] == myC) myd2++;  //поиск на побочной диагонали
                else if (board.isTurnable(SIZE - i - 1, i)) {  //запоминаем координаты ячейки только если можем ее занять
                    d2i = SIZE - i - 1;
                    d2j = i;
                }

                int myh = 0, myv = 0, hi = -1, hj = -1, vi = -1, vj = -1;  // SIZE раз посчитаем кол-во НАШИХ ячеек на горизонтальных и вертикальных линиях
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] == myC) myh++;
                    else if (board.isTurnable(i, j)) {  //запоминаем координаты ячейки только если можем ее занять
                        hi = i;
                        hj = j;
                    }
                    if (map[j][i] == myC) myv++;
                    else if (board.isTurnable(j, i)) {  //запоминаем координаты ячейки только если можем ее занять
                        vi = j;
                        vj = i;
                    }
                }
                if (myh == SIZE - 1 && hi != -1 && hj != -1) {  //если есть координаты свободной ячейки на горизонтальной линии и все остальные ячейки на ней наши
                    weakX = hi;
                    weakY = hj;
                }
                if (myv == SIZE - 1 && vi != -1 && vj != -1) {  //если есть координаты свободной ячейки на вертикальной линии и все остальные ячейки на ней наши
                    weakX = vi;
                    weakY = vj;
                }
            }
            if (myd1 == SIZE - 1 && d1i != -1) {
                weakX = d1i;
                weakY = d1i;
            }
            if (myd2 == SIZE - 1 && d2i != -1 && d2j != -1) {
                weakX = d2i;
                weakY = d2j;
            }

            /*
             * 2) если победить не вышло, будем обороняться
             */
            if (weakX == -1 && weakY == -1) {

                int maxH = 0, maxV = 0;  //максимумы занятых противником ячеек в опасных строках и столбцах
                //опасность - это кол-во занятых противником ячеек в линини.
                int dangerousH = -1, dangerousV = -1;  //самая опасная строка и столбец. У читываться будут только те, которые мы еще не заблокировали своим символом.
                int d1 = 0, d2 = 0;  //для рассчета кол-ва занятых противником ячеек на главной и побочной диагоналях
                boolean d1IsDanger = true, d2IsDanger = true;  //если в линии найдется наш знак, то изменим на false
                for (int i = 0; i < SIZE; i++) {
                    if (map[i][i] == enemyC) d1++;
                    else if (map[i][i] == myC) d1IsDanger = false;
                    if (map[SIZE - i - 1][i] == enemyC) d2++;
                    else if (map[SIZE - i - 1][i] == myC) d2IsDanger = false;

                    int h = 0, v = 0;  // SIZE раз посчитаем кол-во крестиков на горизонтальных и вертикальных линиях
                    boolean hIsDanger = true, vIsDanger = true;
                    for (int j = 0; j < SIZE; j++) {  //проверим горизонтальные и вертикальные в одном цикле
                        if (map[i][j] == enemyC) h++;
                        else if (map[i][j] == myC) hIsDanger = false;
                        if (map[j][i] == enemyC) v++;
                        else if (map[j][i] == myC) vIsDanger = false;
                    }
                    if (h > maxH && hIsDanger) {
                        dangerousH = i;
                        maxH = h;
                    }
                    if (v > maxV && vIsDanger) {
                        dangerousV = i;
                        maxV = v;
                    }
                }
                if (!d1IsDanger) d1 = -1;  //если диагонали не опасны, то дальше они не будут учитываться
                if (!d2IsDanger)
                    d2 = -1;  //не забываем, что если опасных горизонталей и вертикалей не было, то все еще dangerousH = -1, dangerousV = -1
                //если нет ни одной опасной линии, значит поле пусто, тогда просто сходим в центр
                if (d1 == -1 && d2 == -1 && dangerousH == -1 && dangerousV == -1) {
                    weakX = (int) (SIZE / 2);
                    weakY = (int) (SIZE / 2);
                }


                //здесь мы уже имеем представление о самой опасной строке, столбце и диагонялях. Осталось выбрать координаты нашего хода оптимальным способом.
                int maxDanger = Math.max(Math.max(maxH, maxV), Math.max(d1, d2));

                if (d1 == maxDanger) {  //главная диагональ
                    int hm = -1;
                    for (int i = 0; i < SIZE; i++) {
                        if (board.isTurnable(i, i)) {
                            int hn = hindranceToEnemy(i, i, myC, map, SIZE);
                            if (hn > hm) {
                                hm = hn;
                                weakX = i;
                                weakY = i;
                            }
                        }
                    }
                } else if (d2 == maxDanger) {  //побочная диагональ
                    int hm = -1;
                    for (int i = 0; i < SIZE; i++) {
                        int xp = SIZE - 1 - i;
                        if (board.isTurnable(xp, i)) {
                            int hn = hindranceToEnemy(xp, i, myC, map, SIZE);
                            if (hn > hm) {
                                hm = hn;
                                weakX = xp;
                                weakY = i;
                            }
                        }
                    }
                } else if (maxH == maxDanger) {  //опасная горизонтальная линия
                    int hm = -1;
                    for (int i = 0; i < SIZE; i++) {
                        if (board.isTurnable(dangerousH, i)) {
                            int hn = hindranceToEnemy(dangerousH, i, myC, map, SIZE);
                            if (hn > hm) {
                                hm = hn;
                                weakX = dangerousH;
                                weakY = i;
                            }
                        }
                    }
                } else if (maxV == maxDanger) {  //опасная вертикальная линия
                    int hm = -1;
                    for (int i = 0; i < SIZE; i++) {
                        if (board.isTurnable(i, dangerousV)) {
                            int hn = hindranceToEnemy(i, dangerousV, myC, map, SIZE);
                            if (hn > hm) {
                                hm = hn;
                                weakX = i;
                                weakY = dangerousV;
                            }
                        }
                    }
                } else
                    do {
                        weakX = random.nextInt(SIZE);
                        weakY = random.nextInt(SIZE);
                    } while (!board.isTurnable(weakX, weakY));

            }
        }

        //делаем ход и проверяем победили ли
        board.updateGameField(weakX, weakY);
        int cellIndex = GameBoard.dimension * weakX + weakY;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Компьютер выйграл!");
            board.emptyField();
        }
        board.getGame().passTurn();
    }

    private int hindranceToEnemy(int x, int y, char myC, char[][] map, int SIZE) {  //просчет помехи(hindrance) для противника, в случае, если мы займем ячейку
        /*
         * занятие данной ячейки не помешает противнику (бессмысленно) -> 0
         * мы защитим только одну линию -> 1
         * защитим 2 линии -> 2
         * защитим 3 линии -> 3
         * защитим 4 линии -> 4
         */

        int hDefended = 0, vDefended = 0, d1Defended = 0, d2Defended = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[x][i] == myC)
                hDefended = 1;
            if (map[i][y] == myC)
                vDefended = 1;
            if (map[i][i] == myC)
                d1Defended = 1;
            if (map[SIZE - 1 - i][i] == myC)
                d2Defended = 1;
        }
        return hDefended + vDefended + d1Defended + d2Defended;
    }
}
