package com.goranch.publicapis.api

import com.goranch.publicapis.api.model.shazam.ApiTopLevelObject

import io.reactivex.Observable
import retrofit2.http.GET

interface ShazamService {

    @get:GET("/shazam/v2/en/GB/android/-/chart/recent-music/1month/-/-")
    val trending: Observable<ApiTopLevelObject>
}
