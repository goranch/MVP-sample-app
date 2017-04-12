package com.goranch.publicapis.ui.food.details;

import com.goranch.publicapis.api.ApiComponent;
import com.goranch.publicapis.di.scopes.FoodDetailScope;
import com.goranch.publicapis.ui.food.fragment.DetailsFragment;

import dagger.Component;

/**
 * Created by goran on 06/04/2017.
 */

@FoodDetailScope
@Component(dependencies = ApiComponent.class, modules = DetailsFoodModule.class)
public interface DetailsFoodComponent {
    void inject(DetailsFragment detailsFragment);
}
