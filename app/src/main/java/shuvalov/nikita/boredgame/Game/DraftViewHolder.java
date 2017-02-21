package shuvalov.nikita.boredgame.Game;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import shuvalov.nikita.boredgame.Cards.ResourceCard;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/16/17.
 */

public class DraftViewHolder extends RecyclerView.ViewHolder {
    private TextView mResourceText, mRankText;
    public CardView mContainer;

    public DraftViewHolder(View itemView) {
        super(itemView);
        mResourceText = (TextView)itemView.findViewById(R.id.resource_text);
        mRankText = (TextView)itemView.findViewById(R.id.value_text);
        mContainer = (CardView)itemView.findViewById(R.id.draft_card_container);
    }

    public void bindDataToView (ResourceCard resourceCard){
        mResourceText.setText(resourceCard.getResource());

        switch(resourceCard.getRank()){
            case ResourceCard.POOR:
                mContainer.setBackgroundColor(Color.RED); //ToDo: Set a bronze-ish border, change background color based on resource?
                mRankText.setText("Poor (x1)");
                break;
            case ResourceCard.RICH:
                mContainer.setBackgroundColor(Color.GRAY);//ToDo: Set a Silver-ish border, change background color based on resource?
                mRankText.setText("Rich (x2)");
                break;
            case ResourceCard.WEALTHY:
                mContainer.setBackgroundColor(Color.YELLOW);//ToDo: Set a Gold-ish border, change background color based on resource?
                mRankText.setText("Wealthy (x3)");
                break;
        }

    }
}
