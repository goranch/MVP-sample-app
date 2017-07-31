package com.goranch.publicapis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.goranch.publicapis.ui.home.HomeFragment;
import com.goranch.publicapis.ui.home.HomeView;
import com.goranch.publicapis.ui.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeView {

    private final String TAG = getClass().getSimpleName();
    @BindView(R.id.toolbar_title)
    public TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            Utils.INSTANCE.openFragment(this, HomeFragment.newInstance(), false);
        }
    }

}
