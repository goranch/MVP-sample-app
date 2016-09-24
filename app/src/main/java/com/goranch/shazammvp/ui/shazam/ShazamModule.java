package com.goranch.shazammvp.ui.shazam;

import com.goranch.shazammvp.api.ApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by goran on 13/09/2016.
 */

@Module
public class ShazamModule {
    private final ShazamFragmentView mShazamFragmentView;

    public ShazamModule(ShazamFragmentView shazamFragmentView) {
        mShazamFragmentView = shazamFragmentView;
    }

    @Provides
    ShazamFragmentView provideMainFragnemtView() {
        return mShazamFragmentView;
    }

    @Provides
    ShazamShazamDataRepositoryImpl provideDataRepository(ApiService apiService) {
        return new ShazamShazamDataRepositoryImpl(apiService);
    }

    @Provides
    TrendingPresenter provideTrendingPresenter(ShazamFragmentView view, ShazamShazamDataRepositoryImpl repository) {
        return new TrendingListPresenterImpl(view, repository);
    }
}
