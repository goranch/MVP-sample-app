package com.goranch.publicapis.ui.food;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.goranch.publicapis.R;
import com.goranch.publicapis.api.model.food.Recipe;
import com.goranch.publicapis.ui.food.viewmodel.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by goranch on 30/03/16.
 */
public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecipeRecyclerAdapter.ViewHolder> {
    private List<Recipe> recipes = new ArrayList<>();
    private FoodViewModel viewModel;

    public RecipeRecyclerAdapter(FoodViewModel viewModel, List<Recipe> recipes) {
        this.recipes = recipes;
        this.viewModel = viewModel;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Recipe recipe = recipes.get(position);

        holder.mItem = recipe;
        holder.title.setText(recipe.getTitle());
        holder.image.setImageURI(Uri.parse(recipe.getImageUrl()));
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
        @Bind(R.id.tv_name)
        public TextView title;

        @Bind(R.id.recipe_image)
        public SimpleDraweeView image;

        Recipe mItem;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            viewModel.onItemClicked(mItem);

        }
    }
}