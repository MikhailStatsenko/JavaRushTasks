package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 11;
    private static final String FLAG = "\uD83D\uDEA9";
    private static final String MINE = "\uD83D\uDCA3";
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int score;
    private int countFlags;
    private int countMinesOnField;
    private int countClosedTiles = SIDE * SIDE;
    private boolean isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        showGrid(false);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(5) < 1;
                if (isMine)
                    countMinesOnField++;
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellValueEx(x, y, x % 2 == y % 2 ? Color.SEAGREEN : Color.DARKSEAGREEN, "");
            }
        }
        countFlags = countMinesOnField;
        countMineNeighbors();
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE || x < 0 || x >= SIDE || gameField[y][x] == gameObject)
                    continue;
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                if (!gameField[x][y].isMine) {
                    List<GameObject> neighbors = getNeighbors(gameField[x][y]);
                    gameField[x][y].countMineNeighbors = (int) neighbors.stream().filter(el -> el.isMine).count();
                }
            }
        }
    }

    private void openTile(int x, int y) {
        GameObject cell = gameField[y][x];
        if (cell.isOpen || cell.isFlag || isGameStopped) return;

        countClosedTiles--;
        cell.isOpen = true;
        setCellColor(x, y, x % 2 == y % 2 ? Color.SADDLEBROWN : Color.SANDYBROWN);

        if (cell.isMine) {
            setCellValueEx(cell.x, cell.y, Color.RED, MINE);
            gameOver();
            return;
        } else if (cell.countMineNeighbors == 0) {
            setCellValue(cell.x, cell.y, "");
            for (GameObject neighbor : getNeighbors(cell)) {
                if (!neighbor.isOpen)
                    openTile(neighbor.x, neighbor.y);
            }
        } else
            setCellNumber(x, y, cell.countMineNeighbors);

        score += 5;
        setScore(score);

        if (countClosedTiles == countMinesOnField)
            win();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped) {
            restart();
            return;
        }
        openTile(x, y);
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }

    private void markTile(int x, int y) {
        if (isGameStopped) return;

        GameObject cell = gameField[y][x];
        if (cell.isOpen || (countFlags <= 0 && !cell.isFlag)) return;

        if (!cell.isFlag) {
            cell.isFlag = true;
            countFlags--;
            setCellValue(cell.x, cell.y, FLAG);
            setCellColor(cell.x, cell.y, Color.LIGHTGRAY);
        } else {
            cell.isFlag = false;
            countFlags++;
            setCellValue(cell.x, cell.y, "");
            setCellColor(cell.x, cell.y, x % 2 == y % 2 ? Color.SEAGREEN : Color.DARKSEAGREEN);
        }
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "GAME OVER", Color.RED, 70);
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU WIN", Color.GREEN, 70);
    }

    private void restart() {
        countClosedTiles = SIDE * SIDE;
        score = 0;
        setScore(score);
        countMinesOnField = 0;
        isGameStopped = false;
        createGame();
    }
}