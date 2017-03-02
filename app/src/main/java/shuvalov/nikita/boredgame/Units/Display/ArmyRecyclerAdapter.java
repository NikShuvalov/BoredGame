package shuvalov.nikita.boredgame.Units.Display;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.R;
import shuvalov.nikita.boredgame.Units.Army;

/**
 * Created by NikitaShuvalov on 2/23/17.
 */

public class ArmyRecyclerAdapter extends RecyclerView.Adapter<ArmyViewholder>{
    private ArrayList<Army> mPlayerArmy, mSelectedUnits;
    private boolean[] mSelectedPositions;
    private boolean mSelectable;
    private int mSelectedPosition, mMaxUnitsSelectable;
    private ArmyViewholder mSelectedHolder;

    /**
     * This constructor allows for the selection of multiple units, up to the amount passed as the third param.
     *
     * @param playerArmy The player's army to display
     * @param selectable Gives clickability
     * @param maxUnitsSelectable Max number of units that can be selected
     */
    public ArmyRecyclerAdapter(ArrayList<Army> playerArmy, boolean selectable, int maxUnitsSelectable) {
        mPlayerArmy = playerArmy;
        mSelectable = selectable;
        mSelectedPosition=-1;
        mMaxUnitsSelectable = maxUnitsSelectable;
        mSelectedUnits = new ArrayList<>();
        mSelectedPositions = new boolean[mPlayerArmy.size()];
    }

    /**
     * This constructor allows for single selection of units.
     * @param playerArmy The player's army to display
     * @param selectable Gives clickability
     */
    public ArmyRecyclerAdapter(ArrayList<Army> playerArmy, boolean selectable) {
        mPlayerArmy = playerArmy;
        mSelectable = selectable;
        mSelectedPosition=-1;
        mMaxUnitsSelectable = 1;
        mSelectedPositions = new boolean[mPlayerArmy.size()];

    }

    /**
     * Use this constructor just to display the units without clickability.
     * @param playerArmy The Player's army to display
     */
    public ArmyRecyclerAdapter(ArrayList<Army> playerArmy) {
        mPlayerArmy = playerArmy;
        mSelectable = false;
        mSelectedPosition=-1;
        mSelectedPositions = new boolean[mPlayerArmy.size()];

    }


    @Override
    public ArmyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArmyViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_army,null));
    }

    @Override
    public void onBindViewHolder(final ArmyViewholder holder, final int position) {
        holder.bindDataToViews(mPlayerArmy.get(position));
        if(mSelectedPositions[position]){
            holder.mUnitCard.setCardBackgroundColor(Color.argb(100,150,229,255));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.mUnitCard.setElevation(10f);
            }
        }else{
            holder.mUnitCard.setCardBackgroundColor(Color.argb(255,255,255,255));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.mUnitCard.setElevation(2f);
            }
        }
        if(mSelectable){
            holder.mUnitCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mMaxUnitsSelectable==1){
                        if(mSelectedPosition==-1){//If user hasn't selected anything yet, select the position.
                            holder.selectColor();
                            mSelectedPosition=holder.getAdapterPosition();
                            mSelectedHolder=holder;
                        }else if (mSelectedPosition==holder.getAdapterPosition()){//If user selected the previously selected position, unselect it.
                            holder.unSelectColor();
                            mSelectedPosition=-1;
                            mSelectedHolder=null;
                        }else{//User has selected a new unit. Replace previous selection with current selection.
                            mSelectedHolder.unSelectColor();
                            holder.selectColor();
                            mSelectedPosition=holder.getAdapterPosition();
                            mSelectedHolder=holder;
                        }
                    }else{
                        if(mSelectedUnits.size()<mMaxUnitsSelectable){
                            if(!mSelectedPositions[holder.getAdapterPosition()]){
                                mSelectedUnits.add(mPlayerArmy.get(holder.getAdapterPosition()));
                                mSelectedPositions[holder.getAdapterPosition()]=true;
                                holder.selectColor();
                                Log.d("Wilderness", "onClick: "+mSelectedUnits.size());
                            }else{
                                mSelectedPositions[holder.getAdapterPosition()]=false;
                                mSelectedUnits.remove(mPlayerArmy.get(holder.getAdapterPosition()));
                                holder.unSelectColor();
                                Log.d("Wilderness", "onClick: "+mSelectedUnits.size());
                            }
                        }else{
                            String toastMessage = String.format("Max number of units (%s) already selected",mMaxUnitsSelectable);
                            Toast.makeText(holder.mUnitCard.getContext(), toastMessage, Toast.LENGTH_SHORT).show();
                        }
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

    public ArrayList<Army> getSelectedArmy(){
        return mSelectedUnits;
    }

    public void resetSelection(){
        mSelectedHolder.unSelectColor();
        mSelectedPosition=-1;
        mSelectedHolder=null;
    }
}
