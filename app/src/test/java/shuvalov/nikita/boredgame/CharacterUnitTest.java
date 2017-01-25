package shuvalov.nikita.boredgame;

import org.junit.Test;

import shuvalov.nikita.boredgame.Players.Human;
import static org.junit.Assert.*;


/**
 * Created by NikitaShuvalov on 1/25/17.
 */

public class CharacterUnitTest {
    @Test
    public void resource_manipulation_iscorrect() throws Exception{
        String name = "Nikita";
        int id = 0;


        Human testHuman = new Human(name, id);

        assertEquals(name, testHuman.getName());//Test naming is correct
        assertEquals(id, testHuman.getId());//Test id is correct;

        //Test default values are correct
        assertEquals(Human.GOLD_START_VALUE,testHuman.getGold());
        assertEquals(Human.IRON_START_VALUE, testHuman.getIron());
        assertEquals(Human.WOOD_START_VALUE,testHuman.getWood());
        assertEquals(Human.STONE_START_VALUE, testHuman.getStone());
        assertEquals(Human.MANA_START_VALUE, testHuman.getMana());

        //Test adding values works
        testHuman.addGold(5);
        assertEquals(Human.GOLD_START_VALUE+5,testHuman.getGold());

        //Test removing values works
        assertEquals(false, testHuman.spendIron(3));//If not enough resources, should return false
        assertEquals(true, testHuman.spendGold(5));//Returns true if enough resources
        assertEquals(2, testHuman.getGold());//Value should be back to 2 after spending

    }
}
