package shuvalov.nikita.boredgame.Buildings.Town;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.games.Game;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.Cards.ResourceCard;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.Game.GameUtils;
import shuvalov.nikita.boredgame.MainActivity;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.R;
import shuvalov.nikita.boredgame.Units.Mercenary;
import shuvalov.nikita.boredgame.Units.WoodGolem;

/**
 * Created by NikitaShuvalov on 2/20/17.
 */

public class TownBuildingRecyclerAdapter extends RecyclerView.Adapter<BuildingViewHolder> {
    public ArrayList<Building> mBuildings;
//    private String mGiveAwayResource, mReceiveResource;
//    private EditText mGiveAmount, mRecAmount;


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
                if(!GameUtils.canPayCost(GameStateManager.getInstance().getPlayer(0),building.getCost())){
                    Toast.makeText(view.getContext(),"Not enough resources to level up",Toast.LENGTH_LONG).show();
                }else{
                    GameStateManager.getInstance().setResourceText(GameUtils.currentResourceStockpile(GameStateManager.getInstance().getPlayer(0)));
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
                    if(building.useAbility(GameStateManager.getInstance().getPlayer(0), view.getContext())){
//                        getUserInput(building, view.getContext());
                        notifyItemChanged(holder.getAdapterPosition());
                        GameStateManager.getInstance().setResourceText(GameUtils.currentResourceStockpile(GameStateManager.getInstance().getPlayer(0)));
                    }else{
                        Toast.makeText(view.getContext(), "Not enough", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mBuildings.size();
    }

//    public void getUserInput(Building building, Context context){
//        switch(building.getId()){
//            case GameConstants.MARKETPLACE_ID:
//                createUserDialog(building, context);
//                break;
//        }
//
//
//    }
//
//    public void createUserDialog(Building building, final Context context){
//        mReceiveResource=null;
//        mGiveAwayResource = null;
//
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_marketplace,null);
//        Spinner giveSpinner, recSpinner;
//        giveSpinner = (Spinner)view.findViewById(R.id.trading_spinner);
//        mGiveAmount = (EditText)view.findViewById(R.id.trading_amount);
//        recSpinner = (Spinner)view.findViewById(R.id.receiving_spinner);
//        mRecAmount = (EditText)view.findViewById(R.id.receiving_amount);
//        ArrayAdapter<CharSequence> giveAdap = ArrayAdapter.createFromResource(context, R.array.resources,R.layout.support_simple_spinner_dropdown_item);
//        ArrayAdapter<CharSequence> receiveAdap = ArrayAdapter.createFromResource(context, R.array.resources, android.R.layout.simple_spinner_item);
//
//        giveAdap.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        receiveAdap.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//
//        giveSpinner.setAdapter(giveAdap);
//        recSpinner.setAdapter(receiveAdap);
//
//        giveSpinner.setOnItemSelectedListener(this);
//        recSpinner.setOnItemSelectedListener(this);
//
//        mGiveAmount.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                //Put a delay here or just have the trade amount = to the adjusted amount without displaying the difference.
//                int tradedAmount = Integer.valueOf(editable.toString());
//                tradedAmount = tradedAmount-(tradedAmount%2);
//                mGiveAmount.setText(String.valueOf(tradedAmount));
//                mRecAmount.setText(String.valueOf(tradedAmount/2));
//
//
//            }
//        });
//        new AlertDialog.Builder(context)
//                .setTitle(R.string.marketplace_name)
//                .setView(view)
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        mReceiveResource=null;
//                        mGiveAwayResource=null;
//                        dialogInterface.dismiss();
//                    }
//                })
//                .setPositiveButton("Trade", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        if(mGiveAwayResource==null || mReceiveResource == null){
//                            Toast.makeText(context, "Need to select both resources", Toast.LENGTH_SHORT).show();
//                        }else if(mGiveAwayResource.equals(mReceiveResource)){
//                            Toast.makeText(context, "Can't trade for the same resource", Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(context, "Make trade", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                })
//                .create().show();//ToDo: Finish this.
//
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        switch(adapterView.getId()){
//            case R.id.receiving_spinner:
//                switch(i) {
//                    case 0:
//                        mReceiveResource=null;
//                        break;
//                    case 1:
//                        mReceiveResource= ResourceCard.WOOD;
//                        break;
//                    case 2:
//                        mReceiveResource = ResourceCard.IRON;
//                        break;
//                    case 3:
//                        mReceiveResource=ResourceCard.STONE;
//                        break;
//                    case 4:
//                        mReceiveResource = ResourceCard.GOLD;
//                        break;
//                    case 5:
//                        mReceiveResource= ResourceCard.MANA;
//                        break;
//                }
//                Log.d("Marketplace", "onItemSelected: Receiving");
//                break;
//            case R.id.trading_spinner:
//                BaseCharacterRace player = GameStateManager.getInstance().getPlayer(0);
//                int amount;
//                switch(i){
//                    case 0:
//                        mGiveAwayResource=null;
//                        break;
//                    case 1:
//                        mGiveAwayResource= ResourceCard.WOOD;
//                        amount = Integer.valueOf(mGiveAmount.getText().toString());
//                        if(amount>player.getWood()){
//                            amount=player.getWood();
//                        }
//                        amount = amount-(amount%2);
//                        mGiveAmount.setText(String.valueOf(amount));
//                        break;
//                    case 2:
//                        mGiveAwayResource = ResourceCard.IRON;
//                        amount = Integer.valueOf(mGiveAmount.getText().toString());
//                        if(amount>player.getIron()){
//                            amount=player.getIron();
//                        }
//                        amount = amount-(amount%2);
//                        mGiveAmount.setText(String.valueOf(amount));
//                        break;
//                    case 3:
//                        mGiveAwayResource=ResourceCard.STONE;
//                        amount = Integer.valueOf(mGiveAmount.getText().toString());
//                        if(amount>player.getStone()){
//                            amount=player.getStone();
//                        }
//                        amount = amount-(amount%2);
//                        mGiveAmount.setText(String.valueOf(amount));
//                        break;
//                    case 4:
//                        mGiveAwayResource = ResourceCard.GOLD;
//                        amount = Integer.valueOf(mGiveAmount.getText().toString());
//                        if(amount>player.getGold()){
//                            amount=player.getGold();
//                        }
//                        amount = amount-(amount%2);
//                        mGiveAmount.setText(String.valueOf(amount));
//                        break;
//                    case 5:
//                        mGiveAwayResource= ResourceCard.MANA;
//                        amount = Integer.valueOf(mGiveAmount.getText().toString());
//                        if(amount>player.getMana()){
//                            amount=player.getMana();
//                        }
//                        amount = amount-(amount%2);
//                        mGiveAmount.setText(String.valueOf(amount));
//                        break;
//                }
//
//                Log.d("Marketplace", "onItemSelected: Trading");
//                break;
//        }
//        //ToDo: Check which adapter is being used, then apply that resource to the appropriate selected blah blah blah.
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}
