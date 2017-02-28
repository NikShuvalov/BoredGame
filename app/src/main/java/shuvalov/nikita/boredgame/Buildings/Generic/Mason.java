package shuvalov.nikita.boredgame.Buildings.Generic;

import android.content.Context;
import android.widget.Toast;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/21/17.
 */

public class Mason extends Building {
    public static final int MASON_HP = 5;
    public static final int[] MASON_COST = {5,3,3,0,0};

    public Mason() {
        super(MASON_HP, false, 0, GameConstants.MASON_ID, 0);
    }


    @Override
    public String getName(Context context) {
        return context.getString(R.string.mason_name);
    }

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.mason_description);
    }

    @Override
    public boolean useAbility(BaseCharacterRace player, Context context) {
        if(getCounters()<(5-getLevel())){
            return false;
        }
        removeCounters(5-getLevel());
        player.addStone(1);
        Toast.makeText(context, "Added 1 stone", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public int[] getCost() {
        return MASON_COST;
    }
}
