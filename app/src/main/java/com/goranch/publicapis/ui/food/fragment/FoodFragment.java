package com.goranch.publicapis.ui.food.fragment;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.goranch.publicapis.R;
import com.goranch.publicapis.api.ApiComponent;
import com.goranch.publicapis.api.model.food.Recipe;
import com.goranch.publicapis.di.ComponentProvider;
import com.goranch.publicapis.ui.food.DaggerFoodComponent;
import com.goranch.publicapis.ui.food.FoodDataRepositoryImpl;
import com.goranch.publicapis.ui.food.FoodModule;
import com.goranch.publicapis.ui.food.RecipeRecyclerAdapter;
import com.goranch.publicapis.ui.food.SearchRecipeView;
import com.goranch.publicapis.ui.food.viewmodel.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodFragment extends LifecycleFragment implements SearchRecipeView, TextView.OnEditorActionListener {
    public static final String RECIPE_ITEM = "recipe_item";
    private static final String TAG = FoodFragment.class.getSimpleName();
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    @Bind(R.id.et_search)
    EditText search;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    SearchRecipeView mSearchRecipeView;

    @Inject
    FoodDataRepositoryImpl repository;

    private RecipeRecyclerAdapter adapter;
    private List<Recipe> mRecipes = new ArrayList<>();
    private FoodViewModel viewModel;

    public static FoodFragment newInstance() {
        return new FoodFragment();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ApiComponent apiComponent = ((ComponentProvider<ApiComponent>) getActivity().getApplicationContext()).getComponent();
        DaggerFoodComponent.builder()
                .apiComponent(apiComponent)
                .foodModule(new FoodModule(this))
                .build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.food_search);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FoodViewModel.Factory factory = new FoodViewModel.Factory(repository, this);

        viewModel = ViewModelProviders.of(this, factory).get(FoodViewModel.class);

        adapter = new RecipeRecyclerAdapter(viewModel, mRecipes);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(adapter);

        search.setSingleLine();
        search.setOnEditorActionListener(this);
        search.requestFocus();

        //TODO save the last searched recipe and set it here
        search.setText("Chicken");

        subscribeToLiveDataChanges();
    }

    private void subscribeToLiveDataChanges() {
        viewModel.getObservableRecipes().observe(this, recipes -> {
            if (recipes == null) {
                showProgress();
            } else {
                Log.d(TAG, "size: " + recipes.size());
                hideProgress();
                mRecipes = recipes;
                adapter.setRecipes(recipes);
                adapter.notifyDataSetChanged();
            }
        });
    }


    public void getRecipes(String query) {
        showProgress();
        viewModel.getRecipes(query);
    }

    @OnClick(R.id.btn_search)
    public void searchRecipe() {
        showProgress();
        viewModel.getRecipes(search.getText().toString());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openDetailsFragment(Recipe mItem) {
        DetailsFragment f = DetailsFragment.newInstance(mItem);
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_holder, f);
        t.addToBackStack(null);
        t.commit();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                || (actionId == EditorInfo.IME_ACTION_DONE)) {
            getRecipes(search.getText().toString());
        } else if (actionId == EditorInfo.IME_ACTION_SEARCH
                || event == null
                || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            getRecipes(search.getText().toString());
        }
        return false;
    }
}
