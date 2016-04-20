package com.goranch.shazammvp.ui.views;

import android.os.Bundle;
import android.view.View;

import com.goranch.shazammvp.api.model.Item;

import java.util.ArrayList;

/**
 * Created by Goran Ch on 16/04/16.
 */
public interface MainFragmentView {

    void onDataUpdated(ArrayList<Item> data);

    void showLoadingProgress();

    void hideLoadingProgress();

    void openDetailsFragment(Item item);

    void showError(Throwable throwable);

    void loadSavedItems(Bundle savedInstancState);

    void showSnackbar(View v, Throwable throwable);
}
