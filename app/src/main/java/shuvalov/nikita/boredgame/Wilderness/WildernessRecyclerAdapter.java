package shuvalov.nikita.boredgame.Wilderness;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shuvalov.nikita.boredgame.R;
import shuvalov.nikita.boredgame.Units.Mobs.Mob;

/**
 * Created by NikitaShuvalov on 2/28/17.
 */

public class WildernessRecyclerAdapter extends RecyclerView.Adapter<WildernessViewHolder> {
    private Mob[] mWildernessState;
    private int mSelectedPosition;
    private WildernessViewHolder mSelectedHolder;

    public WildernessRecyclerAdapter(Mob[] wildernessState) {
        mWildernessState = wildernessState;
        mSelectedPosition=-1;
    }

    @Override
    public WildernessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WildernessViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_wilderness, null));
    }

    @Override
    public void onBindViewHolder(final WildernessViewHolder holder, int position) {
        holder.bindDataToViews(mWildernessState[position]);
        holder.mMobCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSelectedPosition==holder.getAdapterPosition()){
                    holder.toggleColor();
                    mSelectedPosition= -1;
                    mSelectedHolder=null;
                }else if (mSelectedPosition==-1){
                    holder.toggleColor();
                    mSelectedPosition=holder.getAdapterPosition();
                    mSelectedHolder=holder;
                }else{
                    mSelectedHolder.toggleColor();
                    holder.toggleColor();
                    mSelectedHolder=holder;
                    mSelectedPosition= holder.getAdapterPosition();
                }
            }
        });

    }

    public Mob getSelectedUnit() {
        if(mSelectedPosition==-1){
            return mWildernessState[mSelectedPosition];
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mWildernessState.length;
    }
}
