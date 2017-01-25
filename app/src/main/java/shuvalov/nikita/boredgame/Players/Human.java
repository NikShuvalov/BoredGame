package shuvalov.nikita.boredgame.Players;

import android.content.Context;

import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 1/25/17.
 */

public class Human extends BaseCharacterRace {

    //Adjust starting values accordingly.
    public static final int GOLD_START_VALUE =  2;
    public static final int WOOD_START_VALUE =  2;
    public static final int STONE_START_VALUE =  2;
    public static final int IRON_START_VALUE =  2;
    public static final int MANA_START_VALUE =  0;



    public Human(String name, int id) {
        super(name, id, GOLD_START_VALUE, WOOD_START_VALUE, STONE_START_VALUE, IRON_START_VALUE, MANA_START_VALUE);
    }

    @Override
    public String getCharacterRace(Context context) {
        return context.getResources().getString(R.string.human_class);
    }

    @Override
    public String getRaceDescription(Context context) {
        return context.getResources().getString(R.string.human_descript);
    }

}
