package com.goranch.shazammvp.ui.presenters;

import android.os.Bundle;
import android.view.View;

import com.goranch.shazammvp.api.DataRepositoryImpl;
import com.goranch.shazammvp.api.IDataRepository;
import com.goranch.shazammvp.api.model.Item;
import com.goranch.shazammvp.ui.fragments.MainActivityFragment;
import com.goranch.shazammvp.ui.views.MainFragmentView;

import java.util.ArrayList;

/**
 * Created by Goran Ch on 16/04/16.
 *
 * Presenter class that deals with all the business logic
 */
public class ArtistListPresenterImpl implements IArtistPresenter {

    private DataRepositoryImpl dataRepository;
    private MainFragmentView view;

    public ArtistListPresenterImpl(MainFragmentView mainFragmentView, DataRepositoryImpl dataRepository) {
        this.dataRepository = dataRepository;
        view = mainFragmentView;
    }

    @Override
    public void loadData(ArrayList<Item> items) {

        // make the request and load the data if the list is empty
        if (items.size() == 0) {

            view.showLoadingProgress();

            dataRepository.loadCharts(new IDataRepository.Callback<ArrayList<Item>>() {
                @Override
                public void onDataUpdated(ArrayList<Item> data) {

                    view.hideLoadingProgress();

                    view.onDataUpdated(data);
                }

                @Override
                public void onError(Throwable throwable) {

                    view.hideLoadingProgress();

                    view.showError(throwable);

                }
            });
        }
    }

    @Override
    public void onItemClicked(Item item) {
        view.openDetailsFragment(item);
    }

    @Override
    public void refreshData() {
        loadData(new ArrayList<>());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // get the the list items from the previous saved state
            view.loadSavedItems(savedInstanceState);
        }
    }

    @Override
    public void showError(View v, Throwable throwable) {
        //the view might be null in som cases
        if (v != null){
            view.showSnackbar(v, throwable);
        }
    }
}
