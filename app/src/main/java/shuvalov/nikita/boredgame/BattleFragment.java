package shuvalov.nikita.boredgame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.Units.Army;
import shuvalov.nikita.boredgame.Units.Display.ArmyRecyclerAdapter;
import shuvalov.nikita.boredgame.Units.Mercenary;
import shuvalov.nikita.boredgame.Units.WoodGolem;


public class BattleFragment extends Fragment implements View.OnClickListener, CombatRecyclerAdapter.OnSelectedSlotListener{
    Button mSpawnEnemy, mFight;
    private RecyclerView mSlotRecyclerView, mPlayerArmyRecycler;
    private CombatRecyclerAdapter mFieldAdapter;
    private ArmyRecyclerAdapter mArmyAdapter;


    public BattleFragment() {
        // Required empty public constructor
    }


    //ToDo: Add a listener interface here once we have automated the phases.
    public static BattleFragment newInstance() {
        return new BattleFragment();
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
        View view = inflater.inflate(R.layout.fragment_battle, container, false);

        mSpawnEnemy = (Button)view.findViewById(R.id.spawn_enemy_butt);
        mFight = (Button)view.findViewById(R.id.combat_start_butt);

        mFight.setOnClickListener(this);
        mSpawnEnemy.setOnClickListener(this);

        mSlotRecyclerView = (RecyclerView)view.findViewById(R.id.slots_recycler);
        mPlayerArmyRecycler = (RecyclerView)view.findViewById(R.id.player_army_recycler);
        setUpRecycler();

        return view;
    }

    public void setUpRecycler(){
        mFieldAdapter = new CombatRecyclerAdapter(GameStateManager.getInstance().getBattleState(), this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);

        mSlotRecyclerView.setAdapter(mFieldAdapter);
        mSlotRecyclerView.setLayoutManager(gridLayoutManager);

        mArmyAdapter = new ArmyRecyclerAdapter(GameStateManager.getInstance().getPlayer(0).getPlayerArmy(), true);
        LinearLayoutManager lineUpMaggots = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mPlayerArmyRecycler.setAdapter(mArmyAdapter);
        mPlayerArmyRecycler.setLayoutManager(lineUpMaggots);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.spawn_enemy_butt:
                //To Keep things simple just going to spawn both enemies for now.
                GameStateManager gameStateManager = GameStateManager.getInstance();
                gameStateManager.addUnitToBattlefield(new Mercenary(),0);
                gameStateManager.addUnitToBattlefield(new WoodGolem(),1);
                mFieldAdapter.notifyDataSetChanged();
                break;
            case R.id.combat_start_butt:
                //Start battle
                Toast.makeText(getContext(), "LOL! Not quite there yet. Tomorrow, maybe.", Toast.LENGTH_SHORT).show();
                //Make sure to place back units into player's army's, if they survive, at the end of combat.
                break;
        }

    }

    /**
     *
     * @param position Is the battlefield slot selected
     */
    @Override
    public void onSelectedSlot(int position) {
        Army unit = mArmyAdapter.getSelectedUnit();
        if(unit==null){
            Toast.makeText(getContext(), "No unit selected", Toast.LENGTH_SHORT).show();
        }else{
            GameStateManager gameStateManager = GameStateManager.getInstance();
            Army unitInSlot = gameStateManager.getBattleState()[position];
            gameStateManager.addUnitToBattlefield(unit,position); //Add unit to the battlefield
            gameStateManager.getPlayer(0).getPlayerArmy().remove(mArmyAdapter.getSelectedPosition()); //Remove unit from user's army
            if(unitInSlot==null){ //If no Unit was in the selected slot, just notify adapter that unit was removed.
                mArmyAdapter.notifyItemRemoved(mArmyAdapter.getSelectedPosition()); //Notify unit was removed from player's army
            }else{ //If there was a unit in the selected slot, replace it into the player's army.
                gameStateManager.getPlayer(0).addUnitToArmy(unitInSlot); //Add the unit in the slot back to the player's army.
                mArmyAdapter.notifyDataSetChanged(); //Notify that the unit was put back into the roster.
            }
            mFieldAdapter.notifyItemChanged(position); //Notify unit was added to battlefield
            mArmyAdapter.resetSelection();//Reset selection parameters.
        }
    }
}
