package shuvalov.nikita.boredgame.Buildings;

import android.content.Context;

/**
 * Created by NikitaShuvalov on 1/25/17.
 */

public abstract class Building {
    private int mHp;
    private boolean mExclusive;
    public static final String TYPE_GENERATOR = "Generator_Build";
    public static final String TYPE_MILITARY = "Military_Build";
    public static final String TYPE_SPECIALTY = "Specialty_Build";
    public static final String TYPE_OTHER = "Other_Build";

    public Building(int hp, boolean exclusive){
        mHp = hp;
        mExclusive = exclusive;
    }

    public int getHp(){
        return mHp;
    }

    public void setHp(int hp) {
        mHp = hp;
    }

    public boolean getExclusive(){
        return mExclusive;
    }

    public abstract String getName(Context context);
    public abstract String getDescription(Context context);

    public int takesDamage(int damage){
        mHp-= damage;
        return mHp;
    }
}
