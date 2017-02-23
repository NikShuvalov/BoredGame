package shuvalov.nikita.boredgame.Players;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.Buildings.Generic.Jeweler;
import shuvalov.nikita.boredgame.Buildings.Generic.LumberMill;
import shuvalov.nikita.boredgame.Buildings.Generic.ManaWell;
import shuvalov.nikita.boredgame.Buildings.Generic.Mason;
import shuvalov.nikita.boredgame.Buildings.Generic.Smeltery;
import shuvalov.nikita.boredgame.Cards.ActionCard;
import shuvalov.nikita.boredgame.Cards.ResourceCard;
import shuvalov.nikita.boredgame.GameConstants;

/**
 * Created by NikitaShuvalov on 1/25/17.
 */

public abstract class BaseCharacterRace {
    //Doing it with this naming format, as opposed to "mName, etc." to make pushing to firebase database simpler.
    private String name;
    private int id;
    private int gold, wood, stone, iron, mana;
    private float draftCacheGold, draftCacheWood,draftCacheStone,draftCacheIron,draftCacheMana;
    private String race;
    private int draftStep;
    private ArrayList<ResourceCard> draftedCards;
    private ArrayList<Building> townBuildings;
    private ArrayList<ActionCard> actionHand;

    public BaseCharacterRace(){}

    public BaseCharacterRace(String name, int id, int gold, int wood, int stone, int iron, int mana, String race) {
        this.name = name;
        this.id = id;
        this.gold = gold;
        this.wood = wood;
        this.stone = stone;
        this.iron = iron;
        this.mana = mana;
        this.race = race;
        this.draftedCards = new ArrayList<>();
        this.actionHand = new ArrayList<>();
        createBasicBuildings();
        draftStep=0;
        draftCacheGold=draftCacheWood=draftCacheStone=draftCacheIron=draftCacheMana=0f;
    }

    private void createBasicBuildings(){
        this.townBuildings = new ArrayList<>();
        townBuildings.add(new LumberMill());
        townBuildings.add(new Smeltery());
        townBuildings.add(new Mason());
        townBuildings.add(new Jeweler());
        townBuildings.add(new ManaWell());
    }


