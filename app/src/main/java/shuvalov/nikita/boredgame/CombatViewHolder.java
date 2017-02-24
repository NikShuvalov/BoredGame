package shuvalov.nikita.boredgame;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import shuvalov.nikita.boredgame.Units.Army;

/**
 * Created by NikitaShuvalov on 2/23/17.
 */

public class CombatViewHolder extends RecyclerView.ViewHolder {
    public CardView mSlotCard;
    private TextView mName, mHealth, mAttack, mDefense;

    public CombatViewHolder(View itemView) {
        super(itemView);
        mSlotCard = (CardView)itemView.findViewById(R.id.slot_card);
        mName = (TextView)itemView.findViewById(R.id.name);
        mHealth = (TextView)itemView.findViewById(R.id.health);
        mAttack = (TextView)itemView.findViewById(R.id.attack);
        mDefense = (TextView)itemView.findViewById(R.id.defense);
    }

    public void bindDataToViews(Army unit){
        if(unit!=null){
//            mName.setVisibility(View.VISIBLE);
//            mHealth.setVisibility(View.VISIBLE);
//            mAttack.setVisibility(View.VISIBLE);
//            mDefense.setVisibility(View.VISIBLE);

            mName.setText(unit.getName());
            String healthString = "Hp: "+unit.getHp()+"/"+unit.getMaxHp();
            String attackString = "Atk: "+unit.getAttack();
            String defenseString = "Def: "+unit.getDefense();

            mHealth.setText(healthString);
            mAttack.setText(attackString);
            mDefense.setText(defenseString);
        }else{
            mName.setText("-");
            mHealth.setText("-");
            mAttack.setText("-");
            mDefense.setText("-");

        }

    }
}
