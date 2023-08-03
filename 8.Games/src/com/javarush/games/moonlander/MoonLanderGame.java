package com.javarush.games.moonlander;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private Rocket rocket;
    private GameObject platform;
    private GameObject landscape;
    private boolean movingUp;
    private int score;
    private boolean movingRight;
    private boolean movingLeft;
    private boolean isGameStopped;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        createGameObjects();
        score = 1000;
        isGameStopped = false;
        movingUp = movingRight = movingLeft = false;
        drawScene();
        setTurnTimer(50);
    }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.BLACK);
            }
        }
        landscape.draw(this);
        rocket.draw(this);
    }

    private void createGameObjects() {
        rocket = new Rocket(WIDTH / 2, 0);
        platform = new GameObject(23, MoonLanderGame.HEIGHT - 1, ShapeMatrix.PLATFORM);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
    }

    @Override
    public void onTurn(int step) {
        if (score > 0) score -= 1;
        rocket.move(movingUp, movingRight, movingLeft);
        check();
        setScore(score);
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x < 0 || y < 0 || x > WIDTH - 1 || y > HEIGHT - 1) return;
        super.setCellColor(x, y, color);
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.DOWN)
            movingUp = true;
        else if (key == Key.RIGHT) {
            movingRight = true;
            movingLeft = false;
        } else if (key == Key.LEFT) {
            movingLeft = true;
            movingRight = false;
        } else if (key == Key.SPACE && isGameStopped)
            createGame();
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.DOWN) movingUp = false;
        else if (key == Key.RIGHT) movingRight = false;
        else if (key == Key.LEFT) movingLeft = false;
    }

    private void check() {
        if (rocket.isCollision(platform))
            win();
        else if (rocket.isCollision(landscape))
            gameOver();
    }

    private void win() {
        rocket.land();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU WIN", Color.GREEN, 70);
        stopTurnTimer();
    }

    private void gameOver() {
        score = 0;
        rocket.crash();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "GAME OVER", Color.RED, 70);
        stopTurnTimer();
    }
}


