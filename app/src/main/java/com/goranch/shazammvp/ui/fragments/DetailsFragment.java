package com.goranch.shazammvp.ui.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.goranch.shazammvp.MainActivity;
import com.goranch.shazammvp.R;
import com.goranch.shazammvp.api.model.Item;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Goran Ch on 16/04/16.
 */
public class DetailsFragment extends Fragment {
    public static final String URL = "more_details_url";
    @Bind(R.id.iv_art_img)
    SimpleDraweeView trackImage;

    @Bind(R.id.detail_title)
    TextView title;
    @Bind(R.id.detail_subtitle)
    TextView subtitle;

    @Bind(R.id.play)
    Button play;

    @OnClick(R.id.play)
    void click() {

        WebFragment f = new WebFragment();
        Bundle b = new Bundle();
        b.putString(URL, itemData.getUrl());
        f.setArguments(b);
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_holder, f);
        t.addToBackStack(null);
        t.commit();
    }

    private Item itemData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Bundle b = getArguments();
            itemData = (Item) b.getSerializable(MainActivityFragment.TRACK_ITEM);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {

        ((MainActivity) getActivity()).toolbarTitle.setText(itemData.getHeading().getTitle());

        title.setText(itemData.getHeading().getTitle());
        subtitle.setText(itemData.getHeading().getSubtitle());

        String url = itemData.getImages().getDefault();
        if (url == null)
            url = "";
        trackImage.setImageURI(Uri.parse(url));
    }
}
