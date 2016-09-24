package com.goranch.shazammvp.api;

import com.goranch.shazammvp.api.model.shazam.ApiTopLevelObject;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Goran Ch on 16/04/16.
 */
public interface ApiService {

    @GET("/shazam/v2/en/GB/android/-/chart/recent-music/1month/-/-")
    Observable<ApiTopLevelObject> getTrending();
}
