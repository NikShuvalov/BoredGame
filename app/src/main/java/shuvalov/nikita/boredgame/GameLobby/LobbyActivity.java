package shuvalov.nikita.boredgame.GameLobby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import shuvalov.nikita.boredgame.FirebaseDatabaseUtils;
import shuvalov.nikita.boredgame.R;

public class LobbyActivity extends AppCompatActivity {
    public LobbyRecyclerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        LobbyServerManager.getInstance().clearServerList();
        setDatabaseListener();
        recyclerLogic();
    }

    public void recyclerLogic(){
        RecyclerView lobbyRecycler = (RecyclerView) findViewById(R.id.lobby_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mAdapter = new LobbyRecyclerAdapter(LobbyServerManager.getInstance().getServerList());
        lobbyRecycler.setLayoutManager(linearLayoutManager);
        lobbyRecycler.setAdapter(mAdapter);
    }

    public void setDatabaseListener(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseDatabaseUtils.getServersReference(firebaseDatabase).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while(iterator.hasNext()){
                    LobbyServerManager.getInstance().addServer(iterator.next().getKey());
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
