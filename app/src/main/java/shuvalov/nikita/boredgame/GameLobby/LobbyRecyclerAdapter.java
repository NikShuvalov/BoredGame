package shuvalov.nikita.boredgame.GameLobby;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import shuvalov.nikita.boredgame.Game.GameActivity;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.MainActivity;
import shuvalov.nikita.boredgame.Players.Human;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/7/17.
 */

public class LobbyRecyclerAdapter extends RecyclerView.Adapter<LobbyViewHolder> {
    ArrayList<String> mLobbyList;

    public LobbyRecyclerAdapter(ArrayList<String> lobbyList){
        mLobbyList = lobbyList;
    }

    @Override
    public LobbyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LobbyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_lobby,null));
    }

    @Override
    public void onBindViewHolder(final LobbyViewHolder holder, final int position) {
        final String roomKey = mLobbyList.get(position);
        holder.mLobbyText.setText(roomKey);
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.mContainer.getContext(), GameActivity.class);
                intent.putExtra(GameConstants.ROOM_KEY_EXTRA, roomKey);
                intent.putExtra(GameConstants.PLAYER, GameConstants.PLAYER_TWO);
                String name = holder.mContainer.getContext().getSharedPreferences(MainActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE).getString(MainActivity.USER_NAME_PREF, "The Cock Gobbler");
                holder.mContainer.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLobbyList.size();
    }
}
