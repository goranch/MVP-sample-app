package com.goranch.shazammvp;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.goranch.shazammvp.ui.home.MainActivityFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    @Bind(R.id.toolbar_title)
    public TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private MainActivityFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            mainFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
        } else {
            mainFragment = MainActivityFragment.newInstance();
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.replace(R.id.fragment_holder, mainFragment);
            t.addToBackStack(null);
            t.commit();
        }

    }
}
