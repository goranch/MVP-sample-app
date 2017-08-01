package com.goranch.publicapis.ui.food

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.facebook.drawee.view.SimpleDraweeView
import com.goranch.publicapis.R
import com.goranch.publicapis.api.model.food.recipe.Recipe
import java.util.*

class RecipeRecyclerAdapter(private val view: SearchRecipeView, recipes: List<Recipe>) : RecyclerView.Adapter<RecipeRecyclerAdapter.ViewHolder>() {
    private var recipes: List<Recipe>? = ArrayList()

    init {
        this.recipes = recipes
    }

    fun setRecipes(recipes: List<Recipe>) {
        this.recipes = recipes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recipe_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val recipe = recipes!![position]

        holder.mItem = recipe
        holder.title!!.text = recipe.title
        holder.image!!.setImageURI(Uri.parse(recipe.imageUrl))
    }

    override fun getItemCount(): Int {
        if (recipes != null) {
            return recipes!!.size
        } else {
            return 0
        }

    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        @BindView(R.id.tv_name)
        @JvmField
        var title: TextView? = null

        @BindView(R.id.recipe_image)
        @JvmField
        var image: SimpleDraweeView? = null

        var mItem: Recipe? = null

        init {
            ButterKnife.bind(this, v)
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {

            view.onItemClicked(mItem!!)

        }
    }
}