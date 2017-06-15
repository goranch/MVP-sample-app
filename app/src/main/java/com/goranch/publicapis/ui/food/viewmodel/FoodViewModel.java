package com.goranch.publicapis.ui.food.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.goranch.publicapis.api.ApiModule;
import com.goranch.publicapis.api.model.food.Recipe;
import com.goranch.publicapis.ui.food.IDataRepository;
import com.goranch.publicapis.ui.food.SearchRecipeView;

import java.util.List;

/**
 * Created by goran on 21/05/2017.
 */

public class FoodViewModel extends ViewModel implements IFoodViewModel {
    private static final String TAG = FoodViewModel.class.getSimpleName();
    private MutableLiveData<List<Recipe>> observableRecipes = new MutableLiveData<>();
    private IDataRepository repository;
    private SearchRecipeView view;

    public FoodViewModel(IDataRepository foodDataRepository, SearchRecipeView view) {
        this.repository = foodDataRepository;
        this.view = view;
    }

    @Override
    public void getRecipes(String query) {
        repository.searchRecipes(ApiModule.API_KEY, query)
                .subscribe(observableRecipes::setValue);
    }

    @Override
    public void onItemClicked(Recipe item) {
        view.openDetailsFragment(item);
    }

    public MutableLiveData<List<Recipe>> getObservableRecipes() {
        return observableRecipes;
    }

    //Inject dependencies. It was the preferred way in the example app by Google
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final IDataRepository repository;

        //TODO check if its a good idea to hold a reference to the fragment here
        @NonNull
        private final SearchRecipeView view;

        public Factory(@NonNull IDataRepository repository,
                       @NonNull SearchRecipeView view) {
            this.repository = repository;
            this.view = view;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new FoodViewModel(repository, view);
        }
    }
}
