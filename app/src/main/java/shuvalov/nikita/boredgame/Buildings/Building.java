package shuvalov.nikita.boredgame.Buildings;

import android.content.Context;

import shuvalov.nikita.boredgame.Players.BaseCharacterRace;

/**
 * Created by NikitaShuvalov on 1/25/17.
 */

public abstract class Building {
    private int mHp, mLevel, mId, mCounters;
    private boolean mExclusive;
    public static final String TYPE_GENERATOR = "Generator_Build";
    public static final String TYPE_MILITARY = "Military_Build";
    public static final String TYPE_SPECIALTY = "Specialty_Build";
    public static final String TYPE_OTHER = "Other_Build";

    public Building(int hp, boolean exclusive, int level, int id, int counters){
        mHp = hp;
        mExclusive = exclusive;
        mLevel = level;
        mId = id;
        mCounters = counters;
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
    public abstract boolean useAbility(BaseCharacterRace player, Context context);
    public abstract int[] getCost();


    public int takesDamage(int damage){
        mHp-= damage;
        return mHp;
    }

    public int getLevel(){
        return mLevel;
    }

    public int levelUp(){
        return mLevel++;
    }

    public int getId(){
        return mId;
    }

    public int getCounters(){
        return mCounters;
    }

    public int addCounters(int counters){
        mCounters+=counters;
        return mCounters;
    }

    public int removeCounters(int counters){
        mCounters-= counters;
        return mCounters;
    }

    public int addCounter(){
        return mCounters++;
    }
}
