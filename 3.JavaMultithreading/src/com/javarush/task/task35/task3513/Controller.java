package com.javarush.task.task35.task3513;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private View view;
    private Model model;
    private static final int WINNING_TILE = 2048;

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.score;
    }

    public void resetGame() {
        view.isGameWon = false;
        view.isGameLost = false;
        model.score = 0;
        model.maxTile = 0;
        model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            resetGame();
        if (!model.canMove())
            view.isGameLost = true;

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            model.left();
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            model.right();
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            model.up();
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            model.down();

        if (e.getKeyCode() == KeyEvent.VK_Z)
            model.rollback();
        else if (e.getKeyCode() == KeyEvent.VK_R)
            model.randomMove();
        else if (e.getKeyCode() == KeyEvent.VK_A)
            model.autoMove();

        if (model.maxTile == WINNING_TILE)
            view.isGameWon = true;

        view.repaint();
    }

    public View getView() {
        return view;
    }
}
