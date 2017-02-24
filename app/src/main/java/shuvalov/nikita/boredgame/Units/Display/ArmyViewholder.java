package shuvalov.nikita.boredgame.Units.Display;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.R;
import shuvalov.nikita.boredgame.Units.Army;

/**
 * Created by NikitaShuvalov on 2/23/17.
 */

public class ArmyViewholder extends RecyclerView.ViewHolder {
    private TextView mNameText, mHpText, mAttackText, mDefenseText, mArmorText, mWeaponText;
    public CardView mUnitCard;
    private boolean mSelected;

    public ArmyViewholder(View itemView) {
        super(itemView);
        mNameText = (TextView)itemView.findViewById(R.id.unit_name);
        mHpText = (TextView)itemView.findViewById(R.id.hp_text);
        mAttackText = (TextView)itemView.findViewById(R.id.atk_text);
        mDefenseText = (TextView)itemView.findViewById(R.id.def_text);
        mArmorText = (TextView)itemView.findViewById(R.id.armor_text);
        mWeaponText = (TextView)itemView.findViewById(R.id.weapon_text);
        mUnitCard = (CardView)itemView.findViewById(R.id.unit_card);
        mSelected=false;
    }

    public void bindDataToViews(Army unit){
        mNameText.setText(unit.getName());
        String hpString = "HP: " + unit.getHp()+"/"+unit.getMaxHp();
        String attackString = "Atk: "+ unit.getAttack();
        String defenseString = "Def: "+ unit.getDefense();
        String weaponString= "Weapon: No";
        String armorString = "Armor: NO!";
        mHpText.setText(hpString);
        mAttackText.setText(attackString);
        mDefenseText.setText(defenseString);
        mArmorText.setText(weaponString);
        mWeaponText.setText(armorString);
    }

    public boolean toggleColor(){
        if(!mSelected){
            mUnitCard.setCardBackgroundColor(Color.argb(100,150,229,255));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mUnitCard.setElevation(10f);
            }
        }else{
            mUnitCard.setCardBackgroundColor(Color.argb(255,255,255,255));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mUnitCard.setElevation(2f);
            }
        }
        mSelected=!mSelected;
        return mSelected;
    }
}
