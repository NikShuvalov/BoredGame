package shuvalov.nikita.boredgame.Buildings.Generic;

import android.content.Context;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/21/17.
 */

public class Smeltery extends Building {
    public static final int SMELTERY_HP = 5;
    public static final int[] SMELTERY_COST = {2,2,5,2,0};

    public Smeltery() {
        super(SMELTERY_HP, false, 0, GameConstants.SMELTERY_ID, 0);
    }


    @Override
    public String getName(Context context) {
        return context.getString(R.string.smeltery_name);
    }

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.smeltery_description);
    }

    @Override
    public boolean useAbility() {
        if(getCounters()<(5-getLevel())){
            return false;
        }
        removeCounters(5-getLevel());
        return true;
    }

    @Override
    public int[] getCost() {
        return SMELTERY_COST;
    }
}
