package com.goranch.publicapis.ui.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.goranch.publicapis.R

object Utils {
    fun openFragment(activity: FragmentActivity, fragment: Fragment, addToBackStack: Boolean) {
        val t = activity.supportFragmentManager.beginTransaction()
        t.replace(R.id.fragment_holder, fragment)
        if (addToBackStack) {
            t.addToBackStack(null)
        }
        t.commit()
    }
}
