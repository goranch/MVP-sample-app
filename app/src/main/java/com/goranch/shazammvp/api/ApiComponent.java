package com.goranch.shazammvp.api;

import android.app.Application;

import com.goranch.shazammvp.di.ShazamComponent;

import dagger.Component;

/**
 * Created by goran on 10/09/2016.
 */

@Component(dependencies = ShazamComponent.class, modules = ApiModule.class)
public interface ApiComponent {
    // Expose to subgraphs
    Application application();

    ApiService apiService();
}
