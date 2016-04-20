package com.goranch.shazammvp.api;

import com.goranch.shazammvp.api.model.ApiTopLevelObject;
import com.goranch.shazammvp.api.model.Item;

import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Goran Ch on 16/04/16.
 *
 * Class to handle all the HTTP communication
 */
public class DataRepositoryImpl implements IDataRepository {

    @Override
    public void loadCharts(Callback<ArrayList<Item>> callback) {
        final ApiService apiService = RestServiceProvider.getApiServiceInstance();

        final Observable<ArrayList<Item>> artistsObservable = apiService.getCharts()
                .subscribeOn(Schedulers.io()) // make the request in a background thread
                .observeOn(AndroidSchedulers.mainThread())
                .map(ApiTopLevelObject::getChart);

        artistsObservable.subscribe(callback::onDataUpdated, callback::onError);
    }
}
