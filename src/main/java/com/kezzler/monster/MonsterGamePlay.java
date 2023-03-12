package com.kezzler.monster;

import java.util.*;

public class MonsterGamePlay {
    private List<Monster> monsters;
    private List<FoodPacket> foodPackets;

    public List<FoodPacket> getFoodPackets() {
        return foodPackets;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }
    public MonsterGamePlay(List<Monster> monsters) {
        this.monsters = monsters;
        foodPackets = new ArrayList<>();
    }

    public int getNumberOfAliveMonsters(){
        //check alive count
        int aliveMonsterCount = 0;
        for(Monster monster : monsters){
            if (monster.getCalories() > 0) {
                aliveMonsterCount += 1;
            }else{
                monster.setAlive(false);
                //System.out.println("Monster " + monster.getNumber()+ " RIP!");
            }
        }
        return aliveMonsterCount;
    }

    public void createFoodPackets(int amount){
        for(int i=1; i<=amount; i++){
            if (i % 5 == 0) { // statistically 20 percent if 100 total food packets
                foodPackets.add(new FoodPacket(generateRandomValue(3), true));
            }else {
                foodPackets.add(new FoodPacket(generateRandomValue(3), false));
            }
        }
        Collections.shuffle(foodPackets);
    }

    public void assignFoodPacketsToMonsters(){
        for(Monster monster: monsters){
            monster.addFoodPacket(foodPackets.remove(0));
        }
    }

    public void removeUsedFoodPacketsFromMonsters(){
        for(Monster monster: monsters){
            monster.getFoodPackets().clear();
        }
    }

    public void stealFromAnotherMonster(int stealerIndex){
        List<Integer> monsterIndexes = new LinkedList<Integer>(Arrays.asList(0, 1, 2, 3, 4));
        monsterIndexes.remove(stealerIndex);

        while(!monsterIndexes.isEmpty()){
            int stealFromIndex = monsterIndexes.remove(generateRandomValue(monsterIndexes.size()));
            if(monsters.get(stealFromIndex).isAsleep()){
                monsters.get(stealerIndex).getFoodPackets().add(monsters.get(stealFromIndex).getFoodPackets().remove(0));
                System.out.println("Monster " + stealerIndex + " stole food from monster " + stealFromIndex);
                break;
            }
        }
    }

    public void calculateAndSetCalories(int monsterIndex){
        int totalCalories = 0;
        boolean poisened = false;
        for(FoodPacket foodPacket: monsters.get(monsterIndex).getFoodPackets()){
            totalCalories += foodPacket.getCalories();
            if(foodPacket.isPoisoned()){
                poisened = true;
            }
        }
        if(poisened){
            monsters.get(monsterIndex).burnCalories(totalCalories);
            monsters.get(monsterIndex).burnCalories(1); //burn one calorie each round
            System.out.println( "Oh no, monster " + monsterIndex + " was poisoned: " + monsters.get(monsterIndex).getCalories() + " calories left  ");
        }else{
            monsters.get(monsterIndex).burnCalories(1); //burn one calorie each round
            monsters.get(monsterIndex).addCalories(totalCalories);
        }
    }

    public int generateRandomValue(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public void changeMonstersSleepState(boolean asleep){
        for(Monster monster : monsters) {
            if (monster.isAlive())
                monster.setAsleep(asleep);
        }
    }

    public void displayMonsters(){
        for(Monster monster : monsters) {
            if (monster.isAlive())
                System.out.print( "monster " + monster.getNumber() + "(" + monster.getCalories() + ") ");
            else
                System.out.print( "monster " + monster.getNumber() + "(X) ");
        }
        System.out.print( "\n");
    }
}
