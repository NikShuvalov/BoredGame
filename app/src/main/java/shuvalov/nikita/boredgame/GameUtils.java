package shuvalov.nikita.boredgame;

import shuvalov.nikita.boredgame.Players.BaseCharacterRace;

/**
 * Created by NikitaShuvalov on 2/20/17.
 */

public class GameUtils {

    public static String getCostString(int[] cost){
        StringBuilder costString = new StringBuilder();
        for(int i =0; i<cost.length;i++){
            if(cost[i]>0){
                switch (i){
                    case 0:
                        costString.append(String.format("%s wood\n", cost[i]));
                        break;
                    case 1:
                        costString.append(String.format("%s iron\n", cost[i]));
                        break;
                    case 2:
                        costString.append(String.format("%s stone\n", cost[i]));
                        break;
                    case 3:
                        costString.append(String.format("%s gold\n", cost[i]));
                        break;
                    case 4:
                        costString.append(String.format("%s mana\n", cost[i]));
                        break;
                }
            }
        }
        return costString.toString();
    }

    public static boolean payForLevelUp(BaseCharacterRace player, int[] cost){
        if(player.getWood()< cost[0] || player.getIron()<cost[1] || player.getStone() < cost[2] || player.getGold()< cost[3] || player.getMana()<cost[4]){
            return false;
        }
        player.spendWood(cost[0]);
        player.spendIron(cost[1]);
        player.spendStone(cost[2]);
        player.spendGold(cost[3]);
        player.spendMana(cost[4]);
        return true;



    }
}
