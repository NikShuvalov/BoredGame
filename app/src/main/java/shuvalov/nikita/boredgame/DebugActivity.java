package shuvalov.nikita.boredgame;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import shuvalov.nikita.boredgame.Buildings.Town.TownFragment;
import shuvalov.nikita.boredgame.Draft.DraftFragment;
import shuvalov.nikita.boredgame.Draft.DraftResolveFragment;
import shuvalov.nikita.boredgame.Game.GameStateManager;
import shuvalov.nikita.boredgame.Game.GameUtils;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.Players.CharacterFragment;

public class DebugActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DraftFragment.BeginDraftResolveStepListener, DraftResolveFragment.DraftResolveListener {
    private Toolbar mToolbar;
    public TextView mStockpileText;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        findViews();

    }

    public void findViews(){
        mToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        mStockpileText = (TextView)mToolbar.findViewById(R.id.stockpile_text);
        GameStateManager.getInstance().assignResourceText(mStockpileText);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mStockpileText.setText(GameUtils.currentResourceStockpile(GameStateManager.getInstance().getPlayer(0)));

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView)findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_open, R.string.navigation_closed);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.character_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, CharacterFragment.newInstance()).commit();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.draft_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,DraftFragment.newInstance(this)).commit();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.town_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, TownFragment.newInstance()).commit();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.battle_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, BattleFragment.newInstance()).commit();
                mDrawerLayout.closeDrawers();
                break;
        }
        return false;
    }

    @Override
    public void beginDraftResolveStep() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, DraftResolveFragment.newInstance(this)).commit();
    }

    @Override
    public void draftResolved() {
        GameStateManager gameStateManager = GameStateManager.getInstance();
        BaseCharacterRace player = gameStateManager.getPlayer(0);
        player.resolveCachedAmount();
        player.clearDraftedCards();
        updateResourceDisplay();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, CharacterFragment.newInstance()).commit();
    }


    public void updateResourceDisplay(){
        mStockpileText.setText(GameUtils.currentResourceStockpile(GameStateManager.getInstance().getPlayer(0)));
    }

}
