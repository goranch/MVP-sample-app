package com.goranch.publicapis.ui.shazam;

import android.os.Bundle;
import android.view.View;

import com.goranch.publicapis.api.model.shazam.Item;

import java.util.ArrayList;

/**
 * Created by Goran Ch on 16/04/16.
 */
public interface TrendingPresenter {
    /**
     * Make an http request to load the artists data
     * @param items bla
     */
    void loadData(ArrayList<Item> items);
    void onItemClicked(Item item);

    /**
     * Re fetch the data on SwipeToRefresh
     */
    void refreshData();

    void onActivityCreated(Bundle savedInstanceState);

    void showError(View v, Throwable throwable);
}
