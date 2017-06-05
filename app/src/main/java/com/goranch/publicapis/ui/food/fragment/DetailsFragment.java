package com.goranch.publicapis.ui.food.fragment;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.goranch.publicapis.R;
import com.goranch.publicapis.api.ApiComponent;
import com.goranch.publicapis.api.model.food.Recipe;
import com.goranch.publicapis.di.ComponentProvider;
import com.goranch.publicapis.ui.food.FoodDataRepositoryImpl;
import com.goranch.publicapis.ui.food.details.DaggerDetailsFoodComponent;
import com.goranch.publicapis.ui.food.details.DetailRecipeView;
import com.goranch.publicapis.ui.food.details.DetailsFoodModule;
import com.goranch.publicapis.ui.food.viewmodel.FoodDetailViewModel;
import com.goranch.publicapis.ui.webview.WebFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.support.design.widget.Snackbar.LENGTH_LONG;


public class DetailsFragment extends LifecycleFragment implements DetailRecipeView, View.OnClickListener {

    public static final String URL = "details_food_url";
    @Bind(R.id.iv_recipe_img)
    SimpleDraweeView recipeImage;
    @Bind(R.id.lv_igredients)
    LinearLayout ingredientsLinearLayout;
    @Bind(R.id.tv_view_instrouctions)
    TextView instructions;
    @Bind(R.id.tv_view_original)
    TextView viewOriginal;
    @Bind(R.id.tv_publichers_name)
    TextView publishersName;
    @Bind(R.id.tv_social_rank)
    TextView socialRank;
    @Bind(R.id.progressBar3)
    ProgressBar progressBar;

    @Inject
    DetailRecipeView mDetailRecipeView;

    @Inject
    FoodDataRepositoryImpl repository;

    private Recipe recipeData;
    private String recipeId;
    private FoodDetailViewModel viewModel;

    public static DetailsFragment newInstance(Recipe mItem) {
        Bundle b = new Bundle();
        b.putSerializable(FoodFragment.RECIPE_ITEM, mItem.getRecipeId());
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApiComponent apiComponent = ((ComponentProvider<ApiComponent>) getActivity().getApplicationContext()).getComponent();
        DaggerDetailsFoodComponent.builder()
                .apiComponent(apiComponent)
                .detailsFoodModule(new DetailsFoodModule(this))
                .build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_food, container, false);

        ButterKnife.bind(this, v);

        if (getArguments() != null) {
            Bundle b = getArguments();
            recipeId = (String) b.getSerializable(FoodFragment.RECIPE_ITEM);
        } else {
            if (getView() != null) {
                Snackbar.make(getView(), "No Recipe Id", LENGTH_LONG).show();
            }
        }

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FoodDetailViewModel.Factory factory = new FoodDetailViewModel.Factory(repository);

        viewModel = ViewModelProviders.of(this, factory).get(FoodDetailViewModel.class);

        subscribeToLiveDataChanges();

        showProgress();
        viewModel.getRecipe(recipeId);

    }

    private void subscribeToLiveDataChanges() {
        viewModel.getObservableRecipe().observe(this, this::loadNewData);
    }

    private void loadNewData(Recipe recipe) {
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(recipeData.getTitle());

        hideProgress();

        recipeData = recipe;

        ArrayAdapter<String> ingredientsAdapter = new ArrayAdapter<>(getContext(), R.layout.simple_list_item_mine, recipe.getIngredients());

        ingredientsLinearLayout.removeAllViews();
        for (int i = 0; i < ingredientsAdapter.getCount(); i++) {
            ingredientsLinearLayout.addView(ingredientsAdapter.getView(i, null, ingredientsLinearLayout));
        }

        recipeImage.setImageURI(Uri.parse(recipe.getImageUrl()));
        publishersName.setText(recipe.getPublisher());
        socialRank.setText(String.valueOf(Math.round(Double.parseDouble(recipe.getSocialRank().toString()))));

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
        WebFragment f = WebFragment.newInstance(url);
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_holder, f);
        t.addToBackStack(null);
        t.commit();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
