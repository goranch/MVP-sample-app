package com.goranch.publicapis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.goranch.publicapis.ui.home.HomeFragment
import com.goranch.publicapis.ui.home.HomeView
import com.goranch.publicapis.ui.util.Utils

class MainActivity : AppCompatActivity(), HomeView {

    private val TAG = javaClass.simpleName

    @JvmField
    @BindView(R.id.toolbar_title)
    var toolbarTitle: TextView? = null

    @JvmField
    @BindView(R.id.toolbar)
    internal var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        ButterKnife.bind(this)

        if (savedInstanceState == null) {
            Utils.openFragment(this, HomeFragment.newInstance(), false)
        }
    }

}
