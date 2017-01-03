package com.goranch.publicapis.ui.food.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.goranch.publicapis.R;
import com.goranch.publicapis.api.model.food.Recipe;
import com.goranch.publicapis.ui.webview.WebShazamFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DetailsFragment extends Fragment implements View.OnClickListener {

    public static final String URL = "url";
    @Bind(R.id.iv_recipe_img)
    SimpleDraweeView recipeImage;
    @Bind(R.id.lv_igredients)
    ListView ingredients;
    @Bind(R.id.tv_view_instrouctions)
    TextView instructions;
    @Bind(R.id.tv_view_original)
    TextView viewOriginal;
    @Bind(R.id.tv_publichers_name)
    TextView publishersName;
    @Bind(R.id.tv_social_rank)
    TextView socialRank;


    private Recipe recipeData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Bundle b = getArguments();
            recipeData = (Recipe) b.getSerializable(FoodFragment.RECIPE_ITEM);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_food, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(recipeData.getTitle());

        recipeImage.setImageURI(Uri.parse(recipeData.getImageUrl()));
        publishersName.setText(recipeData.getPublisher());
        socialRank.setText(String.valueOf(Math.round(Double.parseDouble(recipeData.getSocialRank().toString()))));

        instructions.setOnClickListener(this);
        viewOriginal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_view_instrouctions:
                openWebView(recipeData.getF2fUrl());
                break;
            case R.id.tv_view_original:
                openWebView(recipeData.getPublisherUrl());
                break;
            default:
                break;
        }

    }

    private void openWebView(String url) {
        WebShazamFragment f = new WebShazamFragment();
        Bundle b = new Bundle();
        b.putString(URL, url);
        f.setArguments(b);
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_holder, f);
        t.addToBackStack(null);
        t.commit();
    }
}
