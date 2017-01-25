package shuvalov.nikita.boredgame;

/**
 * Created by NikitaShuvalov on 1/25/17.
 */

import org.junit.Test;

import shuvalov.nikita.boredgame.Buildings.Generic.LumberMill;

import static org.junit.Assert.*;

public class BuildingUnitTest {

    @Test
    public void LumberMill_Test(){
        LumberMill testLumberMill = new LumberMill();

        //Test default parameters
        assertEquals(false, testLumberMill.getExclusive());
        assertEquals(LumberMill.LUMBER_MILL_HP, testLumberMill.getHp());

        //Test takesDamage
        assertEquals(LumberMill.LUMBER_MILL_HP-5, testLumberMill.takesDamage(5));
        
    }

}
