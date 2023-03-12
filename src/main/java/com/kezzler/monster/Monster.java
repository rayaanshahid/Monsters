package com.kezzler.monster;

import java.util.ArrayList;
import java.util.List;

public class Monster {
    private int number;
    private int calories;
    private List<FoodPacket> foodPackets = new ArrayList<>();
    private boolean asleep;
    private boolean alive;

    public Monster(int number,int calories, boolean alive) {
        this.number = number;
        this.calories = calories;
        this.alive = alive;
    }

    public int getNumber() {
        return number;
    }
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAsleep() {
        return asleep;
    }

    public void setAsleep(boolean asleep) {
        this.asleep = asleep;
    }

    public int getCalories() {
        return calories;
    }

    public void addCalories(int calories) {
        this.calories += calories;
    }

    public void burnCalories(int calories) {
        this.calories -= calories;
    }

    public List<FoodPacket> getFoodPackets() {
        return foodPackets;
    }

    public void addFoodPacket(FoodPacket foodPacket) {
        this.foodPackets.add(foodPacket);
    }
}
