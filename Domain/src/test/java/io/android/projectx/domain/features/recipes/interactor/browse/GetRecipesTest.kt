package io.android.projectx.domain.features.recipes.interactor.browse

import com.nhaarman.mockitokotlin2.whenever
import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.features.recipes.model.Recipe
import io.android.projectx.domain.features.recipes.repository.RecipesRepository
import io.android.projectx.domain.test.factory.RecipeDataFactory
import io.reactivex.Observable
import org.mockito.Mock
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class GetRecipesTest {
    private lateinit var getRecipes: GetRecipes
    @Mock
    lateinit var recipesRepository: RecipesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getRecipes = GetRecipes(recipesRepository, postExecutionThread)
    }

    @Test
    fun getRecipesCompletes() {
        stubGetRecipes(Observable.just(RecipeDataFactory.makeRecipesList(3)))
        val testObserver = getRecipes.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getRecipesReturnsData() {
        val recipes = RecipeDataFactory.makeRecipesList(3)
        stubGetRecipes(Observable.just(recipes))
        val testObserver = getRecipes.buildUseCaseObservable().test()
        testObserver.assertValue(recipes)
    }

    private fun stubGetRecipes(observable: Observable<List<Recipe>>) {
        whenever(recipesRepository.getRecipes())
            .thenReturn(observable)
    }

}