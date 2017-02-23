package shuvalov.nikita.boredgame.Buildings.Generic;

import android.content.Context;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.Game.GameUtils;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/23/17.
 */

public class SummonersGuild extends Building {
    public static final int SUMM_GUILD_HP = 5;
    public static final int[] WOOD_GOLEM_SUMMON_COST = {1,0,0,2,3}; //1 wood, 2 gold, 3 mana
    public static final int[] SUMM_GUILD_COST = {3,3,3,3,0};

    public SummonersGuild() {
        super(SUMM_GUILD_HP, false, 0, GameConstants.SUMM_GUILD_ID, 0);
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.summoners_guild_name);
    }

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.summoners_guild_description);
    }

    @Override
    public boolean useAbility(BaseCharacterRace player) {
        if(GameUtils.canPayCost(player,WOOD_GOLEM_SUMMON_COST)){
            return true;
        }
        return false;
    }

    @Override
    public int[] getCost() {
        return SUMM_GUILD_COST;
    }

}
