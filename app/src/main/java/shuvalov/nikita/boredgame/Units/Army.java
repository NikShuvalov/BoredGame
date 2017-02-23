package shuvalov.nikita.boredgame.Units;

/**
 * Created by NikitaShuvalov on 2/23/17.
 */

public abstract class Army {
    private int mHp, mMaxHp, mDefense, mAttack, mIdType;
    private boolean mHasArmor, mHasWeapon; //ToDo: Should probably have different types of weapons, but keeping it simple for now.
    private String mName; //ToDo: Change to a more appropriately named string. This represents the type of unit.
    //ToDo: Add isRanged?
    //ToDo: Add names for weapons?


    public Army(int idType, int attack, int defense, int hp, String name) {
        mIdType = idType;
        mAttack = attack;
        mDefense = defense;
        mMaxHp = mHp = hp;
        mName = name;
        mHasArmor=false;
        mHasWeapon=false;
    }

    //<start getters and Setters>
    public void setHp(int hp) {
        mHp = hp;
    }

    public void setDefense(int defense) {
        mDefense = defense;
    }

    public void setAttack(int attack) {
        mAttack = attack;
    }

    public void setIdType(int idType) {
        mIdType = idType;
    }

    public void setHasArmor(boolean hasArmor) {
        mHasArmor = hasArmor;
    }

    public void setHasWeapon(boolean hasWeapon) {
        mHasWeapon = hasWeapon;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getHp() {
        return mHp;
    }

    public int getDefense() {
        return mDefense;
    }

    public int getAttack() {
        return mAttack;
    }

    public int getIdType() {
        return mIdType;
    }

    public boolean isHasArmor() {
        return mHasArmor;
    }

    public boolean isHasWeapon() {
        return mHasWeapon;
    }

    public String getName() {
        return mName;
    }

    public int getMaxHp() {
        return mMaxHp;
    }

    public void setMaxHp(int maxHp) {
        mMaxHp = maxHp;
    }
    //</End Getters and setters>

    //ToDo: Damage and kill check


}
