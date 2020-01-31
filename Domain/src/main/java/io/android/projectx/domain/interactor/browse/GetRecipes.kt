package io.android.projectx.domain.interactor.browse

import io.android.projectx.domain.executor.PostExecutionThread
import io.android.projectx.domain.interactor.ObservableUseCase
import io.android.projectx.domain.model.Recipe
import io.android.projectx.domain.repository.RecipesRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetRecipes @Inject constructor(
    private val recipesRepository: RecipesRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Recipe>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Recipe>> {
        return recipesRepository.getRecipes()
    }

}