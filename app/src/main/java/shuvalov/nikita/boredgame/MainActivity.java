package shuvalov.nikita.boredgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import shuvalov.nikita.boredgame.Cards.ActionCard;
import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.Players.Human;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mLobbyButt, mNewButt, mNameButt;
//    private EditText mNameEntry;

    public static final String SHARED_PREFERENCES = "Bored Preferences";
    public static final String USER_NAME_PREF = "User name";
    public static final String FIREBASE_SERVER_REF = "Servers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    public void findViews(){
        mLobbyButt = (Button)findViewById(R.id.open_lobby_butt);
//        mNewButt = (Button)findViewById(R.id.create_new_butt);
//        mNameButt = (Button)findViewById(R.id.enter_name_butt);
//        mNameEntry = (EditText)findViewById(R.id.your_name_entry);

//        mNameEntry.setText(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE).getString(USER_NAME_PREF,""));

        mLobbyButt.setOnClickListener(this);
//        mNewButt.setOnClickListener(this);
//        mNameButt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
//            case R.id.create_new_butt:
//                intent = new Intent(MainActivity.this, GameActivity.class);
//                startActivity(intent);
//                break;
            case R.id.open_lobby_butt:
                intent = new Intent(MainActivity.this, DebugActivity.class);
                setUpDebug();
                startActivity(intent);
                break;
//            case R.id.enter_name_butt:
//                String userName = mNameEntry.getText().toString();
//                getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE).edit().putString(USER_NAME_PREF,userName).apply();
//                Toast.makeText(this, "Hello "+userName, Toast.LENGTH_LONG).show();
//                break;
        }
    }
    public void setUpDebug(){
        Human player = new Human("Player", 0);
        Human computer = new Human ("Computer", 1);

        player.addActionCardsToHand(ActionCard.createDeck(this));
        computer.addActionCardsToHand(ActionCard.createDeck(this));

        GameStateManager.getInstance().setPlayers(player,computer);

    }
}
