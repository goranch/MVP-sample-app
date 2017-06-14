package com.goranch.publicapis.ui.shazam;

import com.goranch.publicapis.api.ApiComponent;
import com.goranch.publicapis.di.scopes.HomeScope;

import dagger.Component;

@HomeScope
@Component(dependencies = ApiComponent.class, modules = MusicModule.class)
interface MusicComponent {
    void inject(MusicFragment musicFragment);
}
