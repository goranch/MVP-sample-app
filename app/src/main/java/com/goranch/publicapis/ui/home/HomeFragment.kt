package com.goranch.publicapis.ui.home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import com.goranch.publicapis.R
import com.goranch.publicapis.ui.food.FoodActivity
import com.goranch.publicapis.ui.util.Utils

class HomeFragment : Fragment(), HomeView {

    @OnClick(R.id.btn_food)
    internal fun clickFood() {
        val i = Intent(activity, FoodActivity::class.java)
        startActivity(i)
    }

    private fun replaceFragment(fragment: Fragment) {
        Utils.openFragment(activity, fragment, true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_home, container, false)

        ButterKnife.bind(this, v)

        return v
    }

    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
