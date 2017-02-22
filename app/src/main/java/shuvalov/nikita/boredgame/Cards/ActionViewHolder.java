package shuvalov.nikita.boredgame.Cards;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/22/17.
 */

public class ActionViewHolder extends RecyclerView.ViewHolder {
    public CardView mCardView;
    private TextView mNameText, mManaText, mDescriptionText;


    public ActionViewHolder(View itemView) {
        super(itemView);

        mCardView = (CardView) itemView.findViewById(R.id.action_cardview);
        mNameText = (TextView)itemView.findViewById(R.id.name_text);
        mManaText = (TextView)itemView.findViewById(R.id.mana_text);
        mDescriptionText = (TextView)itemView.findViewById(R.id.description_text);
    }

    public void bindDataToViews(ActionCard actionCard){
        mNameText.setText(actionCard.getName());
//        mManaText.setText(actionCard.getManaCost());
        mDescriptionText.setText(actionCard.getDescription());
    }
}
