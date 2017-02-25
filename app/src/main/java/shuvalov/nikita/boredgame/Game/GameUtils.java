package shuvalov.nikita.boredgame.Game;

import java.util.ArrayList;
import java.util.Random;

import shuvalov.nikita.boredgame.Cards.ResourceCard;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.Units.Army;

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

    public static boolean canPayCost(BaseCharacterRace player, int[] cost){
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

    //I don't think this is necessary, if the unit dies in battle I can just remove it from the battlefield and not have it return to player's army.
    public static void removeDeadUnits(ArrayList<Army> playerArmy){
        for(int i = 0; i<playerArmy.size();i++){
            if(playerArmy.get(i).getHp()<=0){
                playerArmy.remove(i);
            }
        }
    }



    //Setting a defender for a slot with no attacker might be pointless, unless if I have like adjacency bonuses or something.
    public static String simulateBattle(Army[] battleState, int attackPos, int defensePos){
        Random rng = new Random();
        String outcomeText;
        Army attackingUnit = battleState[attackPos];
        Army defendingUnit = battleState[defensePos];
        if(attackingUnit!=null && defendingUnit!=null){
            int attack = rng.nextInt(attackingUnit.getAttack()-1)+1;
            int defense = rng.nextInt(defendingUnit.getDefense()-1)+1;
            if(attack==defense){
                outcomeText= String.format("Both units rolled a %s. \nBattle ends in a tie.\nNo one takes damage.", attack);
            }else if (attack>defense){
                int damage = attack-defense;
                String defenderName = defendingUnit.getName();
                outcomeText= String.format("The attacking %s rolled a %s. \nThe defending %s rolled a %s. \nThe defending %s took %s damage.",attackingUnit.getName(),attack, defenderName, defense, defenderName, damage);
                GameStateManager gameStateManager = GameStateManager.getInstance();
                if(gameStateManager.getBattleState()[defensePos].takeDamage(damage)){//Units that don't survive are removed from the battlefield, and not returned to the player's army.
                    gameStateManager.getBattleState()[defensePos]=null;
                }else{
                    //Returns surviving units to players army.
                    gameStateManager.getPlayer(0).addUnitToArmy(defendingUnit);//ToDo: Hard-coded for debugging, only returning defending unit to player, not attacking unit to computer.

                }
            }else{
                int damage = defense-attack;
                String attackerName = attackingUnit.getName();
                outcomeText= String.format("The attacking %s rolled a %s. \nThe defending %s rolled a %s. \nThe attacking %s took %s damage.", attackerName,attack, defendingUnit.getName(), defense, attackerName, damage);
                GameStateManager gameStateManager = GameStateManager.getInstance();
                if(gameStateManager.getBattleState()[attackPos].takeDamage(damage)){//Units that don't survive are removed from the battlefield, and not returned to the player's army.
                    gameStateManager.getBattleState()[attackPos]=null;
                }else{
                    //Returns surviving units to players army.
                    gameStateManager.getPlayer(0).addUnitToArmy(defendingUnit);//ToDo: Hard-coded for debugging, only returning defending unit to player, not attacking unit to computer.
                }
            }
        }else if (attackingUnit!=null){
            int damage = rng.nextInt(attackingUnit.getAttack()-1)+1;
            outcomeText = String.format("Attacking %s rolled a %s. \nNo defender available, attacked structure takes %s damage", attackingUnit.getName(),damage,damage);
            //ToDo: Apply damage to structure.
        }else{
            outcomeText = "Nothing to see here folks.";
        }
        return outcomeText;
    }


}
