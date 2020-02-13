package io.android.projectx.domain.features.recipes.interactor.bookmarked

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.features.recipes.repository.RecipesRepository
import io.android.projectx.domain.test.RecipeDataFactory
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.IllegalArgumentException

class BookmarkRecipeTest {

    private lateinit var bookmarkRecipe: BookmarkRecipe
    @Mock lateinit var recipesRepository: RecipesRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        bookmarkRecipe = BookmarkRecipe(recipesRepository,postExecutionThread)
    }

    @Test
    fun bookmarkRecipeCompletes(){
        stubBookmarkRecipe(Completable.complete())
        val testObserver = bookmarkRecipe.buildUseCaseCompletable(
            BookmarkRecipe.Params.forRecipe(RecipeDataFactory.uniqueId())
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkRecipeThrowException(){
        bookmarkRecipe.buildUseCaseCompletable().test()
    }

    private fun stubBookmarkRecipe(completable: Completable){
        whenever(recipesRepository.bookmarkRecipe(any()))
            .thenReturn(completable)
    }

}