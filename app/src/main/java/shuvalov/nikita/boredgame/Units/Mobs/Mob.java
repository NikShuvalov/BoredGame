package shuvalov.nikita.boredgame.Units.Mobs;

import android.content.Context;

import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.Units.Army;

/**
 * Created by NikitaShuvalov on 2/28/17.
 */


/**
 * Units in the wilderness will be cast to Mob to allow them to use wilderness abilities and have onDefeated triggers.
 * If you want to add a unit that's a mob to player's army, cast it to Army instead.
 */

public abstract class Mob extends Army {

    public Mob(int idType, int attack, int defense, int hp, String name) {
        super(idType, attack, defense, hp, name);
    }

    public abstract void onDefeated(BaseCharacterRace player);//ToDo: Probably going to have to pass player to this function.
    public abstract String getDescriptionText(Context context);
}
