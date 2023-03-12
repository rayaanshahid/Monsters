package com.kezzler.monster;

public class FoodPacket {
    private int calories;
    private boolean poisoned;

    public FoodPacket(int calories, boolean poisoned) {
        this.calories = calories;
        this.poisoned = poisoned;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }
}
