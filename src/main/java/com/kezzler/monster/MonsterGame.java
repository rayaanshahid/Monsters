package com.kezzler.monster;

import java.util.*;

public class MonsterGame {
    public static void main(String[] args) {


        List<Monster> monsters = new ArrayList<>();
        Random rand = new Random();

        // create 5 monsters
        monsters.add(new Monster(0, 5, true));
        monsters.add(new Monster(1, 5, true));
        monsters.add(new Monster(2, 5, true));
        monsters.add(new Monster(3, 5, true));
        monsters.add(new Monster(4, 5, true));

        MonsterGamePlay monsterGamePlay = new MonsterGamePlay(monsters);

        monsterGamePlay.createFoodPackets(100);

        do{
            List<Integer> monsterNumbers = new LinkedList<Integer>(Arrays.asList(0, 1, 2, 3, 4));
            int StealerIndex = rand.nextInt(5);

            monsterGamePlay.changeMonstersSleepState(true);

            monsterGamePlay.displayMonsters();

            monsterGamePlay.assignFoodPacketsToMonsters();

            for(int i = 0; i < monsterGamePlay.getMonsters().size(); i++){

                int randomMonsterIndex = monsterNumbers.remove(rand.nextInt(monsterNumbers.size())); //randomizing monsters that wake up

                monsterGamePlay.getMonsters().get(randomMonsterIndex).setAsleep(false); //a monster woke up

                if(monsterGamePlay.getMonsters().get(randomMonsterIndex).isAlive()){
                    //monster steals if either it is the random stealer this turn or if it has been stolen from
                    if(StealerIndex == randomMonsterIndex || monsterGamePlay.getMonsters().get(randomMonsterIndex).getFoodPackets().isEmpty()){
                        monsterGamePlay.stealFromAnotherMonster(randomMonsterIndex);
                    }
                    monsterGamePlay.calculateAndSetCalories(randomMonsterIndex);
                }
            }

            monsterGamePlay.removeUsedFoodPacketsFromMonsters();

        }while(monsterGamePlay.getNumberOfAliveMonsters() > 1); //next round if more than one monsters remain

        monsterGamePlay.displayMonsters();
    }
}