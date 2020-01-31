package io.android.projectx.domain.interactor.bookmarked

import io.android.projectx.domain.executor.PostExecutionThread
import io.android.projectx.domain.interactor.CompletableUseCase
import io.android.projectx.domain.repository.RecipesRepository
import io.reactivex.Completable
import java.lang.IllegalArgumentException
import javax.inject.Inject

open class BookmarkRecipe @Inject constructor(
    private val recipesRepository: RecipesRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<BookmarkRecipe.Params>(postExecutionThread) {
    public override fun buildUseCaseCompletable(params: BookmarkRecipe.Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null !!")
        return recipesRepository.bookmarkRecipe(params.recipeId)
    }

    data class Params constructor(val recipeId: Long) {
        companion object {
            fun forRecipe(recipeId: Long): Params {
                return Params(recipeId)
            }
        }
    }

}