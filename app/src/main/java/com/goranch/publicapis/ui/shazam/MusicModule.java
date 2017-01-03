package com.goranch.publicapis.ui.shazam;

import com.goranch.publicapis.api.ShazamService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by goran on 13/09/2016.
 */

@Module
public class MusicModule {
    private final MusicFragmentView mMusicFragmentView;

    public MusicModule(MusicFragmentView musicFragmentView) {
        mMusicFragmentView = musicFragmentView;
    }

    @Provides
    MusicFragmentView provideMainFragnemtView() {
        return mMusicFragmentView;
    }

    @Provides
    ShazamShazamDataRepositoryImpl provideDataRepository(ShazamService shazamService) {
        return new ShazamShazamDataRepositoryImpl(shazamService);
    }

    @Provides
    TrendingPresenter provideTrendingPresenter(MusicFragmentView view, ShazamShazamDataRepositoryImpl repository) {
        return new TrendingListPresenterImpl(view, repository);
    }
}
