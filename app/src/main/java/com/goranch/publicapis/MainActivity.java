package com.goranch.publicapis;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.goranch.publicapis.api.ApiComponent;
import com.goranch.publicapis.di.ComponentProvider;
import com.goranch.publicapis.ui.home.DaggerHomeComponent;
import com.goranch.publicapis.ui.home.HomeFragment;
import com.goranch.publicapis.ui.home.HomeModule;
import com.goranch.publicapis.ui.home.HomeView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeView {

    private final String TAG = getClass().getSimpleName();
    @Bind(R.id.toolbar_title)
    public TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private HomeFragment homeFragment;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        ApiComponent apiComponent = ((ComponentProvider<ApiComponent>) getApplicationContext()).getComponent();
        DaggerHomeComponent.builder()
            .apiComponent(apiComponent)
            .homeModule(new HomeModule(this))
            .build().inject(this);

        setContentView(R.layout.activity_main);

        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        homeFragment = HomeFragment.newInstance();
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_holder, homeFragment);
        t.commit();
    }

}
