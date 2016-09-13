package com.goranch.shazammvp.ui.home;

import com.goranch.shazammvp.api.ApiComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = ApiComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    void inject(MainActivityFragment mainActivityFragment);
}
