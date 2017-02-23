package shuvalov.nikita.boredgame.Units;

import shuvalov.nikita.boredgame.GameConstants;

/**
 * Created by NikitaShuvalov on 2/23/17.
 */

public class Mercenary extends Army {
    public static final int MERC_ATTACK = 5;
    public static final int MERC_DEFENSE = 3;
    public static final int MERC_HP = 7;
    public static final String MERC_NAME = "Mercenary";

    public Mercenary() {
        super(GameConstants.MERCENARY_ID, MERC_ATTACK, MERC_DEFENSE, MERC_HP, MERC_NAME);
    }
}
