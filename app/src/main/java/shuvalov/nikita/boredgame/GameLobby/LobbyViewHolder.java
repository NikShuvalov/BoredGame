package shuvalov.nikita.boredgame.GameLobby;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/7/17.
 */

public class LobbyViewHolder extends RecyclerView.ViewHolder{
    public CardView mContainer;
    public TextView mLobbyText;

    public LobbyViewHolder(View itemView) {
        super(itemView);
        mContainer = (CardView)itemView.findViewById(R.id.lobby_card);
        mLobbyText = (TextView)itemView.findViewById(R.id.server_room_text);
    }
}
