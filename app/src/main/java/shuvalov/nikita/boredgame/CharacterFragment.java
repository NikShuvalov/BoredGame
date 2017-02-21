package shuvalov.nikita.boredgame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import shuvalov.nikita.boredgame.Players.BaseCharacterRace;


public class CharacterFragment extends Fragment {
    private TextView mWoodText, mStoneText, mIronText, mGoldText, mManaText;


    public CharacterFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
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
    }

}
