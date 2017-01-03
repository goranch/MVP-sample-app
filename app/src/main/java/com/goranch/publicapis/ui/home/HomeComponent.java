package com.goranch.publicapis.ui.home;

import com.goranch.publicapis.MainActivity;
import com.goranch.publicapis.api.ApiComponent;
import com.goranch.publicapis.di.scopes.HomeScope;

import dagger.Component;

/**
 * Created by goran on 01/10/2016.
 */

@HomeScope
@Component(dependencies = ApiComponent.class, modules = HomeModule.class)
interface HomeComponent {
    void inject(MainActivity mainActivity);
}
