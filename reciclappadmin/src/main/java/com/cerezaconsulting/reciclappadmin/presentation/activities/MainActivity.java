package com.cerezaconsulting.reciclappadmin.presentation.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.cerezaconsulting.reciclappadmin.R;
import com.cerezaconsulting.reciclappadmin.core.BaseActivity;
import com.cerezaconsulting.reciclappadmin.data.repositories.local.SessionManager;
import com.cerezaconsulting.reciclappadmin.presentation.fragments.MainFragment;
import com.cerezaconsulting.reciclappadmin.presentation.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.body)
    FrameLayout body;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle("Reciclapp Gestión");
        ab.setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(navigationView);

        sessionManager = new SessionManager(getApplicationContext());

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,                    /* host Activity */
                drawerLayout,                    /* DrawerLayout object */
                toolbar,
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        );
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.body);
        if(fragment==null){
            fragment=MainFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.body);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position

        switch (menuItem.getItemId()) {
            case R.id.action_recycle:
                next(this,null,RegisterDeliveryActivity.class,false);
                break;
            case R.id.action_account:
                next(this,null,AccountActivity.class,false);
                break;
            default:
                break;

        }

        // Highlight the selected item, update the title, and close the drawer
        //menuItem.setChecked(true);
        //setTitle(menuItem.getTitle());
        //drawerLayout.closeDrawers();
        //this.finish();
    }
}
