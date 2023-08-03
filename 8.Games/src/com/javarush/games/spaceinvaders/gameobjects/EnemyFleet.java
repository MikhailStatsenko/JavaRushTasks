package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;
    private Direction direction = Direction.RIGHT;

    private List<EnemyShip> ships;

    public EnemyFleet() {
        createShips();
    }

    private void createShips() {
        ships = new ArrayList<>();
        ships.add(new Boss(
                STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1, 5));
        for (int x = 0; x < COLUMNS_COUNT; x++) {
            for (int y = 0; y < ROWS_COUNT; y++)
                ships.add(new EnemyShip(x * STEP, y * STEP + 12));
        }
    }

    public void draw(Game game) {
        ships.forEach(ship -> ship.draw(game));
    }

    private double getLeftBorder() {
        double min = ships.get(0).x;
        for (int i = 1; i < ships.size(); i++) {
            double curr = ships.get(i).x;
            if (ships.get(i).x < min)
                min = curr;
        }
        return min;
    }

    private double getRightBorder() {
        Ship max = ships.get(0);
        for (int i = 1; i < ships.size(); i++) {
            Ship curr = ships.get(i);
            if (ships.get(i).x > max.x)
                max = curr;
        }
        return max.x + max.width;
    }

    private double getSpeed() {
        return Math.min(2.0, 3.0 / ships.size());
    }

    public void move() {
        if (ships.isEmpty()) return;

        boolean speedChanged = false;
        if (direction == Direction.LEFT && getLeftBorder() < 0) {
            direction = Direction.RIGHT;
            speedChanged = true;
        } if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
            direction = direction.LEFT;
            speedChanged = true;
        }

        double speed = getSpeed();
        for (EnemyShip ship : ships)
            ship.move(speedChanged ? Direction.DOWN : direction, speed);
    }

    public Bullet fire(Game game) {
        int probability = game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY);
        if (ships.isEmpty() || probability > 0) return null;

        int shipNumber = game.getRandomNumber(ships.size());
        return ships.get(shipNumber).fire();
    }

    public int verifyHit(List<Bullet> bullets) {
        if (bullets.isEmpty()) return 0;

        int scoreSum = 0;
        for (EnemyShip ship : ships) {
            for (Bullet bullet : bullets) {
                if (bullet.isCollision(ship) && ship.isAlive && bullet.isAlive) {
                    scoreSum += ship.score;
                    ship.kill();
                    bullet.kill();
                }
            }
        }
        return scoreSum;
    }

    public void deleteHiddenShips() {
        ships.removeIf(ship -> !ship.isVisible());
    }
    public double getBottomBorder() {
        double bottom = 0;
        for (GameObject ship : ships) {
            if (ship.y + ship.height > bottom) {
                bottom = ship.y + ship.height;
            }
        }
        return bottom;
    }

    public int getShipsCount() {
        return ships.size();
    }
}
