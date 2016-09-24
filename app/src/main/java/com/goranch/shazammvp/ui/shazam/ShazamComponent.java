package com.goranch.shazammvp.ui.shazam;

import com.goranch.shazammvp.api.ApiComponent;
import com.goranch.shazammvp.di.scopes.HomeScope;

import dagger.Component;

@HomeScope
@Component(dependencies = ApiComponent.class, modules = ShazamModule.class)
interface ShazamComponent {
    void inject(ShazamFragment shazamFragment);
}
