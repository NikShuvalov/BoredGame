package shuvalov.nikita.boredgame.Cards;

import android.graphics.Color;
import android.os.Build;
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
    private boolean mSelected;


    public ActionViewHolder(View itemView) {
        super(itemView);

        mCardView = (CardView) itemView.findViewById(R.id.action_cardview);
        mNameText = (TextView)itemView.findViewById(R.id.name_text);
        mManaText = (TextView)itemView.findViewById(R.id.mana_text);
        mDescriptionText = (TextView)itemView.findViewById(R.id.description_text);
        mSelected = false;
    }

    public void bindDataToViews(ActionCard actionCard){
        mNameText.setText(actionCard.getName());
//        mManaText.setText(actionCard.getManaCost());
        mDescriptionText.setText(actionCard.getDescription());
    }

    public boolean toggleColor(){
        if(!mSelected){
            mCardView.setCardBackgroundColor(Color.argb(100,150,229,255));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mCardView.setElevation(10f);
            }
        }else{
            mCardView.setCardBackgroundColor(Color.argb(255,255,255,255));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mCardView.setElevation(2f);
            }
        }
        mSelected=!mSelected;
        return mSelected;
    }
}
