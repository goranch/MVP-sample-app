package com.goranch.publicapis.api;

import android.app.Application;

import com.goranch.publicapis.di.PublicApisComponent;
import com.goranch.publicapis.di.scopes.ApiScope;
import com.goranch.publicapis.ui.home.HomeFragment;

import dagger.Component;

/**
 * Created by goran on 10/09/2016.
 */

@ApiScope
@Component(dependencies = PublicApisComponent.class, modules = ApiModule.class)
public interface ApiComponent {

    void inject(HomeFragment homeFragment);

    // Expose to subgraphs
    Application application();

    ShazamService apiService();

    FoodService foodService();
}
