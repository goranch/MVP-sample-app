package com.goranch.publicapis.ui.food.details;

import com.goranch.publicapis.api.model.food.Recipe;

/**
 * Created by goran on 06/04/2017.
 */

public interface DetailRecipeView {

    void onDataUpdated(Recipe data);

    void showProgress();

    void hideProgress();

    void openWebView(String url);

    void onError(Throwable throwable);
}
