import static org.junit.Assert.assertEquals;

import com.kezzler.monster.Monster;
import com.kezzler.monster.MonsterGamePlay;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class Tests {
    @Test
    public void testGetNumberOfAliveMonsters(){
        List<Monster> monsters = new ArrayList<>();

        // create 5 monsters
        monsters.add(new Monster(0, 5, true));
        monsters.add(new Monster(1, 0, false));
        monsters.add(new Monster(2, 5, true));
        monsters.add(new Monster(3, 5, true));
        monsters.add(new Monster(4, 0, false));

        MonsterGamePlay monsterGamePlay = new MonsterGamePlay(monsters);

        assertEquals(3,monsterGamePlay.getNumberOfAliveMonsters());
    }

    @Test
    public void testCalculateAndSetCalories(){
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(0, 5, true));

        MonsterGamePlay monsterGamePlay = new MonsterGamePlay(monsters);
        monsterGamePlay.calculateAndSetCalories(0);

        assertEquals(4,monsterGamePlay.getMonsters().get(0).getCalories());
    }

    @Test
    public void testStealMonsters(){
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(0, 5, true));
        monsters.add(new Monster(1, 5, true));

        MonsterGamePlay monsterGamePlay = new MonsterGamePlay(monsters);

        monsterGamePlay.changeMonstersSleepState(true);

        monsterGamePlay.createFoodPackets(100);

        monsterGamePlay.assignFoodPacketsToMonsters();

        monsterGamePlay.stealFromAnotherMonster(0);

        assertEquals(2,monsterGamePlay.getMonsters().get(0).getFoodPackets().size());
        assertEquals(0,monsterGamePlay.getMonsters().get(1).getFoodPackets().size());
    }
}
