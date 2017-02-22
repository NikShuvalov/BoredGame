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
import android.widget.TextView;
import android.widget.Toast;

import shuvalov.nikita.boredgame.Cards.ActionHandRecyclerAdapter;
import shuvalov.nikita.boredgame.Game.GameUtils;
import shuvalov.nikita.boredgame.Game.HandDisplayRecyclerAdapter;
import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.R;


public class DraftResolveFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mDraftHandRecycler, mActionHandRecycler;
    private Button mNoCardsButt, mSelectedCardsButt;
    private TextView mCurrentResText;
    DraftResolveListener mDraftResolveListener;
    ActionHandRecyclerAdapter mActionHandAdapter;
//    private TextView mAdjustedResText;


    public DraftResolveFragment() {
    }

    public static DraftResolveFragment newInstance(DraftResolveListener draftResolveListener) {
        DraftResolveFragment fragment = new DraftResolveFragment();
        fragment.mDraftResolveListener = draftResolveListener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameStateManager.getInstance().redeemDraftedCards(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_draft_resolve, container, false);
        mDraftHandRecycler = (RecyclerView)view.findViewById(R.id.draft_hand_recycler);
        mActionHandRecycler = (RecyclerView)view.findViewById(R.id.action_hand_recycler);
        mNoCardsButt = (Button)view.findViewById(R.id.no_cards_butt);
        mSelectedCardsButt = (Button)view.findViewById(R.id.pick_cards_butt);

        mSelectedCardsButt.setOnClickListener(this);
        mNoCardsButt.setOnClickListener(this);

        mCurrentResText =(TextView)view.findViewById(R.id.current_resource_text);
//        mAdjustedResText = (TextView)view.findViewById(R.id.adjusted_resource_text);//ToDo: Remove me if more hassle than worth

        setResourceText();
        recyclerLogic();
        return view;
    }

    public void setResourceText(){
        mCurrentResText.setText(GameUtils.currentResourcesDrafted(GameStateManager.getInstance().getPlayer(0)));
    }
    public void recyclerLogic(){
        HandDisplayRecyclerAdapter draftHandAdapter = new HandDisplayRecyclerAdapter(GameStateManager.getInstance().getPlayer(0).getDraftedCards());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mDraftHandRecycler.setAdapter(draftHandAdapter);
        mDraftHandRecycler.setLayoutManager(linearLayoutManager);

        mActionHandAdapter  = new ActionHandRecyclerAdapter(GameStateManager.getInstance().getPlayer(0).getActionHand());
        LinearLayoutManager actionLayout = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mActionHandRecycler.setLayoutManager(actionLayout);
        mActionHandRecycler.setAdapter(mActionHandAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.no_cards_butt:
                mDraftResolveListener.draftResolved();
                break;
            case R.id.pick_cards_butt:
                //ToDo: Figure out a better way to make it globally effect players when necessary.
                //A hot fix just to test single player.
                //ToDo: Add conditional to check if user has enough mana to play card(s);
                GameStateManager.getInstance().getPlayer(0).cacheAdjustment(mActionHandAdapter.getSelectedCards());
                mDraftResolveListener.draftResolved();
                break;
        }
        //ToDo: Move to next phase from here.
    }

    public interface DraftResolveListener{
        void draftResolved();
    }
}
