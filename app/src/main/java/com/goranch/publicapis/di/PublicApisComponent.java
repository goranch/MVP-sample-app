package com.goranch.publicapis.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by goran on 10/09/2016.
 */

@Singleton
@Component(modules = {PublicApisModule.class})
public interface PublicApisComponent {

    // Expose to subgraphs
    Application application();
}
