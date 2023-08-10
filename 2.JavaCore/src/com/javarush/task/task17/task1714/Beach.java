package com.javarush.task.task17.task1714;

/* 
Comparable
*/

public class Beach implements Comparable<Beach> {
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public synchronized  String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    public synchronized static void main(String[] args) {
        Beach b1 = new Beach("1", 100, 100);
        Beach b2 = new Beach("2", 200, 100);
        System.out.println(b1.compareTo(b2));
    }

    @Override
    public synchronized int compareTo(Beach o) {
        int p1 = (quality > o.quality ? 1 : 0) + (distance < o.distance ? 1 : 0);
        int p2 = (o.quality > quality ? 1 : 0) + (o.distance < distance ? 1 : 0);
        if (p1 == p2)
            return 0;
        if (p1 > p2)
            return 1;
        else
            return -1;
    }
}
