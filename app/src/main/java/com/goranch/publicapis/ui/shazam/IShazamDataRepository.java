package com.goranch.publicapis.ui.shazam;

import com.goranch.publicapis.api.model.shazam.Item;

import java.util.ArrayList;

/**
 * Created by Goran Ch on 16/04/16.
 */
interface IShazamDataRepository {
    /**
     * Makes a request to the API to load the list of charts
     *
     * @param callback the {@link Callback} that will return a list of {@link Item} in onDataUpdated method or a {@link Throwable} in the onError method
     */
    void loadCharts(Callback<ArrayList<Item>> callback);

    interface Callback<T> {
        void onDataUpdated(T data);

        void onError(Throwable throwable);
    }
}
