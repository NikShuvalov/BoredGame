package shuvalov.nikita.boredgame.Units.Mobs;

import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.Units.Army;

/**
 * Created by NikitaShuvalov on 2/28/17.
 */

public class Yeti extends Mob {
    public static final int YETI_ATTACK = 4;
    public static final int YETI_DEFENSE = 4;
    public static final int YETI_MOB_HP = 12;
    public static final int YETI_ALLY_HP=9;
    public static final String YETI_NAME = "Abominable Snowman";


    public Yeti(boolean isAlly) {
        super(GameConstants.YETI_MOB_ID, YETI_ATTACK, YETI_DEFENSE, YETI_MOB_HP, YETI_NAME);
        if(isAlly){
            setHp(YETI_ALLY_HP);
            setMaxHp(YETI_ALLY_HP);
        }
    }

    @Override
    public void onDefeated(BaseCharacterRace player) {
        player.addUnitToArmy(new Yeti(true));
    }
}
