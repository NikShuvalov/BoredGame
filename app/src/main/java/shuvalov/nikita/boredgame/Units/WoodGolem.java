package shuvalov.nikita.boredgame.Units;

import shuvalov.nikita.boredgame.GameConstants;

/**
 * Created by NikitaShuvalov on 2/23/17.
 */


//These guys should not be able to use armor or weapons.

public class WoodGolem extends Army {
    public static final int WG_ATTACK= 2;
    public static final int WG_DEFENSE = 5;
    public static final int WG_HP = 10;
    public static final String WG_NAME= "Wood Golem";

    public WoodGolem() {
        super(GameConstants.WOOD_GOLEM_ID, WG_ATTACK, WG_DEFENSE, WG_HP, WG_NAME);
    }
}
