package shuvalov.nikita.boredgame.Wilderness;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import shuvalov.nikita.boredgame.R;
import shuvalov.nikita.boredgame.Units.Mobs.Mob;

/**
 * Created by NikitaShuvalov on 2/28/17.
 */

public class WildernessViewHolder extends RecyclerView.ViewHolder {
    public CardView mMobCard;
    private TextView mHpText, mNameText, mDescriptionText, mAttackText, mDefenseText;
    private boolean mSelected;

    public WildernessViewHolder(View itemView) {
        super(itemView);
        mHpText = (TextView)itemView.findViewById(R.id.hp_text);
        mNameText = (TextView)itemView.findViewById(R.id.name_text);
        mDescriptionText = (TextView)itemView.findViewById(R.id.description_text);
        mAttackText = (TextView)itemView.findViewById(R.id.attack_text);
        mDefenseText =(TextView)itemView.findViewById(R.id.defense_text);
        mMobCard = (CardView)itemView.findViewById(R.id.mob_card);
    }

    public void bindDataToViews(Mob unit){
        if (unit != null) {
            mNameText.setText(unit.getName());
            mDescriptionText.setText(unit.getDescriptionText(mDescriptionText.getContext()));
            String attackString = "Attack: " + unit.getAttack();
            String defenseString = "Defense: " + unit.getDefense();
            String hpString = unit.getHp()+"/"+unit.getMaxHp();
            mAttackText.setText(attackString);
            mDefenseText.setText(defenseString);
            mHpText.setText(hpString);
        }else{
            String filler= "-";
            mNameText.setText(filler);
            mDescriptionText.setText(filler);
            mAttackText.setText(filler);
            mDefenseText.setText(filler);
            mHpText.setText(filler);
        }

    }
    public boolean toggleColor(){
        if(!mSelected){
            mMobCard.setCardBackgroundColor(Color.argb(100,150,229,255));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mMobCard.setElevation(10f);
            }
        }else{
            mMobCard.setCardBackgroundColor(Color.argb(255,255,255,255));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mMobCard.setElevation(2f);
            }
        }
        mSelected=!mSelected;
        return mSelected;
    }
}
