package com.goranch.publicapis.ui.food.fragment;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.goranch.publicapis.api.model.food.recipe.Recipe;
import com.goranch.publicapis.di.ComponentProvider;
import com.goranch.publicapis.ui.food.FoodDataRepositoryImpl;
import com.goranch.publicapis.ui.food.details.DaggerDetailsFoodComponent;
import com.goranch.publicapis.ui.food.details.DetailRecipeView;
import com.goranch.publicapis.ui.food.details.DetailsFoodModule;
import com.goranch.publicapis.ui.food.viewmodel.FoodViewModel;
import com.goranch.publicapis.ui.webview.WebFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetailsFragment extends LifecycleFragment implements DetailRecipeView {

    public static final String URL = "details_food_url";
    @BindView(R.id.iv_recipe_img)
    SimpleDraweeView recipeImage;
    @BindView(R.id.lv_ingredients)
    LinearLayout ingredientsLinearLayout;
    @BindView(R.id.tv_publishers_name)
    TextView publishersName;
    @BindView(R.id.tv_social_rank)
    TextView socialRank;
    @BindView(R.id.progressBar3)
    ProgressBar progressBar;

    @Inject
    DetailRecipeView mDetailRecipeView;

    @Inject
    FoodDataRepositoryImpl repository;

    private Recipe recipeData;
    private FoodViewModel viewModel;

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @SuppressWarnings("unchecked")
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

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(FoodViewModel.class);

        subscribeToLiveDataChanges();
    }

    private void subscribeToLiveDataChanges() {
        viewModel.getObservableRecipe().observe(this, this::loadNewData);
        viewModel.getRecipeId().observe(this, this::getSingleRecipe);
    }

    public void getSingleRecipe(String recipeId) {
        if (recipeData.getIngredients() == null) {
            showProgress();
            viewModel.getSingleRecipe(recipeId);
        }
    }

    private void loadNewData(@NonNull Recipe recipe) {
        hideProgress();
        recipeData = recipe;
        getActivity().setTitle(recipeData.getTitle());

        recipeImage.setImageURI(Uri.parse(recipeData.getImageUrl()));
        publishersName.setText(recipeData.getPublisher());
        socialRank.setText(String.valueOf(Math.round(Double.parseDouble(recipeData.getSocialRank().toString()))));

        if (recipe.getIngredients() != null) {
            ArrayAdapter<String> ingredientsAdapter = new ArrayAdapter<>(getContext(), R.layout.simple_list_item_mine, recipe.getIngredients());
            ingredientsLinearLayout.removeAllViews();
            for (int i = 0; i < ingredientsAdapter.getCount(); i++) {
                ingredientsLinearLayout.addView(ingredientsAdapter.getView(i, null, ingredientsLinearLayout));
            }
            viewModel.getNaturalLanguageNutritionInfo("query" + recipeData.getIngredients().toString());
        }
    }

    @OnClick(R.id.tv_view_instructions)
    public void openInstructions() {
        openWebView(recipeData.getF2fUrl());
    }

    @OnClick(R.id.tv_view_original)
    public void openSource() {
        openWebView(recipeData.getSourceUrl());
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
        progressBar.setVisibility(View.GONE);
    }
}
