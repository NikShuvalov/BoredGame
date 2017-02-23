package shuvalov.nikita.boredgame.Buildings.Generic;

import android.content.Context;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/21/17.
 */

public class Jeweler extends Building {
    public static final int JEWELER_HP = 5;
    public static final int[] JEWELER_COST = {5,0,2,2,2};

    public Jeweler() {
        super(JEWELER_HP,false, 0, GameConstants.JEWELER_ID,0);
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.jeweler_name);
    }

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.jeweler_description);
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
        return JEWELER_COST;
    }
}
