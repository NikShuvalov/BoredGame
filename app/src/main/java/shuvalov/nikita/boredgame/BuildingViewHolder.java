package shuvalov.nikita.boredgame;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import shuvalov.nikita.boredgame.Buildings.Building;

/**
 * Created by NikitaShuvalov on 2/20/17.
 */

public class BuildingViewHolder extends RecyclerView.ViewHolder {
    private TextView mNameText, mDescriptionText, mCounterText, mLevelText;
    public Button mAbilityButt, mLevelButt;

    public BuildingViewHolder(View itemView) {
        super(itemView);
        mNameText = (TextView) itemView.findViewById(R.id.building_name_text);
        mDescriptionText = (TextView)itemView.findViewById(R.id.description_text);
        mCounterText = (TextView)itemView.findViewById(R.id.counter_text);
        mLevelText = (TextView)itemView.findViewById(R.id.level_text);


        mAbilityButt = (Button) itemView.findViewById(R.id.ability_button);
        mLevelButt = (Button) itemView.findViewById(R.id.level_button);
    }

    public void bindInformationToView(Building building){
        mNameText.setText(building.getName(mNameText.getContext()));
        mDescriptionText.setText(String.format(building.getDescription(mDescriptionText.getContext()),String.valueOf(5-building.getLevel())));
        mLevelText.setText("Level: " + building.getLevel());
        String counterText = "Counters :"+ building.getCounters();
        mCounterText.setText(counterText);
    }
}
