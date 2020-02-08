package io.android.projectx.presentation.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.android.projectx.domain.interactor.bookmarked.GetBookmarkedRecipes
import io.android.projectx.domain.model.Recipe
import io.android.projectx.presentation.mapper.RecipeViewMapper
import io.android.projectx.presentation.model.RecipeView
import io.android.projectx.presentation.state.Resource
import io.android.projectx.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseBookmarkedRecipesViewModel @Inject constructor(
    private val getBookmarkedRecipes: GetBookmarkedRecipes,
    private val mapper: RecipeViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<RecipeView>>> = MutableLiveData()

    override fun onCleared() {
        getBookmarkedRecipes.dispose()
        super.onCleared()
    }

    fun getBookmarkedRecipes(): LiveData<Resource<List<RecipeView>>> {
        return liveData
    }

    fun fetchBookmarkedRecipes() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getBookmarkedRecipes.execute(RecipesSubscriber())
    }

    inner class RecipesSubscriber : DisposableObserver<List<Recipe>>() {

        override fun onNext(t: List<Recipe>) {
            liveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) },
                    null
                )
            )
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

        override fun onComplete() {}

    }

}