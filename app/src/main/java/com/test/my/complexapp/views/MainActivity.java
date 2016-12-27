package com.test.my.complexapp.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.test.my.complexapp.R;
import com.test.my.complexapp.adapters.ListAdapterDrawer;
import com.test.my.complexapp.presenters.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.drawer)
    ListView drawer;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initToolbar();

        System.out.println("MainActivity onCreate");

        ListAdapterDrawer listAdapterDrawer = new ListAdapterDrawer(this, new String[]{"Cache", "CacheDB"});
        drawer.setAdapter(listAdapterDrawer);
        drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = CacheFragment.instance();
                        break;
                    case 1:
                        fragment = CacheDBFragment.instance();
                        break;
                }
                if (fragment != null) {
                    MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, CacheFragment.instance()).commit();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("MainActivity onDestroy");
    }

    @Override
    public void onStart(){
        super.onStart();
        System.out.println("MainActivity onStart");
    }

    @Override
    public void onStop(){
        super.onStop();
        System.out.println("MainActivity onStop");
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("MainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("MainActivity onPause");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Context getContext() {
        return this;
    }

    protected void initToolbar() {
        //noinspection ConstantConditions
        getSupportActionBar().setHomeAsUpIndicator(toolbarMenuResId());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    protected int toolbarMenuResId() {
        return R.drawable.menu_drawer;
    }
}
