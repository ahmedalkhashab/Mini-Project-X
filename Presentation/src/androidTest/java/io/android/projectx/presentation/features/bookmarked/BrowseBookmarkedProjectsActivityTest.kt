package io.android.projectx.presentation.features.bookmarked

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.nhaarman.mockitokotlin2.whenever
import io.android.projectx.domain.model.Recipe
import io.android.projectx.presentation.R
import io.android.projectx.presentation.test.TestApplication
import io.android.projectx.presentation.test.factory.RecipeDataFactory
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrowseBookmarkedRecipesActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<BookmarkedActivity>(BookmarkedActivity::class.java, false,
        false)

    @Test
    fun activityLaunches() {
        stubRecipesRepositoryGetBookmarkedRecipes(
            Observable.just(listOf(
            RecipeDataFactory.makeRecipe())))
        activity.launchActivity(null)
    }

    @Test
    fun bookmarkedRecipesDisplay() {
        val recipes = listOf(RecipeDataFactory.makeRecipe(), RecipeDataFactory.makeRecipe(),
            RecipeDataFactory.makeRecipe(), RecipeDataFactory.makeRecipe())
        stubRecipesRepositoryGetBookmarkedRecipes(Observable.just(recipes))

        activity.launchActivity(null)

        recipes.forEachIndexed { index, recipe ->
            onView(withId(R.id.recyclerRecipes))
                .perform(
                    RecyclerViewActions.scrollToPosition<BookmarkedAdapter.ViewHolder>(
                    index))

            onView(withId(R.id.recyclerRecipes))
                .check(matches(hasDescendant(withText(recipe.title))))
        }
    }

    private fun stubRecipesRepositoryGetBookmarkedRecipes(observable: Observable<List<Recipe>>) {
        /*whenever(TestApplication.appComponent().recipesRepository().getBookmarkedRecipes())
            .thenReturn(observable)*/
    }

}