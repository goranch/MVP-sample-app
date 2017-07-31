package com.goranch.publicapis.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goranch.publicapis.R;
import com.goranch.publicapis.ui.food.FoodActivity;
import com.goranch.publicapis.ui.shazam.MusicFragment;
import com.goranch.publicapis.ui.util.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment implements HomeView {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @OnClick(R.id.btn_shazam)
    void clickMusic() {
        MusicFragment musicFragment = MusicFragment.newInstance();
        replaceFragment(musicFragment);
    }

    @OnClick(R.id.btn_food)
    void clickFood() {
        Intent i = new Intent(getActivity(), FoodActivity.class);
        startActivity(i);
    }

    private void replaceFragment(Fragment fragment) {
        Utils.INSTANCE.openFragment(getActivity(), fragment, true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, v);

        return v;
    }
}
