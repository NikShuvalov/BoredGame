package shuvalov.nikita.boredgame.Players;

import android.content.Context;

import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 1/25/17.
 */

public class Human extends BaseCharacterRace {


    public Human(String name, int id, int gold, int wood, int stone, int iron, int mana) {
        super(name, id, 2, 2, 2, 2, 0); //Adjust starting values accordingly.
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
