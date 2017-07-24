package com.goranch.publicapis.ui.food.fragment;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.goranch.publicapis.R;
import com.goranch.publicapis.api.ApiComponent;
import com.goranch.publicapis.api.model.food.recipe.Recipe;
import com.goranch.publicapis.di.ComponentProvider;
import com.goranch.publicapis.ui.food.DaggerFoodComponent;
import com.goranch.publicapis.ui.food.FoodDataRepositoryImpl;
import com.goranch.publicapis.ui.food.FoodModule;
import com.goranch.publicapis.ui.food.RecipeRecyclerAdapter;
import com.goranch.publicapis.ui.food.SearchRecipeView;
import com.goranch.publicapis.ui.food.viewmodel.FoodViewModel;
import com.goranch.publicapis.ui.util.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodFragment extends LifecycleFragment implements SearchRecipeView, TextView.OnEditorActionListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.et_search)
    EditText search;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    SearchRecipeView mSearchRecipeView;

    @Inject
    FoodDataRepositoryImpl repository;

    private RecipeRecyclerAdapter adapter;
    private List<Recipe> recipeList = new ArrayList<>();
    private FoodViewModel viewModel;
    private GridLayoutManager gridLayoutManager;

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

        FoodViewModel.Factory factory = new FoodViewModel.Factory(repository);

        viewModel = ViewModelProviders.of(getActivity(), factory).get(FoodViewModel.class);

        adapter = new RecipeRecyclerAdapter(this, recipeList);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridLayoutManager = new GridLayoutManager(getActivity(), 1, Configuration.ORIENTATION_PORTRAIT, false);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridLayoutManager = new GridLayoutManager(getActivity(), 4, Configuration.ORIENTATION_UNDEFINED, false);
        }

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        search.setSingleLine();
        search.setOnEditorActionListener(this);
        search.requestFocus();

        //TODO save the last searched recipe and set it here
        search.setText("Chicken");

        subscribeToLiveDataChanges();
    }

    // this method will be invoked with the new data every time when the data changes in the ViewModel class.
    private void subscribeToLiveDataChanges() {
        viewModel.getObservableRecipeList().observe(this, this::loadData);
    }

    @Override
    public void loadData(List<Recipe> recipeList) {
        hideProgress();
        this.recipeList = recipeList;
        adapter.setRecipes(this.recipeList);
        adapter.notifyDataSetChanged();
        hideProgress();
        hideSoftKeyboard(getActivity());
    }

    public void getRecipes(String query) {
        viewModel.getRecipes(query);
    }

    @OnClick(R.id.btn_search)
    public void searchRecipe() {
        showProgress();
        getRecipes(search.getText().toString());
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
    public void openDetailsFragment() {
        Utils.openFragment(getActivity(), DetailsFragment.newInstance(), true);
    }

    @Override
    public void onItemClicked(Recipe mItem) {
//        viewModel.onItemClicked(mItem);
        openDetailsFragment();
    }

    @Override
    public void showHTTPError() {
        if (getView() != null) {
            Snackbar snackbar = Snackbar.make(getView(), R.string.http_error, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.dismiss, v -> snackbar.dismiss()).show();
        }
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

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
