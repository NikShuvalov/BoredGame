package shuvalov.nikita.boredgame.Buildings.Town;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.R;


public class TownFragment extends Fragment {

    public TownFragment() {
        // Required empty public constructor
    }


    public static TownFragment newInstance() {
        return new TownFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_town, container, false);
        RecyclerView buildingRecycler = (RecyclerView) view.findViewById(R.id.town_recycler);

        buildingRecycler.setAdapter(new TownBuildingRecyclerAdapter(GameStateManager.getInstance().getPlayer(0).getTownBuildings()));
        buildingRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));

        return view;
    }

}
