package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int COMPLEXITY = 5;
    private static final int PLAYER_BULLETS_MAX = 3;
    private List<Star> stars;
    private List<Bullet> enemyBullets;
    private List<Bullet> playerBullets;
    private EnemyFleet enemyFleet;
    private PlayerShip playerShip;
    private int score;
    private int animationsCount;
    private boolean isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        createStars();
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerShip = new PlayerShip();
        playerBullets = new ArrayList<>();
        animationsCount = 0;
        score = 0;
        isGameStopped = false;
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
        playerShip.draw(this);
        enemyFleet.draw(this);
        enemyBullets.forEach(bullet -> bullet.draw(this));
        playerBullets.forEach(bullet -> bullet.draw(this));
    }

    private void drawField() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.BLACK, "");
            }
        }

        stars.forEach(star -> star.draw(this));
    }

    private void createStars() {
        stars = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stars.add(new Star(getRandomNumber(3, 62), getRandomNumber(3, 50)));
        }
    }

    @Override
    public void onTurn(int step) {
        setScore(score);
        moveSpaceObjects();
        check();
        Bullet shot = enemyFleet.fire(this);
        if (shot != null) enemyBullets.add(shot);
        drawScene();
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
        playerShip.move();
        enemyBullets.forEach(Bullet::move);
        playerBullets.forEach(Bullet::move);
    }

    private void removeDeadBullets() {
        enemyBullets.removeIf(bullet -> !bullet.isAlive || bullet.y >= HEIGHT-1);
        playerBullets.removeIf(bullet -> !bullet.isAlive || bullet.y + bullet.height < 0);
    }

    private void check() {
        if (!playerShip.isAlive) stopGameWithDelay();
        playerShip.verifyHit(enemyBullets);
        int newScore = enemyFleet.verifyHit(playerBullets);
        enemyFleet.deleteHiddenShips();
        score += newScore;

        if (enemyFleet.getBottomBorder() >= playerShip.y)
            playerShip.kill();
        if (enemyFleet.getShipsCount() == 0) {
            playerShip.win();
            stopGameWithDelay();
        }
        removeDeadBullets();
    }

    private void stopGame(boolean isWin) {
        isGameStopped = true;
        stopTurnTimer();
        if (isWin)
            showMessageDialog(Color.NONE, "YOU WIN", Color.GREEN, 80);
        else
            showMessageDialog(Color.NONE, "GAME OVER", Color.RED, 80);
    }

    private void stopGameWithDelay() {
        animationsCount++;
        if (animationsCount >= 10)
            stopGame(playerShip.isAlive);
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.SPACE && isGameStopped)
            createGame();
        else if (key == Key.SPACE) {
            Bullet bullet = playerShip.fire();
            if (bullet != null && playerBullets.size() < PLAYER_BULLETS_MAX)
                playerBullets.add(bullet);
        } else if (key == Key.LEFT)
            playerShip.setDirection(Direction.LEFT);
        else if (key == Key.RIGHT)
            playerShip.setDirection(Direction.RIGHT);
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.LEFT && playerShip.getDirection() == Direction.LEFT ||
                key == Key.RIGHT && playerShip.getDirection() == Direction.RIGHT)
            playerShip.setDirection(Direction.UP);
    }

    @Override
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        if (x < 0 || x > WIDTH - 1 || y < 0 || y > HEIGHT - 1) return;
        super.setCellValueEx(x, y, cellColor, value);
    }
}
