package com.goranch.publicapis.ui.shazam;

import android.os.Bundle;
import android.view.View;

import com.goranch.publicapis.api.model.shazam.Item;

import java.util.ArrayList;

/**
 * Created by Goran Ch on 16/04/16.
 */
interface ShazamFragmentView {

    void initUi();

    void onDataUpdated(ArrayList<Item> data);

    void showLoadingProgress();

    void hideLoadingProgress();

    void openDetailsFragment(Item item);

    void showError(Throwable throwable);

    void loadSavedItems(Bundle savedInstancState);

    void showSnackbar(View v, Throwable throwable);
}
