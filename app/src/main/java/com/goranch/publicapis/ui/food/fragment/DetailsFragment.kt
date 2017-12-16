package com.goranch.publicapis.ui.food.fragment

import android.app.Dialog
import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.facebook.drawee.view.SimpleDraweeView
import com.goranch.publicapis.R
import com.goranch.publicapis.api.ApiComponent
import com.goranch.publicapis.api.model.food.nutrition.Food
import com.goranch.publicapis.api.model.food.recipe.Recipe
import com.goranch.publicapis.di.ComponentProvider
import com.goranch.publicapis.ui.food.details.DaggerDetailsFoodComponent
import com.goranch.publicapis.ui.food.details.DetailRecipeView
import com.goranch.publicapis.ui.food.details.DetailsFoodModule
import com.goranch.publicapis.ui.food.details.NutritionRecyclerAdapter
import com.goranch.publicapis.ui.food.repository.FoodDataRepositoryImpl
import com.goranch.publicapis.ui.food.viewmodel.FoodViewModel
import com.goranch.publicapis.ui.util.Utils
import com.goranch.publicapis.ui.webview.WebFragment
import java.util.*
import javax.inject.Inject


class DetailsFragment : LifecycleFragment(), DetailRecipeView {
    @BindView(R.id.iv_recipe_img)
    @JvmField
    internal var recipeImage: SimpleDraweeView? = null

    @BindView(R.id.lv_ingredients)
    @JvmField
    internal var ingredientsLinearLayout: LinearLayout? = null

    @JvmField
    @BindView(R.id.tv_publishers_name)
    internal var publishersName: TextView? = null

    @JvmField
    @BindView(R.id.tv_social_rank)
    internal var socialRank: TextView? = null

    @JvmField
    @BindView(R.id.progressBar3)
    internal var progressBar: ProgressBar? = null

    @JvmField
    @Inject
    internal var mDetailRecipeView: DetailRecipeView? = null

    @JvmField
    @Inject
    internal var repository: FoodDataRepositoryImpl? = null

    private var recipeData: Recipe? = null
    private var viewModel: FoodViewModel? = null

    companion object {

        const val URL = "details_food_url"

        fun newInstance(): DetailsFragment {
            return DetailsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiComponent = (activity?.applicationContext as ComponentProvider<ApiComponent>).component
        DaggerDetailsFoodComponent.builder()
                .apiComponent(apiComponent)
                .detailsFoodModule(DetailsFoodModule(this))
                .build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_detail_food, container, false)

        ButterKnife.bind(this, v)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this!!.activity!!).get(FoodViewModel::class.java)

        subscribeToLiveDataChanges()
    }

    private fun subscribeToLiveDataChanges() {
        viewModel?.observableRecipe?.observe(this, Observer<Recipe> { this.loadNewData(it!!) })
        viewModel?.recipeId?.observe(this, Observer<String> { this.getSingleRecipe(it!!) })
        viewModel?.nutritionList?.observe(this, Observer<List<Food>> { this.loadNutritionDetails(it) })
    }

    override fun getSingleRecipe(recipeId: String) {
        if (recipeData?.ingredients == null) {
            showProgress()
            viewModel?.getSingleRecipe(recipeId)
        }
    }

    override fun onItemClicked(item: Food) {
        //do nothing for now
    }

    override fun getNutritionText(food: Food): String {
        if (food != null) {
            return resources.getString(R.string.nutrition_details,
                    food.servingQty?.toInt().toString(),
                    food.foodName?.toString(),
                    food.servingWeightGrams?.toString(),
                    food.nfProtein?.toString(),
                    food.nfCalories?.toString(),
                    food.nfTotalFat?.toString(),
                    food.nfSaturatedFat?.toString(),
                    food.nfCholesterol?.toString(),
                    food.nfSodium?.toString(),
                    food.nfTotalCarbohydrate?.toString(),
                    food.nfDietaryFiber?.toString(),
                    food.nfSugars?.toString())
        } else {
            return resources.getString(R.string.no_nutrition_to_display)
        }
    }

    override fun showHTTPError() {

    }

    private fun loadNewData(recipe: Recipe) {
        hideProgress()
        recipeData = recipe
        activity!!.title = recipeData?.title

        recipeImage?.setImageURI(recipeData?.imageUrl)
        publishersName?.text = recipeData?.publisher
        socialRank?.text = Math.round(java.lang.Double.parseDouble(recipeData?.socialRank?.toString())).toString()

        if (recipe.ingredients != null) {
            val ingredientsAdapter = ArrayAdapter(context, R.layout.simple_list_item_mine, recipe.ingredients!!)
            ingredientsLinearLayout?.removeAllViews()
            for (i in 0..ingredientsAdapter.count - 1) {
                ingredientsLinearLayout?.addView(ingredientsAdapter.getView(i, null, ingredientsLinearLayout!!))
            }
        }
    }

    private fun loadNutritionDetails(foods: List<Food>?) {
        if (foods != null && foods.isNotEmpty()) {
            hideProgress()
            val dialog = Dialog(activity)
            dialog.setContentView(R.layout.nutrition_dialog_layout)
            dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            dialog.setCancelable(true)
            dialog.show()
            dialog.setOnCancelListener { dialog1 -> hideProgress() }

            val recyclerView = dialog.findViewById<RecyclerView>(R.id.nutrition_list)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            val adapter = NutritionRecyclerAdapter(this, foods)
            recyclerView.adapter = adapter

            adapter.notifyDataSetChanged()
            viewModel?.resetNutritionList()
        }
    }


    override fun openWebView(url: String) {
        Utils.openFragment(this!!.activity!!, WebFragment.newInstance(url), true)
    }


    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

    val ingredients: String get() {
            val builder = StringBuilder()
        for (s in recipeData?.ingredients!!) {
                builder.append(s)
            }
            return builder.toString()
        }

    @OnClick(R.id.tv_view_instructions)
    fun openInstructions() {
        openWebView(recipeData?.f2fUrl!!)
    }

    @OnClick(R.id.tv_view_original)
    fun openSource() {
        openWebView(recipeData?.sourceUrl!!)
    }

    @OnClick(R.id.btn_more_details)
    fun showNutritionDetails() {
        showProgress()
        viewModel?.getNaturalLanguageNutritionInfo(ingredients)
        loadNutritionDetails(ArrayList<Food>())
    }
}
