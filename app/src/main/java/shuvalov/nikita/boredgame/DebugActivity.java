package shuvalov.nikita.boredgame;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import shuvalov.nikita.boredgame.Buildings.Town.TownFragment;
import shuvalov.nikita.boredgame.Draft.DraftFragment;
import shuvalov.nikita.boredgame.Draft.DraftResolveFragment;
import shuvalov.nikita.boredgame.Players.CharacterFragment;

public class DebugActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DraftFragment.BeginDraftResolveStepListener {
    private Toolbar mToolbar;
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
        setSupportActionBar(mToolbar);

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
                getSupportActionBar().setTitle("Character");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, CharacterFragment.newInstance()).commit();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.draft_option:
                getSupportActionBar().setTitle("Draft");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,DraftFragment.newInstance(this)).commit();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.town_option:
                getSupportActionBar().setTitle("Town");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, TownFragment.newInstance()).commit();
                mDrawerLayout.closeDrawers();
                break;
        }
        return false;
    }

    @Override
    public void beginDraftResolveStep() {
        getSupportActionBar().setTitle("Draft Resolve");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, DraftResolveFragment.newInstance()).commit();
    }
}
