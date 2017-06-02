package com.goranch.publicapis.ui.food.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.goranch.publicapis.api.ApiModule;
import com.goranch.publicapis.api.model.food.Recipe;
import com.goranch.publicapis.ui.food.IDataRepository;

import java.util.List;

/**
 * Created by goran on 21/05/2017.
 */

public class FoodViewModel extends ViewModel implements IFoodViewModel {
    private static final String TAG = FoodViewModel.class.getSimpleName();
    private MutableLiveData<List<Recipe>> recipes;
    private IDataRepository repository;

    public FoodViewModel(IDataRepository foodDataRepository) {
        this.repository = foodDataRepository;
        recipes = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<List<Recipe>> getRecipes(String query) {

        rx.Observable<List<Recipe>> getRecipesObservable = repository.searchRecipes(ApiModule.API_KEY, query);

        //load data so it can be refreshed in the UI
        getRecipesObservable.subscribe(recipeList -> recipes.setValue(recipeList));

        return recipes;
    }

    //Inject dependencies
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final IDataRepository repository;

        public Factory(@NonNull IDataRepository repository) {
            this.repository = repository;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new FoodViewModel(repository);
        }
    }
}
