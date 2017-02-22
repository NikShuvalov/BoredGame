package shuvalov.nikita.boredgame.Draft;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import shuvalov.nikita.boredgame.Cards.ResourceCard;
import shuvalov.nikita.boredgame.Game.HandDisplayRecyclerAdapter;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.R;


public class DraftFragment extends Fragment implements View.OnClickListener {
    private Button mStartDraft, mFinishDraft;
    private RecyclerView mDraftView, mPickedView;
    private DraftRecyclerAdapter mDraftRecyclerAdapter;
    BeginDraftResolveStepListener mListener;


    public DraftFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DraftFragment newInstance(BeginDraftResolveStepListener listener) {
        DraftFragment fragment = new DraftFragment();
        fragment.mListener = listener;
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_draft,container,false);
        mStartDraft = (Button)view.findViewById(R.id.start_draft_opt);
        mFinishDraft = (Button)view.findViewById(R.id.redeem_cards_opt);
        mDraftView = (RecyclerView) view.findViewById(R.id.draft_recycler);
        mPickedView = (RecyclerView)view.findViewById(R.id.drafted_cards_recycler);


        mDraftRecyclerAdapter = new DraftRecyclerAdapter(new ArrayList<ResourceCard>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager draftedManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);

        mDraftView.setAdapter(mDraftRecyclerAdapter);
        mDraftView.setLayoutManager(linearLayoutManager);

        GameStateManager.getInstance().setDraftedAdapter(new HandDisplayRecyclerAdapter(GameStateManager.getInstance().getPlayer(0).getDraftedCards()));
        mPickedView.setLayoutManager(draftedManager);
        mPickedView.setAdapter(GameStateManager.getInstance().getDraftedAdapter());

        mStartDraft.setOnClickListener(this);
        mFinishDraft.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.start_draft_opt:
                DraftManager.getInstance().resetDrafting();
                int numOfPlayers = GameStateManager.getInstance().getPlayerList().size();
                for(int i = 0; i< numOfPlayers; i++){
                    DraftManager.getInstance().addDraftPack(ResourceCard.createPack(GameConstants.DEFAULT_PACK_SIZE,GameConstants.DEFAULT_WEALTHY_DRAFT,GameConstants.DEFAULT_RICH_DRAFT));
                }
                mDraftRecyclerAdapter.replaceDraftCards(DraftManager.getInstance().getPack(0));
                break;
            case R.id.redeem_cards_opt:
                mListener.beginDraftResolveStep();
                break;
        }
    }

    public interface BeginDraftResolveStepListener{
        public void beginDraftResolveStep();
    }
}
