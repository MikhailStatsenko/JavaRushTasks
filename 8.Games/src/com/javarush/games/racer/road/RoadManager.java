package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoadManager {
    private static final int PLAYER_CAR_DISTANCE = 12;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private List<RoadObject> items = new ArrayList<>();
    private int passedCarsCount = 0;
    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        if (type == RoadObjectType.THORN)
            return new Thorn(x, y);
        else if (type == RoadObjectType.DRUNK_CAR)
            return new MovingCar(x, y);
        else
            return new Car(type, x, y);
    }

    private void addRoadObject(RoadObjectType type, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject obj = createRoadObject(type, x, y);
        if (isRoadSpaceFree(obj)) items.add(obj);
    }

    public void draw(Game game) {
        for (RoadObject obj : items)
            obj.draw(game);
    }

    public void move(int boost) {
        for (RoadObject obj : items)
            obj.move(obj.speed + boost, items);
        deletePassedItems();
    }

    public boolean checkCrush(PlayerCar car) {
        for (RoadObject obj : items) {
            if (obj.isCollision(car))
                return true;
        }
        return false;
    }

    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateMovingCar(game);
        generateRegularCar(game);
    }

    private void deletePassedItems() {
        Iterator<RoadObject> it = items.iterator();
        while (it.hasNext()) {
            RoadObject obj = it.next();
            if (obj.y >= RacerGame.HEIGHT) {
                if (obj.type != RoadObjectType.THORN)
                    passedCarsCount++;
                it.remove();
            }
        }
    }

    private boolean isThornExists() {
        return items.stream().filter(x -> x.type == RoadObjectType.THORN).count() > 0;
    }

    private boolean isMovingCarExists() {
        return items.stream().filter(x -> x.type == RoadObjectType.DRUNK_CAR).count() > 0;
    }

    private void generateThorn(Game game) {
        boolean toCreate = game.getRandomNumber(100) < 10 && !isThornExists();
        if (toCreate)
            addRoadObject(RoadObjectType.THORN, game);
    }

    private void generateMovingCar(Game game) {
        boolean toCreate = game.getRandomNumber(100) < 5 && !isThornExists();
        if (toCreate && !isMovingCarExists())
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
    }

    private void generateRegularCar(Game game) {
        int carTypeNumber = game.getRandomNumber(4);
        boolean toCreate = game.getRandomNumber(100) < 30 && !isThornExists();
        if (toCreate)
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
    }

    private boolean isRoadSpaceFree(RoadObject object) {
        for (RoadObject obj : items) {
            if (obj.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE))
                return false;
        }
        return true;
    }

    public int getPassedCarsCount() {
        return passedCarsCount;
    }
}
