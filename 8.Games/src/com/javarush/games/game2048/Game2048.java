package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

import java.util.Arrays;

public class Game2048 extends Game {
    private static final int SIDE = 5;
    private int[][] gameField = new int[SIDE][SIDE];
    private int score;
    private boolean isGameStopped = false;
    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    private void createGame() {
        gameField = new int[SIDE][SIDE];
        createNewNumber();
        createNewNumber();
    }

    private void drawScene() {
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                setCellColoredNumber(x, y, gameField[y][x]);
            }
        }
    }

    private void createNewNumber() {
        if (getMaxTileValue() == 2048) win();

        int x = getRandomNumber(SIDE);
        int y = getRandomNumber(SIDE);
        while (gameField[x][y] != 0) {
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);
        }
        gameField[x][y] = getRandomNumber(10) == 9 ? 4 : 2;
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 0:
                return Color.WHITE;
            case 2:
                return Color.PLUM;
            case 4:
                return Color.SLATEBLUE;
            case 8:
                return Color.DODGERBLUE;
            case 16:
                return Color.DARKTURQUOISE;
            case 32:
                return Color.MEDIUMSEAGREEN;
            case 64:
                return Color.LIMEGREEN;
            case 128:
                return Color.DARKORANGE;
            case 256:
                return Color.SALMON;
            case 512:
                return Color.ORANGERED;
            case 1024:
                return Color.DEEPPINK;
            case 2048:
                return Color.MEDIUMVIOLETRED;
            default:
                return Color.NONE;
        }
    }

    private void setCellColoredNumber(int x, int y, int value) {
        setCellValueEx(x, y, getColorByValue(value), value > 0 ? String.valueOf(value) : "");
    }

    private boolean compressRow(int[] row) {
        int insertPosition = 0;
        boolean result = false;
        for (int i = 0; i < SIDE; i++) {
            if (row[i] > 0) {
                if (i != insertPosition) {
                    row[insertPosition] = row[i];
                    row[i] = 0;
                    result = true;
                }
                insertPosition++;
            }
        }
        return result;
    }

    private boolean mergeRow(int[] row) {
        boolean result = false;
        for (int i = 0; i < SIDE - 1; i++) {
            if (row[i] == row[i+1] && row[i] != 0) {
                score += row[i]*2;
                setScore(score);

                row[i] *= 2;
                row[++i] = 0;
                result = true;
            }
        }
        return result;
    }

    @Override
    public void onKeyPress(Key key) {
        if (isGameStopped) {
            if (key == Key.SPACE) {
                score = 0;
                setScore(score);
                isGameStopped = false;
                createGame();
                drawScene();
            } else
                return;
        }

        if (!canUserMove()) {
            gameOver();
            return;
        }

        if (key == Key.LEFT)
            moveLeft();
        else if (key == Key.RIGHT)
            moveRight();
        else if (key == Key.UP)
            moveUp();
        else if (key == Key.DOWN)
            moveDown();
        else
            return;
        drawScene();
    }


    private void moveLeft() {
        boolean moved = false;
        boolean compressed, merged;
        for (int[] row : gameField) {
            compressed = compressRow(row);
            merged = mergeRow(row);
            compressRow(row);
            if (!moved) moved = compressed || merged;
        }
        if (moved) createNewNumber();
    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private void rotateClockwise() {
        int[][] copy = new int[SIDE][SIDE];
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                copy[j][SIDE - i - 1] = gameField[i][j];
            }
        }
        gameField = copy;
    }

    private int getMaxTileValue() {
        return Arrays.stream(gameField)
                .flatMapToInt(Arrays::stream)
                .max()
                .getAsInt();
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU WIN", Color.GREEN, 70);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "GAME OVER", Color.RED, 70);
    }

    private boolean canUserMove() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                if (gameField[y][x] == 0) {
                    return true;
                } else if (y < SIDE - 1 && gameField[y][x] == gameField[y + 1][x]) {
                    return true;
                } else if ((x < SIDE - 1) && gameField[y][x] == gameField[y][x + 1]) {
                    return true;
                }
            }
        }
        return false;
    }
}
