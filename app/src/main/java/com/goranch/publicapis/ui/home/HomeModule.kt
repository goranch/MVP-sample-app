package com.goranch.publicapis.ui.home

import com.goranch.publicapis.di.scopes.HomeScope

import dagger.Module
import dagger.Provides

@Module
class HomeModule(private val mHomeView: HomeView) {

    @Provides
    @HomeScope
    internal fun provideHomeView(): HomeView {
        return mHomeView
    }

    @Provides
    @HomeScope
    internal fun provideHomePresenter(homeView: HomePresenter): HomePresenter {
        return HomePresenterImpl(mHomeView)
    }
}
