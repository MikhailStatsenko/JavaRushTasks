package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private int timeLeft;
    private long maxAmount;
    private final int timeSeconds;
    private List<Advertisement> optimalVideoSet;
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        this.timeLeft = Integer.MAX_VALUE;
        findOptimalVideoSet(new ArrayList<>(), timeSeconds, 0L);

        if (optimalVideoSet == null || optimalVideoSet.isEmpty()) {
            throw new NoVideoAvailableException();
        }

        optimalVideoSet.sort(Comparator.comparingLong(Advertisement::getAmountPerOneDisplaying)
                .thenComparingInt(Advertisement::getDuration));

        for (Advertisement ad : optimalVideoSet) {
            System.out.printf(
                    "%s is displaying... %d, %d\n",
                    ad.getName(),
                    ad.getAmountPerOneDisplaying(),
                    (1000 * ad.getAmountPerOneDisplaying() / ad.getDuration()));
            ad.revalidate();
        }
    }
    private void findOptimalVideoSet(List<Advertisement> totalList, int curTimeLeft, long curAmount) {
        if (curTimeLeft < 0)
            return;
        else if (curAmount > maxAmount || curAmount == maxAmount && (timeLeft > curTimeLeft
                || timeLeft == curTimeLeft && totalList.size() < optimalVideoSet.size())) {
            this.timeLeft = curTimeLeft;
            this.optimalVideoSet = totalList;
            this.maxAmount = curAmount;

            if (curTimeLeft == 0)
                return;
        }

        storage.list().stream()
                .filter(ad -> ad.getHits() > 0 && !totalList.contains(ad))
                .forEach(ad -> {
                    ArrayList<Advertisement> currentList = new ArrayList<>(totalList);
                    currentList.add(ad);
                    findOptimalVideoSet(currentList,
                            curTimeLeft - ad.getDuration(),
                            curAmount + ad.getAmountPerOneDisplaying());
                });
    }
}
