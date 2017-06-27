package com.goranch.publicapis.api;


import com.goranch.publicapis.api.model.food.nutrition.TotalHits;
import com.goranch.publicapis.ui.food.FoodDataRepositoryImpl;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NutritionService {

    @Headers({"Content-Type:application/json",
            "x-app-id:" + ApiModule.NUTRITION_APP_ID,
            "x-app-key:" + ApiModule.NUTRITION_API_KEY
    })
    @POST("v2/natural/nutrients")
    Observable<TotalHits> searchNutritions(
            @Body FoodDataRepositoryImpl.HTTPRequestBody body
    );
    
}
