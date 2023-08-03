package com.javarush.games.moonlander;

import com.javarush.games.moonlander.ShapeMatrix;

public class Rocket extends GameObject {
    private double speedY = 0;
    private double boost = 0.05;

    public Rocket(double x, double y) {
        super(x, y, ShapeMatrix.ROCKET);
    }

    public void move() {
        speedY += boost;
        y += speedY;
    }
}
