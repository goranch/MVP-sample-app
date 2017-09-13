package com.goranch.publicapis.ui.food

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.goranch.publicapis.R
import com.goranch.publicapis.ui.food.fragment.FoodFragment
import com.goranch.publicapis.ui.util.Utils


class FoodActivity : AppCompatActivity(), FoodActivityView {
    private val TAG = javaClass.simpleName
    @BindView(R.id.toolbar)
    @JvmField
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_coordinator)
        ButterKnife.bind(this)

        setTitle()

        if (savedInstanceState == null) {
            Utils.openFragment(this, FoodFragment.newInstance(), false)
        }
    }

    override fun setTitle() {
        this.setTitle(R.string.food_search)
        setSupportActionBar(toolbar)
    }
}
