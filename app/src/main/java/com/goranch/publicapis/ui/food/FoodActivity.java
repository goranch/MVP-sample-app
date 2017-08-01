package com.goranch.publicapis.ui.food;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.goranch.publicapis.R;
import com.goranch.publicapis.ui.food.fragment.FoodFragment;
import com.goranch.publicapis.ui.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FoodActivity extends AppCompatActivity implements FoodActivityView {
    private final String TAG = getClass().getSimpleName();
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_coordinator);
        ButterKnife.bind(this);

        setTitle();

        if (savedInstanceState == null) {
            Utils.INSTANCE.openFragment(this, FoodFragment.Companion.newInstance(), false);
        }
    }

    @Override
    public void setTitle() {
        this.setTitle(R.string.food_search);
        setSupportActionBar(toolbar);
    }
}
