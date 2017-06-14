package com.goranch.publicapis.ui.home;

/**
 * Created by goran on 01/10/2016.
 */

public class HomePresenterImpl implements HomePresenter {
    private HomeView mHomeView;

    public HomePresenterImpl(HomeView homeView) {
        mHomeView = homeView;
    }

    @Override
    public void loadHomepage() {

    }
}
