package com.goranch.publicapis.ui.shazam;

import com.goranch.publicapis.api.ShazamService;
import com.goranch.publicapis.api.model.shazam.ApiTopLevelObject;
import com.goranch.publicapis.api.model.shazam.Item;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Goran Ch on 16/04/16.
 *
 * Class to handle all the HTTP communication
 */
public class ShazamShazamDataRepositoryImpl implements IShazamDataRepository {
    private final ShazamService mShazamService;

    public ShazamShazamDataRepositoryImpl(ShazamService shazamService) {
        mShazamService = shazamService;
    }

    @Override
    public void loadCharts(Callback<ArrayList<Item>> callback) {

        final Observable<ArrayList<Item>> artistsObservable = mShazamService.getTrending()
                .subscribeOn(Schedulers.io()) // make the request in a background thread
                .observeOn(AndroidSchedulers.mainThread())
                .map(ApiTopLevelObject::getChart);

        artistsObservable.subscribe(callback::onDataUpdated, callback::onError);
    }
}
