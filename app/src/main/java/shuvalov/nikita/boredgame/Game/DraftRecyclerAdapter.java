package shuvalov.nikita.boredgame.Game;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.Cards.ResourceCard;
import shuvalov.nikita.boredgame.GameStateManager;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/7/17.
 */

public class DraftRecyclerAdapter extends RecyclerView.Adapter<DraftViewHolder> {
    private ArrayList<ResourceCard> mDraftCards;

    public DraftRecyclerAdapter(ArrayList<ResourceCard> draftCards) {
        mDraftCards = draftCards;
    }

    @Override
    public DraftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DraftViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_draft,null));
    }

    @Override
    public void onBindViewHolder(DraftViewHolder holder, final int position) {
        holder.bindDataToView(mDraftCards.get(position));
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This adds the picked card to the user's hand.
                ResourceCard pickedCard = DraftManager.getInstance().pickCard(0, position);
                mDraftCards = DraftManager.getInstance().getPack(0);
                GameStateManager.getInstance().getPlayer(0).addDraftedCard(pickedCard);
                //Add card to players stash


                //This simulates the computer's picked card to the computer's hand. This essentially just mirrors the user's pick.
//                DraftManager.getInstance().pickCard(1,position); //It just removes that card from the pack.
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDraftCards.size();
    }

    public void replaceDraftCards(ArrayList<ResourceCard> draftCards){
        mDraftCards = draftCards;
        notifyDataSetChanged();
    }
}
