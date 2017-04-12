package com.goranch.publicapis.ui.food.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.goranch.publicapis.R;
import com.goranch.publicapis.api.ApiComponent;
import com.goranch.publicapis.api.model.food.Recipe;
import com.goranch.publicapis.di.ComponentProvider;
import com.goranch.publicapis.ui.food.details.DaggerDetailsFoodComponent;
import com.goranch.publicapis.ui.food.details.DetailRecipeView;
import com.goranch.publicapis.ui.food.details.DetailsFoodModule;
import com.goranch.publicapis.ui.food.details.RecipeDetailPresenter;
import com.goranch.publicapis.ui.webview.WebShazamFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.support.design.widget.Snackbar.LENGTH_LONG;


public class DetailsFragment extends Fragment implements DetailRecipeView, View.OnClickListener {

    public static final String URL = "details_food_url";
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

    @Inject
    RecipeDetailPresenter presenter;

    @Inject
    DetailRecipeView mDetailRecipeView;

    private Recipe recipeData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApiComponent apiComponent = ((ComponentProvider<ApiComponent>) getActivity().getApplicationContext()).getComponent();
        DaggerDetailsFoodComponent.builder()
                .apiComponent(apiComponent)
                .detailsFoodModule(new DetailsFoodModule(this))
                .build().inject(this);

        //TODO make another request here to get the ingredients with getRecipe()

        if (getArguments() != null) {
            Bundle b = getArguments();
            recipeData = (Recipe) b.getSerializable(FoodFragment.RECIPE_ITEM);
        } else {
            Snackbar.make(getView(), "No Recipe Id", LENGTH_LONG).show();
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

    @Override
    public void openWebView(String url) {
        WebShazamFragment f = new WebShazamFragment();
        Bundle b = new Bundle();
        b.putString(URL, url);
        f.setArguments(b);
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_holder, f);
        t.addToBackStack(null);
        t.commit();
    }

    @Override
    public void onDataUpdated(Recipe data) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}