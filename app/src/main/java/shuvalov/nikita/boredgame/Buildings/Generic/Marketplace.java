package shuvalov.nikita.boredgame.Buildings.Generic;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/27/17.
 */

public class Marketplace extends Building {
    public static final int MARKETPLACE_HP = 6;
    public static final int[] MARKETPLACE_COST = {4,0,4,0,0};

    public Marketplace() {
        super(MARKETPLACE_HP, false, 0, GameConstants.MARKETPLACE_ID, 0);
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.marketplace_name);
    }

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.marketplace_description);
    }

    @Override
    public boolean useAbility(BaseCharacterRace player, Context context) {
        if(getCounters()>= getLevel()){
            Toast.makeText(context, "No more usages left this turn", Toast.LENGTH_SHORT).show();
            return false;
        }
//        new AlertDialog.Builder(context).setTitle(R.string.marketplace_name).setV
        return true;
    }

    @Override
    public int[] getCost() {
        return MARKETPLACE_COST;
    }
}
