package com.goranch.publicapis.ui.food.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.goranch.publicapis.ui.food.FoodModule;
import com.goranch.publicapis.ui.food.RecipeListPresenter;
import com.goranch.publicapis.ui.food.RecipeRecyclerAdapter;
import com.goranch.publicapis.ui.food.SearchRecipeView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by goran on 03/01/2017.
 */
public class FoodFragment extends Fragment implements SearchRecipeView, TextView.OnEditorActionListener {
    public static final String RECIPE_ITEM = "recipe_item";
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    @Bind(R.id.et_search)
    EditText search;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    RecipeListPresenter presenter;

    @Inject
    SearchRecipeView mSearchRecipeView;

    private RecipeRecyclerAdapter adapter;
    private ArrayList<Recipe> recipes = new ArrayList<>();

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

        setRetainInstance(true);
        if (savedInstanceState == null) {

            adapter = new RecipeRecyclerAdapter(presenter, recipes);

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

            recyclerView.setAdapter(adapter);

            search.setSingleLine();
            search.setOnEditorActionListener(this);

        }
        return v;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
            || (actionId == EditorInfo.IME_ACTION_DONE)) {
            presenter.onSearchRequest(search.getText().toString());
        } else if (actionId == EditorInfo.IME_ACTION_SEARCH
            || event == null
            || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            presenter.onSearchRequest(search.getText().toString());
        }
        return false;
    }

    @Override
    public void onDataUpdated(ArrayList<Recipe> data) {
        this.recipes = data;
        adapter.setRecipes(data);
        adapter.notifyDataSetChanged();

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
        DetailsFragment f = new DetailsFragment();
        Bundle b = new Bundle();
        b.putSerializable(RECIPE_ITEM, mItem);
        f.setArguments(b);
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_holder, f);
        t.addToBackStack(null);
        t.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}