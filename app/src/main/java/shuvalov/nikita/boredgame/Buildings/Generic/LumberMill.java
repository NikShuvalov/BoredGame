package shuvalov.nikita.boredgame.Buildings.Generic;

import android.content.Context;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 1/25/17.
 */

public class LumberMill extends Building {
    private static final int LUMBER_MILL_HP = 5;


    public LumberMill() {
        super(LUMBER_MILL_HP, false);
    }

    @Override
    public String getName(Context context) {
        return context.getResources().getString(R.string.lumber_mill_name);
    }

    @Override
    public String getDescription(Context context) {
        return context.getResources().getString(R.string.lumber_mill_description);
    }
}
