package com.goranch.shazammvp.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.goranch.shazammvp.MainActivity;
import com.goranch.shazammvp.R;
import com.goranch.shazammvp.api.ApiComponent;
import com.goranch.shazammvp.api.model.Item;
import com.goranch.shazammvp.di.ComponentProvider;
import com.goranch.shazammvp.ui.adapters.ArtistRecyclerAdapter;
import com.goranch.shazammvp.ui.fragments.DetailsFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Goran Ch on 16/04/16.
 */
public class MainActivityFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, MainFragmentView {

    public static final String TRACK_ITEM = "track_item";
    private static final String LIST_ITEMS = "list_items";
    @Bind(R.id.swipeContainer)
    public SwipeRefreshLayout refresh;
    @Bind(R.id.recyclerview)
    public RecyclerView recyclerView;
    @Bind(R.id.progressBar)
    public ProgressBar progressBar;
    @Inject
    MainFragmentView mMainFragmentView;
    @Inject
    TrendingPresenter presenter;
    private ArtistRecyclerAdapter adapter;
    private ArrayList<Item> items = new ArrayList<>();

    public MainActivityFragment() {
    }

    public static MainActivityFragment newInstance() {
        return new MainActivityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiComponent apiComponent = ((ComponentProvider<ApiComponent>) getActivity().getApplicationContext()).getComponent();
        DaggerHomeComponent.builder()
                .apiComponent(apiComponent)
                .homeModule(new HomeModule(this))
                .build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, v);

        initUi();

        return v;
    }

    private void initUi() {
        ((MainActivity) getActivity()).toolbarTitle.setText(R.string.app_name);
//        presenter = new TrendingListPresenterImpl(this, new DataRepositoryImpl());

        adapter = new ArtistRecyclerAdapter(presenter, items);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        // Enable swipeToRefresh only when we're at the top of the list
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                refresh.setEnabled(topRowVerticalPosition >= 0);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        refresh.setOnRefreshListener(this);

        presenter.onActivityCreated(savedInstanceState);

        presenter.loadData(items);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //save teh list items for the case when activity is destroyed e.g.(rotate screen)
        outState.putSerializable(LIST_ITEMS, items);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDataUpdated(ArrayList<Item> data) {
        this.items = data;
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgress() {
        progressBar.setVisibility(View.GONE);
        refresh.setRefreshing(false);
    }


    @Override
    public void openDetailsFragment(Item item) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TRACK_ITEM, item);
        detailsFragment.setArguments(bundle);

        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_holder, detailsFragment);
        t.addToBackStack(null);
        t.commit();
    }

    @Override
    public void showError(Throwable throwable) {

        hideLoadingProgress();

        //send the view to the presenter because it can be null
        presenter.showError(getView(), throwable);

    }

    @Override
    public void loadSavedItems(Bundle savedInstanceState) {
        items = (ArrayList<Item>) savedInstanceState.getSerializable(LIST_ITEMS);
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showSnackbar(View v, Throwable throwable) {
        Snackbar.make(v, throwable.toString(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        presenter.refreshData();
    }
}