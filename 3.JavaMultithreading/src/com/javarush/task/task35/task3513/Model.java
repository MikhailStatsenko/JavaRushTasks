package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;
    private boolean isSaveNeeded = true;
    private final Stack<Tile[][]> previousStates = new Stack<>();
    private final Stack<Integer> previousScores = new Stack<>();


    public Model() {
        resetGameTiles();
    }
    public boolean hasBoardChanged() {
        return Arrays.stream(previousStates.peek())
                .flatMap(Arrays::stream)
                .mapToInt(tile -> tile.value)
                .sum()
                !=
                Arrays.stream(gameTiles)
                        .flatMap(Arrays::stream)
                        .mapToInt(tile -> tile.value)
                        .sum();
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();

        if (!hasBoardChanged())
            return new MoveEfficiency(-1, 0, move);

        MoveEfficiency result = new MoveEfficiency(getEmptyTiles().size(), score, move);
        rollback();

        return result;
    }
    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());

        queue.offer(getMoveEfficiency(this::left));
        queue.offer(getMoveEfficiency(this::right));
        queue.offer(getMoveEfficiency(this::up));
        queue.offer(getMoveEfficiency(this::down));

        queue.poll().getMove().move();
    }
    public void randomMove() {
        switch ((int) (Math.random() % 100) % 4) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
        }
    }
    private void saveState(Tile[][] tiles) {
        Tile[][] tilesToSave = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tilesToSave[i][j] = new Tile(tiles[i][j].value);
            }
        }

        previousStates.push(tilesToSave);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (previousStates.isEmpty() || previousScores.isEmpty())
            return;

        gameTiles = previousStates.pop();
        score = previousScores.pop();
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty())
                    emptyTiles.add(gameTiles[i][j]);
            }
        }
        return emptyTiles;
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();

        if (emptyTiles.isEmpty())
            return;

        Tile randomEmptyTile = emptyTiles.get((int) (Math.random() * emptyTiles.size()));
        randomEmptyTile.value = Math.random() < 0.9 ? 2 : 4;
    }

    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }

        boolean fieldChanged = false;

        for (Tile[] row : gameTiles) {
            fieldChanged |= compressTiles(row);
            fieldChanged |= mergeTiles(row);
        }

        if (fieldChanged) {
            addTile();
        }

        isSaveNeeded = true;
    }

    public void right() {
        saveState(gameTiles);
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
    }

    public void up() {
        saveState(gameTiles);
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
    }

    public void down() {
        saveState(gameTiles);
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private void rotateClockwise() {
        Tile[][] rotated = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                rotated[j][i] = gameTiles[FIELD_WIDTH-i-1][j];
            }
        }
        gameTiles = rotated;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean changed = false;

        boolean found = false;
        int free = Integer.MAX_VALUE;
        for (int i = 0; i < tiles.length; i++) {
            if (!found && tiles[i].isEmpty()) {
                free = i;
                found = true;
            }

            if (!tiles[i].isEmpty() && i > free) {
                tiles[free].value = tiles[i].value;
                tiles[i].value = 0;
                found = false;
                i = free;
                changed = true;
            }
        }
        return changed;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean changed = false;
        for (int i = 0; i < tiles.length-1; i++) {
            if (!tiles[i].isEmpty() && tiles[i].value == tiles[i+1].value) {
                tiles[i].value *= 2;
                for (int j = i+1; j < tiles.length - 1; j++) {
                    tiles[j] = tiles[j+1];
                }
                tiles[tiles.length-1] = new Tile();

                maxTile = Math.max(maxTile, tiles[i].value);
                score += tiles[i].value;
                changed = true;
            }
        }
        return changed;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        if (Arrays.stream(gameTiles).flatMap(Arrays::stream).anyMatch(Tile::isEmpty))
            return true;

        for (int i = 0; i < FIELD_WIDTH-1; i++) {
            for (int j = 0; j < FIELD_WIDTH-1; j++) {
                if (gameTiles[i][j].value == gameTiles[i+1][j].value
                        || gameTiles[i][j].value == gameTiles[i][j+1].value)
                    return true;
            }
        }
        return false;
    }
}
