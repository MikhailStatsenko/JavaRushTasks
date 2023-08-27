package com.javarush.games.minigames.mini06;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

/* 
Диалоговые окна
*/

public class MessageGame extends Game {

    @Override
    public void initialize() {
        setScreenSize(3, 3);
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        showMessageDialog(Color.AQUA, "Вы выиграли", Color.GREEN, 70);
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        showMessageDialog(Color.AQUA, "Вы проиграли", Color.RED, 70);
    }
}
