package shuvalov.nikita.boredgame.Draft;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.Cards.ResourceCard;

/**
 * Created by NikitaShuvalov on 2/7/17.
 */

public class DraftManager {
//    private ArrayList<ResourceCard> mPack;
    private ArrayList<ArrayList<ResourceCard>> mDraftPacks;
    private int mDraftStep;


    private DraftManager(){
        mDraftPacks = new ArrayList<>();
    }

    private static DraftManager sDraftManager;

    public static DraftManager getInstance() {
        if(sDraftManager==null){
            sDraftManager = new DraftManager();
        }
        return sDraftManager;
    }

//    public void changePack(ArrayList<ResourceCard> nextPack){
//        mPack = nextPack;
//    }

    public ArrayList<ResourceCard> getPack(int playerNumber) {
        return mDraftPacks.get(playerNumber);
    }

    public void addDraftPack(ArrayList<ResourceCard> draftPack){
        mDraftPacks.add(draftPack);
    }

    public int getDraftStep() {
        return mDraftStep;
    }

    public void setDraftStep(int draftStep) {
        mDraftStep = draftStep;
    }

    public void nextDraftStep(){
        mDraftStep++;
        ArrayList<ResourceCard> temp = mDraftPacks.get(mDraftPacks.size()-1);
        for(int i = mDraftPacks.size()-1; i>0;i--){
            mDraftPacks.set(i,mDraftPacks.get(i-1));
        }
        mDraftPacks.set(0,temp);
    }

    public ResourceCard pickCard(int playerNumber, int cardPicked){
        ResourceCard pickedCard = mDraftPacks.get(0).get(cardPicked);
        ResourceCard computerPickedCard = mDraftPacks.get(1).get(cardPicked);
//        ResourceCard pickedCard = mDraftPacks.get(playerNumber).get(cardPicked); //Get the picked card
        mDraftPacks.get(0).remove(cardPicked); //Remove the picked card from the draft deck
        mDraftPacks.get(1).remove(cardPicked);
        nextDraftStep();
        return pickedCard;
    }

    public void resetDrafting(){
        mDraftPacks.clear();
    }
}
