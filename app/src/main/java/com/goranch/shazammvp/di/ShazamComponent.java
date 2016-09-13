package com.goranch.shazammvp.di;

import android.app.Application;

import dagger.Component;

/**
 * Created by goran on 10/09/2016.
 */

@Component(modules = {ShazamModule.class})
public interface ShazamComponent {

    // Expose to subgraphs
    Application application();
}
