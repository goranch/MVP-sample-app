package com.goranch.publicapis.ui.food.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.goranch.publicapis.api.ApiModule;
import com.goranch.publicapis.api.model.food.nutrition.Food;
import com.goranch.publicapis.api.model.food.recipe.Recipe;
import com.goranch.publicapis.ui.food.FoodDataRepositoryImpl;
import com.goranch.publicapis.ui.food.IDataRepository;
import com.goranch.publicapis.ui.food.SearchRecipeView;

import java.util.List;

public class FoodViewModel extends ViewModel implements IFoodViewModel {
    private static final String TAG = FoodViewModel.class.getSimpleName();
    private final MutableLiveData<Recipe> observableRecipe = new MutableLiveData<>();
    private MutableLiveData<List<Recipe>> observableRecipeList = new MutableLiveData<>();
    private MutableLiveData<List<Food>> observableNutritionList = new MutableLiveData<>();
    private MutableLiveData<String> observableRecipeID = new MutableLiveData<>();
    private IDataRepository repository;
    private SearchRecipeView view;

    private FoodViewModel(IDataRepository foodDataRepository, SearchRecipeView view) {
        this.repository = foodDataRepository;
        this.view = view;
    }

    @Override
    public void getRecipes(String query) {
        repository.searchRecipes(ApiModule.RECIPE_API_KEY, query)
                .subscribe(observableRecipeList::setValue);
        view.showProgress();
    }

    @Override
    public void getSingleRecipe(String recipeId) {
        repository.getRecipe(ApiModule.RECIPE_API_KEY, recipeId)
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
        view.openDetailsFragment();
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

        //TODO check if its a good idea to hold a reference to the fragment here
        private SearchRecipeView view;

        public Factory(@NonNull IDataRepository repository,
                       @NonNull SearchRecipeView view) {
            this.repository = repository;
            this.view = view;
        }

        public Factory(@NonNull FoodDataRepositoryImpl repository) {
            this.repository = repository;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new FoodViewModel(repository, view);
        }
    }
}
