package shuvalov.nikita.boredgame.Cards;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/22/17.
 */

public class ActionHandRecyclerAdapter extends RecyclerView.Adapter<ActionViewHolder> {
    private ArrayList<ActionCard> mActionHand;

    public ActionHandRecyclerAdapter(ArrayList<ActionCard> actionHand) {
        mActionHand = actionHand;
    }

    @Override
    public ActionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_action_cards,null));
    }

    @Override
    public void onBindViewHolder(ActionViewHolder holder, int position) {
        holder.bindDataToViews(mActionHand.get(position));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToDo: OnClick logic
            }
        });

    }

    @Override
    public int getItemCount() {
        return mActionHand.size();
    }
}
