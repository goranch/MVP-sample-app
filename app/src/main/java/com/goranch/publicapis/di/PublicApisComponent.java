package com.goranch.publicapis.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PublicApisModule.class})
public interface PublicApisComponent {

    // Expose to subgraphs
    Application application();
}