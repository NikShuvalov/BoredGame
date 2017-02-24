package shuvalov.nikita.boredgame.Units.Display;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.R;
import shuvalov.nikita.boredgame.Units.Army;

/**
 * Created by NikitaShuvalov on 2/23/17.
 */

public class ArmyRecyclerAdapter extends RecyclerView.Adapter<ArmyViewholder>{
    private ArrayList<Army> mPlayerArmy;
    private boolean mSelectable;
    private int mSelectedPosition;
    private ArmyViewholder mSelectedHolder;

    /**
     * This Adapter is used in both the character fragment and battle fragment, if you're using it for the battle fragment
     * pass selectable as true to give the adapter functionality, otherwise pass false to just display army.
     * @param playerArmy
     * @param selectable
     */
    public ArmyRecyclerAdapter(ArrayList<Army> playerArmy, boolean selectable) {
        mPlayerArmy = playerArmy;
        mSelectable = selectable;
        mSelectedPosition=-1;
    }

    @Override
    public ArmyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArmyViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_army,null));
    }

    @Override
    public void onBindViewHolder(final ArmyViewholder holder, int position) {
        holder.bindDataToViews(mPlayerArmy.get(position));
        if(mSelectable){
            holder.mUnitCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mSelectedPosition==-1){//If user hasn't selected anything yet, select the position.
                        holder.toggleColor();
                        mSelectedHolder=holder;
                        mSelectedPosition=holder.getAdapterPosition();
                    }else if (mSelectedPosition==holder.getAdapterPosition()){//If user selected the previously selected position, unselect it.
                        holder.toggleColor();
                        mSelectedPosition=-1;
                        mSelectedHolder=null;
                    }else{//User has selected a new unit. Replace previous selection with current selection.
                        mSelectedHolder.toggleColor();
                        holder.toggleColor();
                        mSelectedPosition=holder.getAdapterPosition();
                        mSelectedHolder=holder;
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPlayerArmy.size();
    }

    public Army getSelectedUnit(){
        if(mSelectedPosition!=-1){
            return mPlayerArmy.get(mSelectedPosition);
        }
        return null;
    }
    public int getSelectedPosition(){
        return mSelectedPosition;
    }

    public void resetSelection(){
        mSelectedHolder.toggleColor();
        mSelectedPosition=-1;
        mSelectedHolder=null;
    }
}
