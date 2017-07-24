//package com.goranch.publicapis.ui.food.viewmodel;
//
//import android.arch.lifecycle.LiveData;
//import android.arch.lifecycle.MutableLiveData;
//import android.arch.lifecycle.ViewModel;
//import android.arch.lifecycle.ViewModelProvider;
//import android.support.annotation.NonNull;
//
//import com.goranch.publicapis.api.ApiModule;
//import com.goranch.publicapis.api.model.food.nutrition.Food;
//import com.goranch.publicapis.api.model.food.recipe.Recipe;
//import com.goranch.publicapis.ui.food.FoodDataRepositoryImpl;
//import com.goranch.publicapis.ui.food.IDataRepository;
//
//import java.util.List;
//
//
//public class DetailsViewModel extends ViewModel {
//    private final MutableLiveData<Recipe> observableRecipe = new MutableLiveData<>();
//    private MutableLiveData<List<Food>> observableNutritionList = new MutableLiveData<>();
//    private MutableLiveData<String> observableRecipeID = new MutableLiveData<>();
//    private IDataRepository repository;
//
//    public DetailsViewModel(IDataRepository repository) {
//        this.repository = repository;
//    }
//
//    public LiveData<Recipe> getObservableRecipe() {
//        return observableRecipe;
//    }
//
//    public LiveData<String> getRecipeId() {
//        return observableRecipeID;
//    }
//
//    public LiveData<List<Food>> getNutritionList() {
//        return observableNutritionList;
//    }
//
//    public void getSingleRecipe(String recipeId) {
//        repository.getRecipe(ApiModule.RECIPE_API_KEY, recipeId)
//                .subscribe(observableRecipe::setValue);
//    }
//
//    public void resetNutritionList() {
//        observableNutritionList.setValue(null);
//    }
//
//    public void getNaturalLanguageNutritionInfo(String ingredients) {
//        repository.getNaturalLanguageNutritionInfo(ingredients)
//                .subscribe(observableNutritionList::setValue);
//    }
//
//    //Inject dependencies. It was the preferred way in the example app by Google
//    public static class Factory extends ViewModelProvider.NewInstanceFactory {
//
//        @NonNull
//        private final IDataRepository repository;
//
//        public Factory(@NonNull IDataRepository repository) {
//            this.repository = repository;
//        }
//
//        public Factory(@NonNull FoodDataRepositoryImpl repository) {
//            this.repository = repository;
//        }
//
//        @Override
//        public <T extends ViewModel> T create(Class<T> modelClass) {
//            //noinspection unchecked
//            return (T) new
//            DetailsViewModel(repository);
//        }
//    }
//}
