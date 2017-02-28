package shuvalov.nikita.boredgame.Buildings.Generic;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.Cards.ActionCard;
import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/27/17.
 */

public class SpyDen extends Building {
    public static final int SPYDEN_HP = 6;
    public static final int[] SPYDEN_COST = {2,2,2,4,0};


    public SpyDen() {
        super(SPYDEN_HP, false, 0, GameConstants.SPYDEN_ID, 0);
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.spy_den_name);
    }

    @Override
    public String getDescription(Context context) {
        return String.format(context.getString(R.string.spy_den_description),String.valueOf((1+getCounters()*2)+2));
    }

    @Override
    public boolean useAbility(BaseCharacterRace player, Context context) {
        int goldCost = 1+2*getCounters()+2; //Since a counter is added before paying, the player needs to see if they can pay for current counters + 2 gold(for the additional counter)
        if(player.getGold()>goldCost){//If player can afford it, add a counter and resolve the ability.
            addCounter();
            player.spendGold(goldCost);
            Toast.makeText(context, "Bought an action card", Toast.LENGTH_SHORT).show();
            ActionCard topCard= null;
            int i =0; //Just to prevent an infinite loop.
            while(topCard==null && i<3){
                i++;
                topCard = GameStateManager.getInstance().getTopActionCard();
                if(topCard==null){//Check if deck is empty.
                    Log.d("SpyDen", "useAbility: Reshuffle deck");
                    GameStateManager.getInstance().setActionCardDeck(ActionCard.createDeck(context)); //IF Empty deck repopulate it
                }else{
                    player.addActionCardToHand(topCard);
                }
                if(i==3){//If this iterates more than once then there's a problem.
                    Log.e("SpyDen.Class", "useAbility: Infinite loop" );
                    Toast.makeText(context, "All cards in use", Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int[] getCost() {
        return SPYDEN_COST;
    }
}
