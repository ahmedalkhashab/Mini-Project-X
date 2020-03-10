package io.android.projectx.presentation.features.browse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.android.projectx.domain.features.recipes.interactor.bookmarked.BookmarkRecipe
import io.android.projectx.domain.features.recipes.interactor.bookmarked.UnBookmarkRecipe
import io.android.projectx.domain.features.recipes.interactor.browse.GetRecipes
import io.android.projectx.domain.features.recipes.model.Recipe
import io.android.projectx.presentation.base.mapper.RecipeViewMapper
import io.android.projectx.presentation.base.model.RecipeView
import io.android.projectx.presentation.base.state.Resource
import io.android.projectx.presentation.base.state.Resource.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseRecipesViewModel @Inject constructor(
    private val getRecipes: GetRecipes?,
    private val bookmarkRecipe: BookmarkRecipe,
    private val unBookmarkRecipe: UnBookmarkRecipe,
    private val mapper: RecipeViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<RecipeView>>> = MutableLiveData()

    override fun onCleared() {
        getRecipes?.dispose()
        super.onCleared()
    }

    fun getRecipes(): LiveData<Resource<List<RecipeView>>> {
        return liveData
    }

    fun fetchRecipes() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getRecipes?.execute(RecipesSubscriber())
    }

    fun bookmarkRecipe(recipeId: Long) {
        return bookmarkRecipe.execute(
            BookmarkProjectsSubscriber(),
            BookmarkRecipe.Params.forRecipe(recipeId)
        )
    }

    fun unBookmarkRecipe(recipeId: Long) {
        return unBookmarkRecipe.execute(
            BookmarkProjectsSubscriber(),
            UnBookmarkRecipe.Params.forRecipe(recipeId)
        )
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

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

    inner class BookmarkProjectsSubscriber : DisposableCompletableObserver() {

        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(
                Resource(
                    ResourceState.ERROR,
                    liveData.value?.data,
                    e.localizedMessage
                )
            )
        }

    }

}