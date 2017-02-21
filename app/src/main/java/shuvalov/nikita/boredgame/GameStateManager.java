package shuvalov.nikita.boredgame;

import java.util.ArrayList;
import java.util.Arrays;

import shuvalov.nikita.boredgame.Cards.ResourceCard;
import shuvalov.nikita.boredgame.Game.HandDisplayRecyclerAdapter;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;

/**
 * Created by NikitaShuvalov on 2/19/17.
 */

public class GameStateManager {
    private ArrayList<BaseCharacterRace> mPlayerList;
    private HandDisplayRecyclerAdapter mHandDisplayAdapter;

    private static GameStateManager sGameStateManager;

    private GameStateManager(){
        mPlayerList = new ArrayList<>();
    }

    public static GameStateManager getInstance() {
        if(sGameStateManager==null){
            sGameStateManager = new GameStateManager();
        }
        return sGameStateManager;
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
        BaseCharacterRace player = mPlayerList.get(playerNumber);
        ArrayList<ResourceCard> draftedCards = player.getDraftedCards();
        for(ResourceCard card: draftedCards){
            String resourceType = card.getResource();
            switch (resourceType){
                case ResourceCard.GOLD:
                    player.addGold(card.getRank());
                    break;
                case ResourceCard.MANA:
                    player.addMana(card.getRank());
                    break;
                case ResourceCard.WOOD:
                    player.addWood(card.getRank());
                    break;
                case ResourceCard.STONE:
                    player.addStone(card.getRank());
                    break;
                case ResourceCard.IRON:
                    player.addIron(card.getRank());
                    break;
            }
        }

    }
}
