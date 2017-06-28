package com.goranch.publicapis.ui.food.details;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.goranch.publicapis.R;
import com.goranch.publicapis.api.model.food.nutrition.Food;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NutritionRecyclerAdapter extends RecyclerView.Adapter<NutritionRecyclerAdapter.ViewHolder> {
    private List<Food> recipes = new ArrayList<>();
    private DetailRecipeView view;

    public NutritionRecyclerAdapter(DetailRecipeView view, List<Food> recipes) {
        this.recipes = recipes;
        this.view = view;
    }

    public void setRecipes(List<Food> recipes) {
        this.recipes = recipes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nutrition_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Food food = recipes.get(position);

        holder.item = food;
        holder.title.setText(food.getFoodName());
        holder.image.setImageURI(Uri.parse(food.getPhoto().getThumb()));
    }

    @Override
    public int getItemCount() {
        if (recipes != null) {
            return recipes.size();
        } else {
            return 0;
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_nutrition)
        public TextView title;

        @BindView(R.id.img_nutrition)
        public SimpleDraweeView image;

        Food item;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            view.onItemClicked(item);

        }
    }
}