    public ArrayList<Building> getTownBuildings(){
        return townBuildings;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getGold() {
        return gold;
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getIron() {
        return iron;
    }

    public int getMana() {
        return mana;
    }

    public boolean spendGold(int goldSpent){
        if(gold<goldSpent){
            return false;
        }
        gold-=goldSpent;
        return true;
    }

    public boolean spendWood(int woodSpent){
        if(wood<woodSpent){
            return false;
        }
        wood-=woodSpent;
        return true;
    }

    public boolean spendStone(int stoneSpent){
        if(stone<stoneSpent){
            return false;
        }
        stone-=stoneSpent;
        return true;
    }

    public boolean spendIron(int ironSpent){
        if(iron <ironSpent){
            return false;
        }
        iron -=ironSpent;
        return true;
    }

    public boolean spendMana(int spentMana){
        if(mana <spentMana){
            return false;
        }
        mana -=spentMana;
        return true;
    }

    //Adds resources directly to player's stockpile <Start>
    public int addGold(int goldGained){
        gold+=goldGained;
        return gold;
    }

    public int addWood(int woodGained){
        wood+=woodGained;
        return wood;
    }

    public int addStone (int stoneGained){
        stone+=stoneGained;
        return stone;
    }

    public int addIron(int ironGained){
        iron +=ironGained;
        return iron;
    }

    public int addMana(int manaGained){
        mana +=manaGained;
        return mana;
    }
    //</End>

    //Adds resource to the draft cache, held to allow to be modified before draft resolution <Start>
    public float addToDraftCacheGold(int goldDrafted){
        draftCacheGold+=goldDrafted;
        return draftCacheGold;
    }
    public float addToDraftCacheWood(int woodDrafted){
        draftCacheWood+=woodDrafted;
        return draftCacheWood;
    }
    public float addToDraftCacheIron(int ironDrafted){
        draftCacheIron+=ironDrafted;
        return draftCacheIron;
    }
    public float addToDraftCacheStone(int stoneDrafted){
        draftCacheStone+=stoneDrafted;
        return draftCacheStone;
    }
    public float addToDraftCacheMana(int manaDrafted){
        draftCacheMana+=manaDrafted;
        return draftCacheMana;
    }
    //</End>


    public float getDraftCacheGold() {
        return draftCacheGold;
    }

    public float getDraftCacheWood() {
        return draftCacheWood;
    }

    public float getDraftCacheStone() {
        return draftCacheStone;
    }

    public float getDraftCacheIron() {
        return draftCacheIron;
    }

    public float getDraftCacheMana() {
        return draftCacheMana;
    }

    public void setDraftCacheGold(float draftCacheGold) {
        this.draftCacheGold = draftCacheGold;
    }

    public void setDraftCacheWood(float draftCacheWood) {
        this.draftCacheWood = draftCacheWood;
    }

    public void setDraftCacheStone(float draftCacheStone) {
        this.draftCacheStone = draftCacheStone;
    }

    public void setDraftCacheIron(float draftCacheIron) {
        this.draftCacheIron = draftCacheIron;
    }

    public void setDraftCacheMana(float draftCacheMana) {
        this.draftCacheMana = draftCacheMana;
    }

    public int getDraftStep(){
        return draftStep;
    }

    public int incrementDraftStep(){
        return draftStep++; //FixMe: This does increment it... right?
    }

    public ArrayList<ResourceCard> getDraftedCards() {
        return draftedCards;
    }

    public void addDraftedCard(ResourceCard resourceCard){
        draftedCards.add(resourceCard);
    }

    public void clearDraftedCards(){
        draftedCards.clear();
    }

    public ArrayList<ActionCard> getActionHand() {
        return actionHand;
    }
    public void addActionCardsToHand(ArrayList<ActionCard> hand){
        actionHand.addAll(hand);
    }
    public void addActionCardToHand(ActionCard actionCard){
        actionHand.add(actionCard);
    }
    //ToDo:Create a method to use/remove a card from user's action hand.

    public String getRace() {
        return race;
    }

    public String getCharacterRace(){return race;}
    public abstract String getRaceDescription(Context context);

    public void cacheAdjustment(ArrayList<ActionCard> usedCards){
        Log.d("UsedCards", "cacheAdjustment: "+usedCards.size());
        for(ActionCard card: usedCards){
            switch(card.getId()){
                case GameConstants.RUINED_SHIPMENT_WOOD_ID:
                    Log.d("Wood", "cacheAdjustment: ");
                    draftCacheWood= draftCacheWood/2;
                    break;
                case GameConstants.RUINED_SHIPMENT_STONE_ID:
                    draftCacheStone= draftCacheStone/2;
                    Log.d("Stone", "cacheAdjustment: "+draftCacheStone);
                    break;
                case GameConstants.RUINED_SHIPMENT_IRON_ID:
                    draftCacheIron = draftCacheIron/2;
                    Log.d("Iron", "cacheAdjustment: "+draftCacheIron);
                    break;
                case GameConstants.RUINED_SHIPMENT_GOLD_ID:
                    draftCacheGold = draftCacheGold/2;
                    Log.d("Gold", "cacheAdjustment: "+draftCacheGold);
                    break;
            }
        }

    }

    public void resolveCachedAmount(){
        addWood((int)draftCacheWood);
        addIron((int)draftCacheIron);
        addStone((int)draftCacheStone);
        addGold((int)draftCacheGold);
        addMana((int)draftCacheMana);
        draftCacheGold=draftCacheWood=draftCacheStone=draftCacheIron=draftCacheMana=0f;
    }
}
