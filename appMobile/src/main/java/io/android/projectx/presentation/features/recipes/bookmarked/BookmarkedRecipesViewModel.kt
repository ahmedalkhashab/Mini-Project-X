package io.android.projectx.presentation.features.recipes.bookmarked

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.android.projectx.domain.features.recipes.interactor.bookmarked.GetBookmarkedRecipes
import io.android.projectx.domain.features.recipes.model.Recipe
import io.android.projectx.presentation.base.mapper.RecipeViewMapper
import io.android.projectx.presentation.base.model.RecipeView
import io.android.projectx.presentation.base.state.Resource
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BookmarkedRecipesViewModel @Inject constructor(
    private val getBookmarkedRecipes: GetBookmarkedRecipes,
    private val mapper: RecipeViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<RecipeView>?>> = MutableLiveData()

    override fun onCleared() {
        getBookmarkedRecipes.dispose()
        super.onCleared()
    }

    fun getBookmarkedRecipes(): LiveData<Resource<List<RecipeView>?>> {
        return liveData
    }

    fun fetchBookmarkedRecipes() {
        liveData.postValue(Resource.loading())
        return getBookmarkedRecipes.execute(RecipesSubscriber())
    }

    inner class RecipesSubscriber : DisposableObserver<List<Recipe>>() {

        override fun onNext(t: List<Recipe>) {
            liveData.postValue(Resource.success(t.map { mapper.mapToView(it) }))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource.error(ex = e))
        }

        override fun onComplete() {}

    }

}