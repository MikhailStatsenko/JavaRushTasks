package com.javarush.task.task27.task2712.ad;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();
    private static StatisticAdvertisementManager instance;
    private StatisticAdvertisementManager(){};
    public static StatisticAdvertisementManager getInstance() {
        if (instance == null)
            instance = new StatisticAdvertisementManager();
        return instance;
    }

    public List<Advertisement> getActiveVideos() {
        return advertisementStorage.list().stream()
                .filter(Advertisement::isActive)
                .collect(Collectors.toList());
    }

    public List<Advertisement> getArchivedVideos() {
        return advertisementStorage.list().stream()
                .filter(ad -> !ad.isActive())
                .collect(Collectors.toList());
    }
}
