package shuvalov.nikita.boredgame;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import shuvalov.nikita.boredgame.Units.Army;

/**
 * Created by NikitaShuvalov on 2/23/17.
 */

public class CombatRecyclerAdapter extends RecyclerView.Adapter<CombatViewHolder>  {
    Army[] mCombatants;
    OnSelectedSlotListener mSelectionListener;

    public CombatRecyclerAdapter(Army[] combatants, OnSelectedSlotListener selectionListener) {
        mCombatants = combatants;
        mSelectionListener = selectionListener;
    }

    @Override
    public CombatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CombatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_combat,null));
    }

    @Override
    public void onBindViewHolder(final CombatViewHolder holder, int position) {
        holder.bindDataToViews(mCombatants[position]);
        holder.mSlotCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.getAdapterPosition()>1){
                    mSelectionListener.onSelectedSlot(holder.getAdapterPosition());
                }else{
                    Toast.makeText(view.getContext(),"Can't place in enemy slot", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    interface OnSelectedSlotListener{
        void onSelectedSlot(int position);
    }
}
