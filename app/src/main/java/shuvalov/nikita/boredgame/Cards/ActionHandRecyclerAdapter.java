package shuvalov.nikita.boredgame.Cards;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/22/17.
 */

public class ActionHandRecyclerAdapter extends RecyclerView.Adapter<ActionViewHolder> {
    private ArrayList<ActionCard> mActionHand;
    private ArrayList<ActionCard> mSelectedCards;

    public ActionHandRecyclerAdapter(ArrayList<ActionCard> actionHand) {
        mActionHand = actionHand;
        mSelectedCards = new ArrayList<>();
    }

    @Override
    public ActionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_action_cards,null));
    }

    @Override
    public void onBindViewHolder(final ActionViewHolder holder, final int position) {
        holder.bindDataToViews(mActionHand.get(position));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.toggleColor()){
                    mSelectedCards.add(mActionHand.get(position));
                    Log.d("Selected", "Selected amount: "+mSelectedCards.size());
                }else{
                    mSelectedCards.remove(mActionHand.get(position));
                    Log.d("Unselected", "Selected amount: "+mSelectedCards.size());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mActionHand.size();
    }

    public ArrayList<ActionCard> getSelectedCards() {
        return mSelectedCards;
    }
}
