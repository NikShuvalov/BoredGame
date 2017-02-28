package shuvalov.nikita.boredgame.Buildings.Generic;

import android.content.Context;
import android.widget.Toast;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.Game.GameUtils;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.R;
import shuvalov.nikita.boredgame.Units.Mercenary;

/**
 * Created by NikitaShuvalov on 2/23/17.
 */

public class MercGuild extends Building {
    public static final int MERCGUILD_HP = 5;
    public static final int[] MERC_HIRE_COST = {0,1,0,4,0}; //1 Iron, 4 gold
    public static final int[] MERCGUILD_COST = {3,3,3,3,0};


    public MercGuild() {
        super(MERCGUILD_HP, false, 0, GameConstants.MERCGUILD_ID, 0);
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.mercenary_guild_name);
    }

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.mercenary_guild_description);
    }

    @Override
    public boolean useAbility(BaseCharacterRace player, Context context) {
        if(GameUtils.canPayCost(player,MERC_HIRE_COST)){
            player.addUnitToArmy(new Mercenary());
            Toast.makeText(context, "Hired a mercenary", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public int[] getCost() {
        return MERCGUILD_COST;
    }
}
