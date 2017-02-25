package shuvalov.nikita.boredgame.Game;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import shuvalov.nikita.boredgame.BattleFragment;
import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.Cards.ActionCard;
import shuvalov.nikita.boredgame.Cards.ResourceCard;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.Units.Army;

/**
 * Created by NikitaShuvalov on 2/19/17.
 */

public class GameStateManager {
    private ArrayList<BaseCharacterRace> mPlayerList;
    private HandDisplayRecyclerAdapter mHandDisplayAdapter;
    private TextView mToolbarText; //FixMe: Remove this once I have a better way to make a HUD.
    private Army[] mBattleState;


    private static GameStateManager sGameStateManager;

    private GameStateManager(){
        mPlayerList = new ArrayList<>();
        mBattleState= new Army[BattleFragment.COLUMN_AMOUNT*2];
    }

    public static GameStateManager getInstance() {
        if(sGameStateManager==null){
            sGameStateManager = new GameStateManager();
        }
        return sGameStateManager;
    }

    public void assignResourceText(TextView resourceTextView){
        mToolbarText = resourceTextView;
    }

    public void setResourceText(String resourceText){
        mToolbarText.setText(resourceText);
    }

    public void setPlayers(BaseCharacterRace... players){
        mPlayerList.addAll(Arrays.asList(players));
    }

    public ArrayList<BaseCharacterRace> getPlayerList(){
        return mPlayerList;
    }

    public BaseCharacterRace getPlayer(int playerNumber){
        return mPlayerList.get(playerNumber);
    }

    public void setDraftedAdapter(HandDisplayRecyclerAdapter adapter){
        mHandDisplayAdapter = adapter;
    }

    public void notifyChangeHandDisplay(){
        mHandDisplayAdapter.notifyDataSetChanged();
    }

    public HandDisplayRecyclerAdapter getDraftedAdapter(){
        return mHandDisplayAdapter;
    }

    public void redeemDraftedCards(int playerNumber){
//        int wood=0, iron = 0, stone=0, gold=0, mana=0;
        BaseCharacterRace player = mPlayerList.get(playerNumber);
        ArrayList<ResourceCard> draftedCards = player.getDraftedCards();
        for(ResourceCard card: draftedCards){
            String resourceType = card.getResource();
            switch (resourceType){
                case ResourceCard.GOLD:
//                    gold+=card.getRank();
                    player.addToDraftCacheGold(card.getRank());
                    for(Building building: player.getTownBuildings()) {
                        if (building.getId() == GameConstants.JEWELER_ID) {
                            if (building.getLevel() != 0) {
                                building.addCounter();
                            }
                            break;
                        }
                    }
                    break;
                case ResourceCard.MANA:
//                    mana+=card.getRank();
                    player.addToDraftCacheMana(card.getRank());
                    for(Building building: player.getTownBuildings()) {
                        if (building.getId() == GameConstants.MANA_WELL_ID) {
                            if (building.getLevel() != 0) {
                                building.addCounter();
                            }
                            break;
                        }
                    }
                    break;
                case ResourceCard.WOOD:
//                    wood+=card.getRank();
                    player.addToDraftCacheWood(card.getRank());
                    for(Building building: player.getTownBuildings()){
                        if(building.getId()==GameConstants.LUMBER_MILL_ID){
                            if(building.getLevel()!=0){
                                building.addCounter();
                            }
                            break;
                        }
                    }
                    break;
                case ResourceCard.STONE:
//                    stone+=card.getRank();
                    player.addToDraftCacheStone(card.getRank());
                    for(Building building: player.getTownBuildings()) {
                        if (building.getId() == GameConstants.MASON_ID) {
                            if (building.getLevel() != 0) {
                                building.addCounter();
                            }
                            break;
                        }
                    }
                    break;
                case ResourceCard.IRON:
//                    iron+=card.getRank();
                    player.addToDraftCacheIron(card.getRank());
                    for(Building building: player.getTownBuildings()) {
                        if (building.getId() == GameConstants.SMELTERY_ID) {
                            if (building.getLevel() != 0) {
                                building.addCounter();
                            }
                            break;
                        }
                    }
                    break;
            }
        }
    }
    public void addUnitToBattlefield(Army unit, int slot){
        mBattleState[slot]=unit;
    }
    public Army[] getBattleState(){
        return mBattleState;
    }
    public void resetBattleState(){
        for(int i=0;i<mBattleState.length;i++){
            mBattleState[i]=null;
        }
    }

}
