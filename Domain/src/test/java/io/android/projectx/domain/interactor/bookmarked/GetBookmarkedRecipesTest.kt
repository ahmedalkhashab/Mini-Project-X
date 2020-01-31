package io.android.projectx.domain.interactor.bookmarked

import com.nhaarman.mockitokotlin2.whenever
import io.android.projectx.domain.executor.PostExecutionThread
import io.android.projectx.domain.model.Recipe
import io.android.projectx.domain.repository.RecipesRepository
import io.android.projectx.domain.test.RecipeDataFactory
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkedRecipesTest {

    private lateinit var getBookmarkedRecipes: GetBookmarkedRecipes
    @Mock
    lateinit var recipesRepository: RecipesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedRecipes = GetBookmarkedRecipes(recipesRepository, postExecutionThread)
    }

    @Test
    fun getBookmarkedRecipesCompletes() {
        stubGetRecipes(Observable.just(RecipeDataFactory.makeRecipesList(3)))
        val testObserver = getBookmarkedRecipes.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedRecipesReturnsData() {
        val recipes = RecipeDataFactory.makeRecipesList(3)
        stubGetRecipes(Observable.just(recipes))
        val testObserver = getBookmarkedRecipes.buildUseCaseObservable().test()
        testObserver.assertValue(recipes)
    }

    private fun stubGetRecipes(observable: Observable<List<Recipe>>) {
        whenever(recipesRepository.getBookmarkedRecipes())
            .thenReturn(observable)
    }

}