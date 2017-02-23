package shuvalov.nikita.boredgame.Game;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.Cards.ResourceCard;
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

    public static String currentResourcesDrafted(BaseCharacterRace player){
        int wood = (int)player.getDraftCacheWood();
        int iron = (int)player.getDraftCacheIron();
        int gold = (int)player.getDraftCacheGold();
        int stone = (int)player.getDraftCacheStone();
        int mana = (int)player.getDraftCacheMana();
//        ArrayList<ResourceCard> draftedCards = player.getDraftedCards();
//        int wood=0, iron=0, gold=0, stone=0, mana = 0;
//        for(ResourceCard card: draftedCards){
//            String resourceType = card.getResource();
//            switch (resourceType){
//                case ResourceCard.WOOD:
//                    wood+=card.getRank();
//                    break;
//                case ResourceCard.IRON:
//                    iron+=card.getRank();
//                    break;
//                case ResourceCard.GOLD:
//                    gold+=card.getRank();
//                    break;
//                case ResourceCard.STONE:
//                    stone+=card.getRank();
//                    break;
//                case ResourceCard.MANA:
//                    mana+=card.getRank();
//                    break;
//            }
//        }
        return String.format("Drafted: %s wood | %s iron | %s stone | %s gold | %s mana", wood, iron, stone, gold, mana);
    }

    public static String currentResourceStockpile(BaseCharacterRace player){
        int wood = player.getWood();
        int iron = player.getIron();
        int stone = player.getStone();
        int gold = player.getGold();
        int mana = player.getMana();
        return String.format("%s wood | %s iron | %s stone | %s gold | %s mana", wood, iron, stone, gold, mana);
    }
}
