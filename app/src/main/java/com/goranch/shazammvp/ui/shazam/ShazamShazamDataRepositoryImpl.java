package com.goranch.shazammvp.ui.shazam;

import com.goranch.shazammvp.api.ApiService;
import com.goranch.shazammvp.api.model.shazam.ApiTopLevelObject;
import com.goranch.shazammvp.api.model.shazam.Item;

import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Goran Ch on 16/04/16.
 *
 * Class to handle all the HTTP communication
 */
public class ShazamShazamDataRepositoryImpl implements IShazamDataRepository {
    private final ApiService mApiService;

    public ShazamShazamDataRepositoryImpl(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void loadCharts(Callback<ArrayList<Item>> callback) {

        final Observable<ArrayList<Item>> artistsObservable = mApiService.getTrending()
                .subscribeOn(Schedulers.io()) // make the request in a background thread
                .observeOn(AndroidSchedulers.mainThread())
                .map(ApiTopLevelObject::getChart);

        artistsObservable.subscribe(callback::onDataUpdated, callback::onError);
    }
}
