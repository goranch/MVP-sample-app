package com.goranch.publicapis.ui.food.details

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.facebook.drawee.view.SimpleDraweeView
import com.goranch.publicapis.R
import com.goranch.publicapis.api.model.food.nutrition.Food
import java.util.*

class NutritionRecyclerAdapter(private val view: DetailRecipeView, food: List<Food>) : RecyclerView.Adapter<NutritionRecyclerAdapter.ViewHolder>() {
    private var food: List<Food>? = ArrayList()

    init {
        this.food = food
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.nutrition_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val food = this.food!![position]

        holder.item = food
        holder.title?.text = view.getNutritionText(food)
        holder.image?.setImageURI(food.photo?.thumb)
    }

    override fun getItemCount(): Int {
        if (food != null) {
            return food!!.size
        } else {
            return 0
        }

    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        @BindView(R.id.tv_nutrition)
        @JvmField
        var title: TextView? = null

        @BindView(R.id.img_nutrition)
        @JvmField
        var image: SimpleDraweeView? = null

        var item: Food? = null

        init {
            ButterKnife.bind(this, v)
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {

            view.onItemClicked(item!!)

        }
    }
}