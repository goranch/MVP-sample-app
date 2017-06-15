package com.goranch.publicapis.api;

import com.goranch.publicapis.api.model.shazam.ApiTopLevelObject;

import retrofit2.http.GET;
import rx.Observable;

public interface ShazamService {

    @GET("/shazam/v2/en/GB/android/-/chart/recent-music/1month/-/-")
    Observable<ApiTopLevelObject> getTrending();
}
