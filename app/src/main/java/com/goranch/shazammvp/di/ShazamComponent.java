package com.goranch.shazammvp.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by goran on 10/09/2016.
 */

@Singleton
@Component(modules = {ShazamModule.class})
public interface ShazamComponent {

    // Expose to subgraphs
    Application application();
}
