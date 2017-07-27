package com.goranch.publicapis.ui.food.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.goranch.publicapis.api.ApiModule;
import com.goranch.publicapis.api.model.food.nutrition.Food;
import com.goranch.publicapis.api.model.food.recipe.Recipe;
import com.goranch.publicapis.ui.food.repository.FoodDataRepositoryImpl;
import com.goranch.publicapis.ui.food.repository.IDataRepository;

import java.util.List;

public class FoodViewModel extends ViewModel implements IFoodViewModel {
    private static final String TAG = FoodViewModel.class.getSimpleName();
    private final MutableLiveData<Recipe> observableRecipe = new MutableLiveData<>();
    private MutableLiveData<List<Recipe>> observableRecipeList = new MutableLiveData<>();
    private MutableLiveData<List<Food>> observableNutritionList = new MutableLiveData<>();
    private MutableLiveData<String> observableRecipeID = new MutableLiveData<>();
    private IDataRepository repository;

    private FoodViewModel(IDataRepository foodDataRepository) {
        this.repository = foodDataRepository;
    }

    @Override
    public void getRecipes(String query) {
        repository.searchRecipes(ApiModule.Companion.getRECIPE_API_KEY(), query)
                .subscribe(observableRecipeList::setValue);
    }

    @Override
    public void getSingleRecipe(String recipeId) {
        repository.getRecipe(ApiModule.Companion.getRECIPE_API_KEY(), recipeId)
                .subscribe(observableRecipe::setValue);
    }

    @Override
    public void getNaturalLanguageNutritionInfo(String ingredients) {
        repository.getNaturalLanguageNutritionInfo(ingredients)
                .subscribe(observableNutritionList::setValue);
    }

    @Override
    public void onItemClicked(Recipe item) {
        observableRecipe.setValue(item);
        observableRecipeID.setValue(item.getRecipeId());
    }

    public MutableLiveData<List<Recipe>> getObservableRecipeList() {
        return observableRecipeList;
    }

    public MutableLiveData<Recipe> getObservableRecipe() {
        return observableRecipe;
    }

    public MutableLiveData<String> getRecipeId() {
        return observableRecipeID;
    }

    public MutableLiveData<List<Food>> getNutritionList() {
        return observableNutritionList;
    }

    public void resetNutritionList() {
        observableNutritionList.setValue(null);
    }

    //Inject dependencies. It was the preferred way in the example app by Google
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final IDataRepository repository;

        public Factory(@NonNull IDataRepository repository) {
            this.repository = repository;
        }

        public Factory(@NonNull FoodDataRepositoryImpl repository) {
            this.repository = repository;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new FoodViewModel(repository);
        }
    }
}
