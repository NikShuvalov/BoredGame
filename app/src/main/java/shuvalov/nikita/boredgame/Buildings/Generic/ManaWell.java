package shuvalov.nikita.boredgame.Buildings.Generic;

import android.content.Context;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/21/17.
 */

public class ManaWell extends Building{
    public static final int MANAWELL_HP = 5;
    public static final int[] MANAWELL_COST = {0,0,4,4,3};

    public ManaWell() {
        super(MANAWELL_HP, false, 0, GameConstants.MANA_WELL_ID, 0);
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.mana_well_name);
    }

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.mana_well_description);
    }

    @Override
    public boolean useAbility(BaseCharacterRace player) {
        if(getCounters()<(5-getLevel())){
            return false;
        }
        removeCounters(5-getLevel());
        return true;
    }

    @Override
    public int[] getCost() {
        return MANAWELL_COST;
    }
}
