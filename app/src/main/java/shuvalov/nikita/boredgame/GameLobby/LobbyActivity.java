package shuvalov.nikita.boredgame.GameLobby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import shuvalov.nikita.boredgame.R;

public class LobbyActivity extends AppCompatActivity {
    public LobbyRecyclerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        LobbyServerManager.getInstance().clearServerList();
        recyclerLogic();
    }

    public void recyclerLogic(){
        RecyclerView lobbyRecycler = (RecyclerView) findViewById(R.id.lobby_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mAdapter = new LobbyRecyclerAdapter(LobbyServerManager.getInstance().getServerList());
        lobbyRecycler.setLayoutManager(linearLayoutManager);
        lobbyRecycler.setAdapter(mAdapter);
    }

}
