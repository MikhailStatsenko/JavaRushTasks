package com.javarush.games.minigames.mini05;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

/* 
Цвета радуги
*/

public class RainbowGame extends Game {
    @Override
    public void initialize() {
        setScreenSize(10, 7);
        Color[] arr = new Color[]{Color.RED, Color.ORANGE, Color.YELLOW,
                Color.GREEN, Color.BLUE, Color.INDIGO, Color.VIOLET};
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 7; y++) {
                setCellColor(x, y, arr[y]);
            }
        }
    }
}
