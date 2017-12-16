package com.goranch.publicapis.ui.food.fragment

import android.app.Activity
import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.goranch.publicapis.R
import com.goranch.publicapis.api.ApiComponent
import com.goranch.publicapis.api.model.food.recipe.Recipe
import com.goranch.publicapis.di.ComponentProvider
import com.goranch.publicapis.ui.food.DaggerFoodComponent
import com.goranch.publicapis.ui.food.FoodModule
import com.goranch.publicapis.ui.food.RecipeRecyclerAdapter
import com.goranch.publicapis.ui.food.SearchRecipeView
import com.goranch.publicapis.ui.food.repository.FoodDataRepositoryImpl
import com.goranch.publicapis.ui.food.viewmodel.FoodViewModel
import com.goranch.publicapis.ui.util.Utils
import java.util.*
import javax.inject.Inject

class FoodFragment : LifecycleFragment(), SearchRecipeView, TextView.OnEditorActionListener {

    @BindView(R.id.recyclerview)
    @JvmField
    internal var recyclerView: RecyclerView? = null

    @BindView(R.id.et_search)
    @JvmField
    internal var search: EditText? = null

    @BindView(R.id.progressBar)
    @JvmField
    internal var progressBar: ProgressBar? = null

    @Inject
    @JvmField
    internal var mSearchRecipeView: SearchRecipeView? = null

    @Inject
    @JvmField
    internal var repository: FoodDataRepositoryImpl? = null

    private var adapter: RecipeRecyclerAdapter? = null
    private var recipeList: List<Recipe> = ArrayList()
    private var viewModel: FoodViewModel? = null
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiComponent = (activity!!.applicationContext as ComponentProvider<ApiComponent>).component
        DaggerFoodComponent.builder()
                .apiComponent(apiComponent)
                .foodModule(FoodModule(this))
                .build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_food, container, false)

        ButterKnife.bind(this, v)

        return v
    }

    override fun onResume() {
        super.onResume()
        activity!!.setTitle(R.string.food_search)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = FoodViewModel.Factory(repository!!)

        viewModel = ViewModelProviders.of(this!!.activity!!, factory).get(FoodViewModel::class.java)

        adapter = RecipeRecyclerAdapter(this, recipeList)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridLayoutManager = GridLayoutManager(activity, 1, Configuration.ORIENTATION_PORTRAIT, false)
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridLayoutManager = GridLayoutManager(activity, 4, Configuration.ORIENTATION_UNDEFINED, false)
        }

        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.adapter = adapter

        search?.setSingleLine()
        search?.setOnEditorActionListener(this)
        search?.requestFocus()

        //TODO save the last searched recipe and set it here
        search?.setText("Green tea")

        subscribeToLiveDataChanges()
    }

    // this method will be invoked with the new data every time when the data changes in the ViewModel class.
    private fun subscribeToLiveDataChanges() {
        viewModel?.observableRecipeList?.observe(this, Observer<List<Recipe>> { this.loadData(it!!) })
    }

    override fun loadData(recipeList: List<Recipe>) {
        hideProgress()
        this.recipeList = recipeList
        adapter?.setRecipes(this.recipeList)
        adapter?.notifyDataSetChanged()
        hideProgress()
        hideSoftKeyboard(this!!.activity!!)
    }

    fun getRecipes(query: String) {
        viewModel?.getRecipes(query)
    }

    @OnClick(R.id.btn_search)
    fun searchRecipe() {
        showProgress()
        getRecipes(search?.text.toString())
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

    override fun openDetailsFragment() {
        Utils.openFragment(this!!.activity!!, DetailsFragment.newInstance(), true)
    }

    override fun onItemClicked(mItem: Recipe) {
        viewModel?.onItemClicked(mItem)
        openDetailsFragment()
    }

    override fun showHTTPError() {
        if (view != null) {
            val snackbar = Snackbar.make(view!!, R.string.http_error, Snackbar.LENGTH_LONG)
            snackbar.setAction(R.string.dismiss) { v -> snackbar.dismiss() }.show()
        }
    }

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
        if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
            getRecipes(search?.text.toString())
        } else if (actionId == EditorInfo.IME_ACTION_SEARCH
                || event == null
                || event.keyCode == KeyEvent.KEYCODE_ENTER) {
            getRecipes(search?.text.toString())
        }
        return false
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus?.windowToken, 0)
    }

    companion object {

        fun newInstance(): FoodFragment {
            return FoodFragment()
        }
    }
}
