package shuvalov.nikita.boredgame.Players;

import android.content.Context;

/**
 * Created by NikitaShuvalov on 1/25/17.
 */

public abstract class BaseCharacterRace {
    private String mName;
    private int mId;
    private int mGold, mWood, mStone, mIron, mMana;

    public BaseCharacterRace(String name, int id, int gold, int wood, int stone, int iron, int mana) {
        mName = name;
        mId = id;
        mGold = gold;
        mWood = wood;
        mStone = stone;
        mIron = iron;
        mMana = mana;
    }

    public String getName() {
        return mName;
    }

    public int getId() {
        return mId;
    }

    public int getGold() {
        return mGold;
    }

    public int getWood() {
        return mWood;
    }

    public int getStone() {
        return mStone;
    }

    public int getIron() {
        return mIron;
    }

    public int getMana() {
        return mMana;
    }

    public boolean spendGold(int goldSpent){
        if(mGold<goldSpent){
            return false;
        }
        mGold-=goldSpent;
        return true;
    }

    public boolean spendWood(int woodSpent){
        if(mWood<woodSpent){
            return false;
        }
        mWood-=woodSpent;
        return true;
    }

    public boolean spendStone(int stoneSpent){
        if(mStone<stoneSpent){
            return false;
        }
        mStone-=stoneSpent;
        return true;
    }

    public boolean spendIron(int ironSpent){
        if(mIron<ironSpent){
            return false;
        }
        mIron-=ironSpent;
        return true;
    }

    public boolean spendMana(int spentMana){
        if(mMana<spentMana){
            return false;
        }
        mMana-=spentMana;
        return true;
    }

    public int addGold(int goldGained){
        mGold+=goldGained;
        return mGold;
    }

    public int addWood(int woodGained){
        mWood+=woodGained;
        return mWood;
    }

    public int addStone (int stoneGained){
        mStone+=stoneGained;
        return mStone;
    }

    public int addIron(int ironGained){
        mIron+=ironGained;
        return mIron;
    }

    public int addMana(int manaGained){
        mMana+=manaGained;
        return mMana;
    }

    public abstract String getCharacterRace(Context context);
    public abstract String getRaceDescription(Context context);
}
