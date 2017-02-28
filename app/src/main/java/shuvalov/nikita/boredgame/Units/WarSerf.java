package shuvalov.nikita.boredgame.Units;

import shuvalov.nikita.boredgame.GameConstants;

/**
 * Created by NikitaShuvalov on 2/27/17.
 */

public class WarSerf extends Army {
    public static final int WARSERF_ATTACK = 2;
    public static final int WARSERF_DEFENSE = 3;
    public static final int WARSERF_HP = 5;
    public static final String WARSERF_NAME = "Serf Warrior";

    public WarSerf() {
        super(GameConstants.WARSERF_ID, WARSERF_ATTACK, WARSERF_DEFENSE, WARSERF_HP, WARSERF_NAME);
    }
}
