package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result = new ArrayList<>();
        for (String word : words) {
            result.add(findWord(crossword, word));
        }
        return result;
    }

    private static List<int[]> findIndexes(int[][] crossword, int firstLetter) {
        List<int[]> indexes = new ArrayList<>();
        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword[0].length; j++) {
                if (crossword[i][j] == firstLetter) {
                    indexes.add(new int[]{i, j});
                }
            }
        }
        return indexes;
    }

    private static Word findWord(int[][] crossWord, String word) {
        List<int[]> indexes = findIndexes(crossWord, word.charAt(0));

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
        for (int[] index : indexes) {
            for (int[] direction : directions) {
                int[] result = search(crossWord, word, index[0], index[1], 0, direction[0], direction[1]);
                if (result[0] != -1 && result[1] != -1) {
                    Word w = new Word(word);
                    w.setStartPoint(index[1], index[0]);
                    w.setEndPoint(result[1], result[0]);
                    return w;
                }
            }
        }
        return null;
    }

    private static int[] search(int[][] crossWord,
                                String word, int x, int y, int index, int moveX, int moveY) {
        try {
            if (index > word.length() - 1 || crossWord[x][y] != word.charAt(index))
                return new int[]{-1, -1};
        } catch (IndexOutOfBoundsException e) {
            return new int[]{-1, -1};
        }

        if (index == word.length()-1)
            return new int[]{x, y};
        else
            return search(crossWord, word, x+moveX, y+moveY, ++index, moveX, moveY);
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
