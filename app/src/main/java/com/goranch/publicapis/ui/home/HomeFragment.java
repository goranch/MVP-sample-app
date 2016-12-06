package com.goranch.publicapis.ui.home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goranch.publicapis.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by goran on 24/09/2016.
 */

public class HomeFragment extends Fragment implements HomeView {

    @Bind(R.id.btn_shazam)
    public TextView shazamButton;

    @Bind(R.id.btn_food)
    public TextView foodButton;


    @OnClick(R.id.btn_shazam)
    void clickShazam() {

    }

    @OnClick(R.id.btn_food)
    void clickFood() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, v);

        return v;
    }
}
