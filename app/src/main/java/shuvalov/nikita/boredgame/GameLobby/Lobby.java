package shuvalov.nikita.boredgame.GameLobby;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.Cards.ResourceCard;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;

/**
 * Created by NikitaShuvalov on 2/7/17.
 */

public class Lobby {
    private ArrayList<BaseCharacterRace> players;
    private int numOfPlayers;
    private ArrayList<ArrayList<ResourceCard>> resourcePacks;
    private String lobbyid;

    public Lobby(){}

    public Lobby(ArrayList<BaseCharacterRace> players, int numOfPlayers, ArrayList<ArrayList<ResourceCard>> resourcePacks, String lobbyid){
        this.players = players;
        this.numOfPlayers = numOfPlayers;
        this.resourcePacks = resourcePacks;
        this.lobbyid = lobbyid;
    }

    public Lobby(int numOfPlayers, String lobbyid, BaseCharacterRace player){
        this.numOfPlayers = numOfPlayers;
        this.lobbyid = lobbyid;
        this.resourcePacks = new ArrayList<>();
        this.players = new ArrayList<>();
        this.players.add(player);

        for(int i =0; i<numOfPlayers; i++){
            resourcePacks.add(ResourceCard.createPack(GameConstants.DEFAULT_PACK_SIZE,GameConstants.DEFAULT_WEALTHY_DRAFT,GameConstants.DEFAULT_RICH_DRAFT));
        }
    }

    public ArrayList<BaseCharacterRace> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<BaseCharacterRace> players) {
        this.players = players;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public ArrayList<ArrayList<ResourceCard>> getResourcePacks() {
        return resourcePacks;
    }

    public void setResourcePacks(ArrayList<ArrayList<ResourceCard>> resourcePacks) {
        this.resourcePacks = resourcePacks;
    }

    public String getLobbyid() {
        return this.lobbyid;
    }

    public void setLobbyid(String lobbyid) {
        this.lobbyid = lobbyid;
    }

    public void addPlayer(BaseCharacterRace player){
        players.add(player);
    }
}
