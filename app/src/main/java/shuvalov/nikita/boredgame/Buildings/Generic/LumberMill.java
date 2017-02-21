package shuvalov.nikita.boredgame.Buildings.Generic;

import android.content.Context;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.GameStateManager;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 1/25/17.
 */

public class LumberMill extends Building {
    public static final int LUMBER_MILL_HP = 5;
    public static final int[] LUMBER_MILL_COST = {2,5,2,2,0};


    public LumberMill() {
        super(LUMBER_MILL_HP, false, 0, GameConstants.LUMBER_MILL_ID, 0);
    }

    @Override
    public String getName(Context context) {
        return context.getResources().getString(R.string.lumber_mill_name);
    }

    @Override
    public String getDescription(Context context) {
        return context.getResources().getString(R.string.lumber_mill_description);
    }

    //This is to be adjusted depending on how I'm using this building.
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
        return LUMBER_MILL_COST;
    }
}
