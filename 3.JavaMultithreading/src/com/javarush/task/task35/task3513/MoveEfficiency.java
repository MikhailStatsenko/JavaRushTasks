package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private Move move;
    private int score;
    private int numberOfEmptyTiles;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.move = move;
        this.score = score;
        this.numberOfEmptyTiles = numberOfEmptyTiles;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        if (numberOfEmptyTiles == o.numberOfEmptyTiles) {
            return Integer.compare(score, o.score);
        }
        return Integer.compare(numberOfEmptyTiles, o.numberOfEmptyTiles);
    }
}
