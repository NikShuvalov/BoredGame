package shuvalov.nikita.boredgame.Units;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/23/17.
 */

public class ArmyRecyclerAdapter extends RecyclerView.Adapter<ArmyViewholder>{
    private ArrayList<Army> mPlayerArmy;

    public ArmyRecyclerAdapter(ArrayList<Army> playerArmy) {
        mPlayerArmy = playerArmy;
    }

    @Override
    public ArmyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArmyViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_army,null));
    }

    @Override
    public void onBindViewHolder(ArmyViewholder holder, int position) {
        holder.bindDataToViews(mPlayerArmy.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlayerArmy.size();
    }
}
