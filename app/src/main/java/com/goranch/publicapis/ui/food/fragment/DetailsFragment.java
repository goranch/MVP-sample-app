package com.goranch.publicapis.ui.food.fragment;

import android.app.Dialog;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.goranch.publicapis.api.model.food.nutrition.Food;
import com.goranch.publicapis.api.model.food.recipe.Recipe;
import com.goranch.publicapis.di.ComponentProvider;
import com.goranch.publicapis.ui.food.details.DaggerDetailsFoodComponent;
import com.goranch.publicapis.ui.food.details.DetailRecipeView;
import com.goranch.publicapis.ui.food.details.DetailsFoodModule;
import com.goranch.publicapis.ui.food.details.NutritionRecyclerAdapter;
import com.goranch.publicapis.ui.food.repository.FoodDataRepositoryImpl;
import com.goranch.publicapis.ui.food.viewmodel.FoodViewModel;
import com.goranch.publicapis.ui.util.Utils;
import com.goranch.publicapis.ui.webview.WebFragment;

import java.util.ArrayList;
import java.util.List;

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
        viewModel.getNutritionList().observe(this, this::loadNutritionDetails);
    }

    public void getSingleRecipe(@NonNull String recipeId) {
        if (recipeData.getIngredients() == null) {
            showProgress();
            viewModel.getSingleRecipe(recipeId);
        }
    }

    @Override
    public void onItemClicked(@NonNull Food item) {
        //do nothing for now
    }

    @Override
    public String getNutritionText(@NonNull Food food) {
        if (food != null) {
            return getResources().getString(R.string.nutrition_details,
                    String.valueOf(food.getServingQty().intValue()),
                    food.getFoodName(),
                    String.valueOf(food.getServingWeightGrams()),
                    String.valueOf(food.getNfProtein()),
                    String.valueOf(food.getNfCalories()),
                    String.valueOf(food.getNfTotalFat()),
                    String.valueOf(food.getNfSaturatedFat()),
                    String.valueOf(food.getNfCholesterol()),
                    String.valueOf(food.getNfSodium()),
                    String.valueOf(food.getNfTotalCarbohydrate()),
                    String.valueOf(food.getNfDietaryFiber()),
                    String.valueOf(food.getNfSugars()));
        } else {
            return getResources().getString(R.string.no_nutrition_to_display);
        }
    }

    @Override
    public void showHTTPError() {

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
        }
    }

    private void loadNutritionDetails(List<Food> foods) {
        if (foods != null && foods.size() > 0) {
            hideProgress();
            Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.nutrition_dialog_layout);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialog.show();
            dialog.setOnCancelListener(dialog1 -> hideProgress());
            RecyclerView recyclerView = dialog.findViewById(R.id.nutrition_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            NutritionRecyclerAdapter adapter = new NutritionRecyclerAdapter(this, foods);
            recyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
            viewModel.resetNutritionList();
        }
    }


    @Override
    public void openWebView(@NonNull String url) {
        Utils.INSTANCE.openFragment(getActivity(), WebFragment.newInstance(url), true);
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    public String getIngredients() {
        StringBuilder builder = new StringBuilder();
        for (String s : recipeData.getIngredients()) {
            builder.append(s);
        }
        return builder.toString();
    }

    @OnClick(R.id.tv_view_instructions)
    public void openInstructions() {
        openWebView(recipeData.getF2fUrl());
    }

    @OnClick(R.id.tv_view_original)
    public void openSource() {
        openWebView(recipeData.getSourceUrl());
    }

    @OnClick(R.id.btn_more_details)
    public void showNutritionDetails() {
        showProgress();
        viewModel.getNaturalLanguageNutritionInfo(getIngredients());
        loadNutritionDetails(new ArrayList<>());
    }
}
