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
    public boolean useAbility(BaseCharacterRace player, Context context) {
        if(getCounters()<(5-getLevel())){
            return false;
        }
        player.addIron(1);
        Toast.makeText(context, "Added 1 iron", Toast.LENGTH_SHORT).show();
        removeCounters(5-getLevel());
        return true;
    }

    @Override
    public int[] getCost() {
        return SMELTERY_COST;
    }
}
