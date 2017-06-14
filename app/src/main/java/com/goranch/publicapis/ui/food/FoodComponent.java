package com.goranch.publicapis.ui.food;

import com.goranch.publicapis.api.ApiComponent;
import com.goranch.publicapis.di.scopes.FoodScope;
import com.goranch.publicapis.ui.food.fragment.FoodFragment;

import dagger.Component;

/**
 * Created by goran on 21/09/2016.
 */

@FoodScope
@Component(dependencies = ApiComponent.class, modules = FoodModule.class)
public interface FoodComponent {
    void inject(FoodFragment foodFragment);
}
