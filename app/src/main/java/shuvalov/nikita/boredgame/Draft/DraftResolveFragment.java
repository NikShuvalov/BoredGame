package shuvalov.nikita.boredgame.Draft;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import shuvalov.nikita.boredgame.Cards.ActionHandRecyclerAdapter;
import shuvalov.nikita.boredgame.Game.HandDisplayRecyclerAdapter;
import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.R;


public class DraftResolveFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mDraftHandRecycler, mActionHandRecycler;
    private Button mNoCardsButt;


    public DraftResolveFragment() {
    }

    public static DraftResolveFragment newInstance() {
        return new DraftResolveFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_draft_resolve, container, false);
        mDraftHandRecycler = (RecyclerView)view.findViewById(R.id.draft_hand_recycler);
        mActionHandRecycler = (RecyclerView)view.findViewById(R.id.action_hand_recycler);
        mNoCardsButt = (Button)view.findViewById(R.id.no_cards_butt);
        mNoCardsButt.setOnClickListener(this);

        recyclerLogic();
        return view;
    }

    public void recyclerLogic(){
        HandDisplayRecyclerAdapter draftHandAdapter = new HandDisplayRecyclerAdapter(GameStateManager.getInstance().getPlayer(0).getDraftedCards());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mDraftHandRecycler.setAdapter(draftHandAdapter);
        mDraftHandRecycler.setLayoutManager(linearLayoutManager);

        ActionHandRecyclerAdapter actionHandAdapter  = new ActionHandRecyclerAdapter(GameStateManager.getInstance().getPlayer(0).getActionHand());
        LinearLayoutManager actionLayout = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mActionHandRecycler.setLayoutManager(actionLayout);
        mActionHandRecycler.setAdapter(actionHandAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.no_cards_butt:
                GameStateManager gameStateManager  = GameStateManager.getInstance();
                gameStateManager.redeemDraftedCards(0);
                gameStateManager.getPlayer(0).clearDraftedCards();
                gameStateManager.getDraftedAdapter().notifyDataSetChanged();
                break;
        }
        //ToDo: Move to next phase from here.
    }
}
