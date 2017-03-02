package shuvalov.nikita.boredgame.Wilderness;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.Game.GameUtils;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.R;
import shuvalov.nikita.boredgame.Units.Army;
import shuvalov.nikita.boredgame.Units.Display.ArmyRecyclerAdapter;
import shuvalov.nikita.boredgame.Units.Mobs.Mob;


public class WildernessFragment extends Fragment implements View.OnClickListener{
    private RecyclerView mWildernessView, mArmyView;
    private Button mSpawn, mFight, mPussyout;
    private WildernessRecyclerAdapter mWildernessAdapter;
    private ArmyRecyclerAdapter mArmyAdapter;
    private static OnWildernessExitListener mExitListener;
    private boolean mBattled;

    public WildernessFragment() {
    }


    public static WildernessFragment newInstance(OnWildernessExitListener exitListener) { //Add an interface to debug activity when automated.
        WildernessFragment fragment = new WildernessFragment();
        mExitListener = exitListener;
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_wilderness, container, false);
        mBattled = false;

        mWildernessView = (RecyclerView)view.findViewById(R.id.wilderness_recycler);
        mArmyView = (RecyclerView)view.findViewById(R.id.player_army_recycler);

        mWildernessAdapter = new WildernessRecyclerAdapter(GameStateManager.getInstance().getWildernessState());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        mWildernessView.setAdapter(mWildernessAdapter);
        mWildernessView.setLayoutManager(linearLayoutManager);

        mArmyAdapter = new ArmyRecyclerAdapter(GameStateManager.getInstance().getPlayer(0).getPlayerArmy(),true,4);//FixMe: Currently hard-coded, might want to have this number change.
        LinearLayoutManager armyLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);

        mArmyView.setAdapter(mArmyAdapter);
        mArmyView.setLayoutManager(armyLayoutManager);


        mSpawn = (Button)view.findViewById(R.id.debug_spawn_butt);
        mFight = (Button)view.findViewById(R.id.fight_action);
        mPussyout = (Button)view.findViewById(R.id.cancel_action);

        mSpawn.setOnClickListener(this);
        mFight.setOnClickListener(this);
        mPussyout.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.cancel_action:
                mExitListener.onWildernessExit();
                break;
            case R.id.fight_action:
                if(!mBattled){
                    Mob selectedMobUnit = mWildernessAdapter.getSelectedUnit();
                    ArrayList<Army> playerTroops = mArmyAdapter.getSelectedArmy();
                    //ToDo: Add a check to see if any army units are selected as well.
                    if(selectedMobUnit==null){
                        Toast.makeText(getContext(), "No creature selected", Toast.LENGTH_SHORT).show();
                    }else{
                        if(playerTroops.isEmpty()){
                            Toast.makeText(getContext(), "No units were selected to fight with", Toast.LENGTH_SHORT).show();
                        }else{
                            String battleLog = GameUtils.wildernessBattle(mArmyAdapter.getSelectedArmy(),mWildernessAdapter.getSelectedUnit());
                            Log.d("Battle Log", "onClick: "+battleLog);
                            mWildernessAdapter.notifyDataSetChanged();
                            mArmyView.setAdapter(new ArmyRecyclerAdapter(mArmyAdapter.getSelectedArmy()));
                            mBattled=true;
                            mFight.setText("View Details");
                            mPussyout.setText("Skip details");
                            mBattled=false;
                            //ToDo: Leave only the creature that was battled, remove the highlight from it.
                        }
                }

                }
                else{
                    //ToDo: Display battle log details, either in new fragment or move everything in this fragment out and put a textView, or Alert Dialog.
                    mExitListener.onWildernessExit();
                }
                break;
            case R.id.debug_spawn_butt:
                GameStateManager.getInstance().replenishWilderness(); //For now, just a hard-coded method to fill the wilderness with Yetis.
                mWildernessAdapter.notifyDataSetChanged();
                //ToDo: Create deck in gameStateManager with all mob cards, perhaps add a level parameter to mobs to prevent overpowered mobs from being drawn early.
                break;
        }
    }
    public interface OnWildernessExitListener{
        void onWildernessExit();
    }
}
