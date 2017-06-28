package com.goranch.publicapis.ui.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.goranch.publicapis.R;

public class Utils {
    public static void openFragment(FragmentActivity activity, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction t = activity.getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_holder, fragment);
        if (addToBackStack) {
            t.addToBackStack(null);
        }
        t.commit();
    }
}
