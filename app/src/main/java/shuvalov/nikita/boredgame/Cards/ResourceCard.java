package shuvalov.nikita.boredgame.Cards;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by NikitaShuvalov on 2/7/17.
 */

public class ResourceCard {
    public static final int WEALTHY = 3;
    public static final int RICH = 2;
    public static final int POOR = 1;

    public static final String MANA = "mana";
    public static final String WOOD = "wood";
    public static final String STONE = "stone";
    public static final String IRON = "iron";
    public static final String GOLD = "gold";

    private String mType;
    private int mRank;

    public ResourceCard(int rank, String type){
        mType = type;
        mRank = rank;
    }

    public static ArrayList<ResourceCard> createPack(int packSize, int wealthies, int richies){
        ArrayList<ResourceCard> resourcePack = new ArrayList<>();
        Random rng = new Random();
        String[] types = new String[]{MANA, WOOD, STONE, IRON, GOLD};
        for(int i =0; i<wealthies; i++ ){
            int chosenType =  rng.nextInt(5);
            resourcePack.add(new ResourceCard(WEALTHY,types[chosenType]));
        }
        for (int i = 0; i<richies;i++){
            int chosenType = rng.nextInt(5);
            resourcePack.add(new ResourceCard(RICH,types[chosenType]));
        }
        while(resourcePack.size()<packSize){
            int chosenType = rng.nextInt(5);
            resourcePack.add(new ResourceCard(POOR, types[chosenType]));
        }
        return resourcePack;
    }

}
