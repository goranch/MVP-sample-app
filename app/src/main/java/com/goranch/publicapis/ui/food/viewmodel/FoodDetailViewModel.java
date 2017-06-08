package com.goranch.publicapis.ui.food.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.goranch.publicapis.api.ApiModule;
import com.goranch.publicapis.api.model.food.Recipe;
import com.goranch.publicapis.ui.food.IDataRepository;

public class FoodDetailViewModel extends ViewModel {
    private final MutableLiveData<Recipe> recipeObservable = new MutableLiveData<>();
    private IDataRepository repository;

    public FoodDetailViewModel(IDataRepository foodDataRepository) {
        this.repository = foodDataRepository;
    }

    public void getRecipe(String recipeId) {
        repository.getRecipe(ApiModule.API_KEY, recipeId)
                .subscribe(recipeObservable::setValue);
    }

    public MutableLiveData<Recipe> getObservableRecipe() {
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
