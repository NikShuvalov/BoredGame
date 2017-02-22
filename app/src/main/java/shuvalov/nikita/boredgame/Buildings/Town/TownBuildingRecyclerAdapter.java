package shuvalov.nikita.boredgame.Buildings.Town;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.Game.GameUtils;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/20/17.
 */

public class TownBuildingRecyclerAdapter extends RecyclerView.Adapter<BuildingViewHolder> {
    public ArrayList<Building> mBuildings;

    public TownBuildingRecyclerAdapter(ArrayList<Building> buildings){
        mBuildings = buildings;
    }

    @Override
    public BuildingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BuildingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_town, null));
    }

    @Override
    public void onBindViewHolder(final BuildingViewHolder holder, int position) {
        final Building building = mBuildings.get(position);
        holder.bindInformationToView(building);
        String levelUpPrompt;
        if(building.getLevel()==0){
            levelUpPrompt = "Construct building for :\n"+ GameUtils.getCostString(building.getCost());
        }else{
            levelUpPrompt = "Level up building for :\n"+ GameUtils.getCostString(building.getCost());

        }
        holder.mLevelButt.setText(levelUpPrompt);

        holder.mLevelButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!GameUtils.payForLevelUp(GameStateManager.getInstance().getPlayer(0),building.getCost())){
                    Toast.makeText(view.getContext(),"Not enough resources to level up",Toast.LENGTH_LONG).show();
                }else{
                    building.levelUp();
                    notifyItemChanged(holder.getAdapterPosition());
                }
            }
        });

        if(building.getLevel()==0){
            holder.mAbilityButt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Can't use a level 0 building", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            holder.mAbilityButt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(building.useAbility()){
                        applyEffect(building, view.getContext());
                        notifyItemChanged(holder.getAdapterPosition());
                    }else{
                        Toast.makeText(view.getContext(), "Not enough counters", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mBuildings.size();
    }

    public void applyEffect(Building building, Context context){
        switch (building.getId()){
            case GameConstants.LUMBER_MILL_ID:
                GameStateManager.getInstance().getPlayer(0).addWood(1);
                Toast.makeText(context, "Added 1 wood", Toast.LENGTH_SHORT).show();
                break;
            case GameConstants.SMELTERY_ID:
                GameStateManager.getInstance().getPlayer(0).addIron(1);
                Toast.makeText(context, "Added 1 iron", Toast.LENGTH_SHORT).show();
                break;
            case GameConstants.MASON_ID:
                GameStateManager.getInstance().getPlayer(0).addStone(1);
                Toast.makeText(context, "Added 1 stone", Toast.LENGTH_SHORT).show();
                break;
            case GameConstants.JEWELER_ID:
                GameStateManager.getInstance().getPlayer(0).addGold(1);
                Toast.makeText(context, "Added 1 gold", Toast.LENGTH_SHORT).show();
                break;
            case GameConstants.MANA_WELL_ID:
                GameStateManager.getInstance().getPlayer(0).addMana(1);
                Toast.makeText(context, "Added 1 mana", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
