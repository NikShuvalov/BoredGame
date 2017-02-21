package shuvalov.nikita.boredgame;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class DebugActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;
    private FrameLayout mFragmentContainer;
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
        mFragmentContainer = (FrameLayout)findViewById(R.id.fragment_container);
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, CharacterFragment.newInstance()).commit();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.draft_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,DraftFragment.newInstance()).commit();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.town_option:
                Toast.makeText(this, "Coming soon town information", Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
                break;
        }
        return false;
    }
}
