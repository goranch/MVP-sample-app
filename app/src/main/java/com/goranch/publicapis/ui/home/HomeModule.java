package com.goranch.publicapis.ui.home;

import com.goranch.publicapis.di.scopes.HomeScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by goran on 01/10/2016.
 */

@Module
public class HomeModule {

    private HomeView mHomeView;

    public HomeModule(HomeView homeView) {
        mHomeView = homeView;
    }

    @Provides
    @HomeScope
    HomeView provideHomeView() {
        return mHomeView;
    }

    @Provides
    @HomeScope
    HomePresenter provideHomePresenter(HomePresenter homeView) {
        return new HomePresenterImpl(mHomeView);
    }
}
