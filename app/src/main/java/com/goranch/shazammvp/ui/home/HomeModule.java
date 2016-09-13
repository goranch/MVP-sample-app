package com.goranch.shazammvp.ui.home;

import com.goranch.shazammvp.api.DataRepositoryImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by goran on 13/09/2016.
 */

@Module
public class HomeModule {
    private final MainFragmentView mMainFragmentView;

    public HomeModule(MainFragmentView mainFragmentView) {
        mMainFragmentView = mainFragmentView;
    }

    @Provides
    MainFragmentView provideMainFragnemtView() {
        return mMainFragmentView;
    }

    @Provides
    DataRepositoryImpl provideDataRepository() {
        return new DataRepositoryImpl();
    }

    @Provides
    TrendingPresenter provideTrendingPresenter(MainFragmentView view, DataRepositoryImpl repository) {
        return new TrendingListPresenterImpl(view, repository);
    }
}
