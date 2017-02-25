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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.Game.GameUtils;
import shuvalov.nikita.boredgame.Units.Army;
import shuvalov.nikita.boredgame.Units.Display.ArmyRecyclerAdapter;
import shuvalov.nikita.boredgame.Units.Mercenary;
import shuvalov.nikita.boredgame.Units.WoodGolem;


public class BattleFragment extends Fragment implements View.OnClickListener, CombatRecyclerAdapter.OnSelectedSlotListener{
    Button mSpawnEnemy, mFight;
    private RecyclerView mSlotRecyclerView, mPlayerArmyRecycler;
    private CombatRecyclerAdapter mFieldAdapter;
    private ArmyRecyclerAdapter mArmyAdapter;
    TextView mOutcome0, mOutcome1; //ToDo: Replace with recycler.
    public static final int COLUMN_AMOUNT=2;
    private LinearLayout mOutcomeHolder;
    private boolean mBattleCompleted;
    private static CompletedBattlePhaseListener mCompletedBattleListener;


    public BattleFragment() {
        // Required empty public constructor
    }


    //ToDo: Add a listener interface here once we have automated the phases.
    public static BattleFragment newInstance(CompletedBattlePhaseListener completedBattlePhaseListener) {
        mCompletedBattleListener = completedBattlePhaseListener;
        return new BattleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBattleCompleted=false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battle, container, false);

        mSpawnEnemy = (Button)view.findViewById(R.id.spawn_enemy_butt);
        mFight = (Button)view.findViewById(R.id.combat_start_butt);

        mOutcomeHolder = (LinearLayout)view.findViewById(R.id.outcome_holder);
        mOutcome0 =(TextView)view.findViewById(R.id.slot_0_outcome);
        mOutcome1 = (TextView)view.findViewById(R.id.slot_1_outcome);

        mFight.setOnClickListener(this);
        mSpawnEnemy.setOnClickListener(this);

        mSlotRecyclerView = (RecyclerView)view.findViewById(R.id.slots_recycler);
        mPlayerArmyRecycler = (RecyclerView)view.findViewById(R.id.player_army_recycler);


        mPlayerArmyRecycler.setVisibility(View.VISIBLE);
        mOutcomeHolder.setVisibility(View.INVISIBLE);
        setUpRecycler();

        return view;
    }

    public void setUpRecycler(){
        mFieldAdapter = new CombatRecyclerAdapter(GameStateManager.getInstance().getBattleState(), this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),COLUMN_AMOUNT);

        mSlotRecyclerView.setAdapter(mFieldAdapter);
        mSlotRecyclerView.setLayoutManager(gridLayoutManager);

        mArmyAdapter = new ArmyRecyclerAdapter(GameStateManager.getInstance().getPlayer(0).getPlayerArmy(), true);
        LinearLayoutManager lineUpMaggots = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mPlayerArmyRecycler.setAdapter(mArmyAdapter);
        mPlayerArmyRecycler.setLayoutManager(lineUpMaggots);
    }

    @Override
    public void onClick(View view) {
        GameStateManager gameStateManager = GameStateManager.getInstance();
        switch (view.getId()){
            case R.id.spawn_enemy_butt:
                //To Keep things simple just going to spawn both enemies for now.
                gameStateManager.addUnitToBattlefield(new Mercenary(),0);
                gameStateManager.addUnitToBattlefield(new WoodGolem(),1);
                mFieldAdapter.notifyDataSetChanged();
                break;
            case R.id.combat_start_butt:
                //Start battle
                if(!mBattleCompleted){
                    Army[] battleState = gameStateManager.getBattleState();
                    mPlayerArmyRecycler.setVisibility(View.GONE);
                    mOutcomeHolder.setVisibility(View.VISIBLE);

                    //ToDo: Replace with recycler.
                    mOutcome0.setText(GameUtils.simulateBattle(battleState,0,2));
                    mOutcome1.setText(GameUtils.simulateBattle(battleState,1,3));

                    mFieldAdapter.notifyDataSetChanged();
                    mFight.setText("Go to next Step");
                    mBattleCompleted = true;
                }else{
                    mBattleCompleted=false;
                    gameStateManager.resetBattleState();
                    mCompletedBattleListener.completedBattlePhase();
                }
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
    public interface CompletedBattlePhaseListener{
        void completedBattlePhase();
    }
}
