package shuvalov.nikita.boredgame.Cards;

import android.content.Context;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/22/17.
 */

public class ActionCard {
    private String mName, mDescription;
    private int mId, mManaCost;

    public ActionCard(String name, int id, String description, int manaCost){
        mName = name;
        mId = id;
        mDescription = description;
        mManaCost = manaCost;
    }

    public String getName() {
        return mName;
    }

    public int getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getManaCost() {
        return mManaCost;
    }

    /**
     * Creates a deck with a single copy of each card.
     * @param context is needed to get stringArray resource values
     * @return
     */
    public static ArrayList<ActionCard> createDeck(Context context){
        ArrayList<ActionCard> actionDeck = new ArrayList<>();
        String[] cardNames = context.getResources().getStringArray(R.array.action_card_names);
        String[] cardDescriptions = context.getResources().getStringArray(R.array.action_card_descriptions);
        int[] cardManaCosts = context.getResources().getIntArray(R.array.action_card_mana_costs);

        for (int i = 0; i<cardNames.length;i++){
            actionDeck.add(new ActionCard(cardNames[i], 2000+i, cardDescriptions[i], cardManaCosts[i]));
        }
        return actionDeck;
    }
}
