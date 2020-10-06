package io.android.projectx.domain.features.recipes.interactor.browse

import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.base.interactor.ObservableUseCase
import io.android.projectx.domain.features.recipes.model.Recipe
import io.android.projectx.domain.features.recipes.repository.RecipesRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetRecipes @Inject constructor(
    private val recipesRepository: RecipesRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Recipe>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Recipe>> {
        // todo - move parameters
        return recipesRepository.getRecipes(0, 10)
    }

}