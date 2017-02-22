package shuvalov.nikita.boredgame.Game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.Cards.ResourceCard;
import shuvalov.nikita.boredgame.Draft.DraftManager;
import shuvalov.nikita.boredgame.Draft.DraftRecyclerAdapter;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.GameLobby.Lobby;
import shuvalov.nikita.boredgame.MainActivity;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.Players.Human;
import shuvalov.nikita.boredgame.R;

public class GameActivity extends AppCompatActivity {
    private String mRoomKey, mUsername;
    private BaseCharacterRace mPlayer, mComputer;
    private Lobby mLobby;
    private int mPackSize;
//    private ArrayList<ResourceCard> mDraftPack;
    private ArrayList<ArrayList<ResourceCard>>  mDraftPacks;
    public static final int NUM_OF_PLAYERS_TEST = 2;
    private DraftRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mRoomKey = getIntent().getStringExtra(GameConstants.ROOM_KEY_EXTRA);
        mUsername = getSharedPreferences(MainActivity.SHARED_PREFERENCES,MODE_PRIVATE).getString(MainActivity.USER_NAME_PREF,"The Cock Gobbler!");
        debugSetup();
    }

    public void debugSetup(){
        DraftManager.getInstance().setDraftStep(0);
        mPlayer = new Human(mUsername,0);
        mComputer = new Human("C0mputer", 1);
        GameStateManager.getInstance().setPlayers(mPlayer,mComputer);
        mDraftPacks = new ArrayList<>();
        for(int i = 0; i<NUM_OF_PLAYERS_TEST; i++){
            DraftManager.getInstance().addDraftPack(ResourceCard.createPack(GameConstants.DEFAULT_PACK_SIZE,GameConstants.DEFAULT_WEALTHY_DRAFT,GameConstants.DEFAULT_RICH_DRAFT));
        }

        recyclerLogic();
        draft();
    }

    public void recyclerLogic(){
        mAdapter = new DraftRecyclerAdapter(DraftManager.getInstance().getPack(0));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.draft_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }


    public void draft(){
        if (DraftManager.getInstance().getDraftStep()==0){

        }
    }
}
