package com.goranch.publicapis.ui.food.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.goranch.publicapis.api.ApiModule;
import com.goranch.publicapis.api.model.food.Recipe;
import com.goranch.publicapis.ui.food.IDataRepository;

/**
 * Created by goran on 01/06/2017.
 */

public class FoodDetailViewModel extends ViewModel {
    private final MutableLiveData<Object> recipeObservable;
    private IDataRepository repository;

    public FoodDetailViewModel(IDataRepository foodDataRepository) {
        this.repository = foodDataRepository;
        recipeObservable = new MutableLiveData<>();
    }

    public MutableLiveData<Object> getRecipe(String recipeId) {
        rx.Observable<Recipe> getRecipeObservable = repository.getRecipe(ApiModule.API_KEY, recipeId);

        getRecipeObservable.subscribe(recipeObservable::setValue);

        return recipeObservable;

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
            return (T) new FoodDetailViewModel(repository);
        }
    }
}
