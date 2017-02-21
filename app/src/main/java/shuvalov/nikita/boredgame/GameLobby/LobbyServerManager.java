package shuvalov.nikita.boredgame.GameLobby;

import java.util.ArrayList;

/**
 * Created by NikitaShuvalov on 2/7/17.
 */

public class LobbyServerManager {
    private ArrayList<String> mServerList;
    private static LobbyServerManager sLobbyServerManager;

    private LobbyServerManager(){
        mServerList = new ArrayList<>();
    }

    public static LobbyServerManager getInstance() {
        if(sLobbyServerManager==null){
            sLobbyServerManager = new LobbyServerManager();
        }
        return sLobbyServerManager;
    }

    public ArrayList<String> getServerList(){
        return mServerList;
    }

    public void addServer(String server){
        mServerList.add(server);
    }
    public void clearServerList(){
        mServerList.clear();
    }

    public int getLobbySize(){
        return mServerList.size();
    }
}
