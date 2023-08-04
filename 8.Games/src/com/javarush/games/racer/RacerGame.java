package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;
    private static final int RACE_GOAL_CARS_COUNT = 35;
    private PlayerCar player;
    private FinishLine finishLine;
    private ProgressBar progressBar;
    private RoadManager roadManager;
    private RoadMarking roadMarking;
    private boolean isGameStopped;
    private int score;
    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        player = new PlayerCar();
        finishLine = new FinishLine();
        roadManager = new RoadManager();
        roadMarking = new RoadMarking();
        progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
        isGameStopped = false;
        score = 2000;
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
        roadMarking.draw(this);
        finishLine.draw(this);
        progressBar.draw(this);
        roadManager.draw(this);
        player.draw(this);
    }

    private void drawField() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (x == CENTER_X)
                    setCellColor(CENTER_X, y, Color.WHITE);
                else if (x >= ROADSIDE_WIDTH && x < WIDTH - ROADSIDE_WIDTH)
                    setCellColor(x, y, Color.DIMGRAY);
                else
                    setCellColor(x, y, Color.GREEN);
            }
        }
        setCellValueEx(2, 3, Color.BLACK, "S", Color.WHITE, 100);
        setCellValueEx(3, 3, Color.BLACK, "C", Color.WHITE, 100);
        setCellValueEx(4, 3, Color.BLACK, "O", Color.WHITE, 100);
        setCellValueEx(5, 3, Color.BLACK, "R", Color.WHITE, 100);
        setCellValueEx(6, 3, Color.BLACK, "E", Color.WHITE, 100);
        setCellValueEx(7, 3, Color.BLACK, ":", Color.WHITE, 100);
        setCellValueEx(8, 3, Color.BLACK, score / 1000 > 0 ? String.valueOf(score / 1000) : "", Color.WHITE, 100);
        setCellValueEx(9, 3, Color.BLACK, score % 1000 / 100 > 0 ? String.valueOf(score % 1000 / 100) : "", Color.WHITE, 100);
        setCellValueEx(10, 3, Color.BLACK, score % 100 / 10 > 0 ? String.valueOf(score % 100 / 10) : "", Color.WHITE, 100);
        setCellValueEx(11, 3, Color.BLACK, score % 10 > 0 ? String.valueOf(score % 10) : "", Color.WHITE, 100);
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x < 0 || x > WIDTH - 1 || y < 0 || y > HEIGHT - 1) return;

        super.setCellColor(x, y, color);
    }

    private void moveAll() {
        finishLine.move(player.speed);
        roadManager.move(player.speed);
        roadMarking.move(player.speed);
        progressBar.move(roadManager.getPassedCarsCount());
        player.move();
    }

    @Override
    public void onTurn(int step) {
        if (roadManager.checkCrush(player)) {
            gameOver();
            drawScene();
            return;
        }
        if (roadManager.getPassedCarsCount() >= RACE_GOAL_CARS_COUNT)
            finishLine.show();
        if (finishLine.isCrossed(player)) {
            win();
            drawScene();
            return;
        }
        score--;
        setScore(score);
        roadManager.generateNewRoadObjects(this);
        roadManager.draw(this);
        moveAll();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (isGameStopped && key == Key.SPACE)
            createGame();
        if (key == Key.UP)
            player.speed = 2;
        else if (key == Key.RIGHT)
            player.setDirection(Direction.RIGHT);
        else if (key == Key.LEFT)
            player.setDirection(Direction.LEFT);
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.UP)
            player.speed = 1;
        else if (key == Key.RIGHT && player.getDirection() == Direction.RIGHT
                || key == Key.LEFT && player.getDirection() == Direction.LEFT)
            player.setDirection(Direction.NONE);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "GAME OVER", Color.RED, 70);
        stopTurnTimer();
        player.stop();
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU WIN", Color.GREEN, 70);
        stopTurnTimer();
    }
}
