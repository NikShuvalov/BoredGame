package shuvalov.nikita.boredgame.Players;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.R;
import shuvalov.nikita.boredgame.Units.ArmyRecyclerAdapter;


public class CharacterFragment extends Fragment {
    private TextView mWoodText, mStoneText, mIronText, mGoldText, mManaText, mArmyText;
    private RecyclerView mArmyRecycler;


    public CharacterFragment() {
        // Required empty public constructor
    }


    public static CharacterFragment newInstance() {
        return new CharacterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character, container, false);
        mGoldText = (TextView)view.findViewById(R.id.gold_amount);
        mIronText = (TextView)view.findViewById(R.id.iron_amount);
        mWoodText = (TextView)view.findViewById(R.id.wood_amount);
        mStoneText = (TextView)view.findViewById(R.id.stone_amount);
        mManaText = (TextView)view.findViewById(R.id.mana_amount);
        mArmyText = (TextView)view.findViewById(R.id.army_amount);

        mArmyRecycler = (RecyclerView)view.findViewById(R.id.army_recycler);

        setUpRecycler();
        setDisplayValues();
        return view;
    }

    public void setDisplayValues(){
        BaseCharacterRace character = GameStateManager.getInstance().getPlayer(0);

        mGoldText.setText(String.valueOf(character.getGold()));
        mIronText.setText(String.valueOf(character.getIron()));
        mWoodText.setText(String.valueOf(character.getWood()));
        mStoneText.setText(String.valueOf(character.getStone()));
        mManaText.setText(String.valueOf(character.getMana()));
        mArmyText.setText(String.valueOf(character.getPlayerArmy().size()));
    }

    public void setUpRecycler(){
        ArmyRecyclerAdapter adapter = new ArmyRecyclerAdapter(GameStateManager.getInstance().getPlayer(0).getPlayerArmy());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mArmyRecycler.setAdapter(adapter);
        mArmyRecycler.setLayoutManager(linearLayoutManager);
    }

}
