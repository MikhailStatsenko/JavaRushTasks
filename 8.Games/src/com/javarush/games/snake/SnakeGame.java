package com.javarush.games.snake;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;

public class SnakeGame extends Game {
    private int score;
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;
    private Apple apple;

    private boolean isGameStopped;

    private int turnDelay;

    private Snake snake;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        isGameStopped = false;
        snake = new Snake(WIDTH/2, HEIGHT/2);
        createNewApple();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        score = 0;
        setScore(score);
        drawScene();
    }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, x%2 == y%2 ? Color.SEAGREEN : Color.DARKSEAGREEN, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.LEFT)
            snake.setDirection(Direction.LEFT);
        else if (key == Key.RIGHT)
            snake.setDirection(Direction.RIGHT);
        else if (key == Key.UP)
            snake.setDirection(Direction.UP);
        else if (key == Key.DOWN)
            snake.setDirection(Direction.DOWN);
        else if (key == Key.SPACE && isGameStopped) {
            createGame();
        }
    }

    @Override
    public void onTurn(int step) {
        if (!snake.isAlive) gameOver();

        if (snake.getLength() > GOAL) win();

        snake.move(apple);
        if (!apple.isAlive) {
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
            createNewApple();
        }
        drawScene();
    }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.MIDNIGHTBLUE, "GAME OVER", Color.WHITE, 80);
    }

    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.CORNFLOWERBLUE, "YOU WIN", Color.WHITE, 80);
    }

    private void createNewApple() {
        apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        while (snake.checkCollision(apple))
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
    }

    @Override
    public void setScore(int score) {
        super.setScore(score);
    }
}
