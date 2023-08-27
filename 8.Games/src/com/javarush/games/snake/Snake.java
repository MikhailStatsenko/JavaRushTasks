package com.javarush.games.snake;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private Direction direction;
    public boolean isAlive;
    private List<GameObject> snakeParts = new ArrayList<>();

    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
        direction = Direction.LEFT;
        isAlive = true;
    }

    public void draw(Game game) {
        for (int i = 0; i < snakeParts.size(); i++) {
            game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE,
                    i == 0 ? HEAD_SIGN : BODY_SIGN, isAlive ? Color.INDIGO : Color.RED, 75);
        }
        createNewHead();
    }

    public void setDirection(Direction direction) {
        if (direction == Direction.UP && this.direction == Direction.DOWN
                || direction == Direction.LEFT && this.direction == Direction.RIGHT
                || direction == Direction.RIGHT && this.direction == Direction.LEFT
                || direction == Direction.DOWN && this.direction == Direction.UP)
            return;

        GameObject part0 = snakeParts.get(0), part1 = snakeParts.get(1);
        if ((direction == Direction.LEFT || direction == Direction.RIGHT) && part0.y == part1.y
                || (direction == Direction.UP || direction == Direction.DOWN) && part0.x == part1.x)
            return;

        this.direction = direction;
    }

    public void move(Apple apple) {
        GameObject head = createNewHead();
        if (checkCollision(head)
                || head.x < 0
                || head.x >= SnakeGame.WIDTH
                || head.y >= SnakeGame.HEIGHT
                || head.y < 0) {
            isAlive = false;
            return;
        }
        snakeParts.add(0, head);

        if (head.x == apple.x && head.y == apple.y)
            apple.isAlive = false;
        else
            removeTail();
    }

    public GameObject createNewHead() {
        GameObject oldHead = snakeParts.get(0);
        if (direction == Direction.LEFT) {
            return new GameObject(oldHead.x - 1, oldHead.y);
        } else if (direction == Direction.RIGHT) {
            return new GameObject(oldHead.x + 1, oldHead.y);
        } else if (direction == Direction.UP) {
            return new GameObject(oldHead.x, oldHead.y - 1);
        } else {
            return new GameObject(oldHead.x, oldHead.y + 1);
        }
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size()-1);
    }

    public boolean checkCollision(GameObject o) {
        for (GameObject part : snakeParts) {
            if (part.x == o.x && part.y == o.y)
                return true;
        }
        return false;
    }

    public int getLength() {
        return snakeParts.size();
    }
}
