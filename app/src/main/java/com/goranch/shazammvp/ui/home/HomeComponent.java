package com.goranch.shazammvp.ui.home;

import com.goranch.shazammvp.api.ApiComponent;
import com.goranch.shazammvp.di.scopes.HomeScope;

import dagger.Component;

@HomeScope
@Component(dependencies = ApiComponent.class, modules = HomeModule.class)
interface HomeComponent {
    void inject(MainActivityFragment mainActivityFragment);
}
