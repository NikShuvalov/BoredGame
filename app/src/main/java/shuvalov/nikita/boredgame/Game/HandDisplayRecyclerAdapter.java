package shuvalov.nikita.boredgame.Game;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.Cards.ResourceCard;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/20/17.
 */

public class HandDisplayRecyclerAdapter extends RecyclerView.Adapter<DraftViewHolder> {
    public ArrayList<ResourceCard> mDraftedCards;

    public HandDisplayRecyclerAdapter(ArrayList<ResourceCard> draftedCards){
        mDraftedCards = draftedCards;
    }

    @Override
    public DraftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DraftViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_draft,null));
    }

    @Override
    public void onBindViewHolder(DraftViewHolder holder, int position) {
        holder.bindDataToView(mDraftedCards.get(position));
    }

    @Override
    public int getItemCount() {
        return mDraftedCards.size();
    }
}
