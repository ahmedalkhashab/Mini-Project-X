package io.android.projectx.presentation.features.browse

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import io.android.projectx.domain.features.recipes.model.Recipe
import io.android.projectx.presentation.R
import io.android.projectx.presentation.test.factory.RecipeDataFactory
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrowseRecipesActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<BrowseActivity>(BrowseActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        stubRecipesRepositoryGetRecipes(Observable.just(listOf(RecipeDataFactory.makeRecipe())))
        activity.launchActivity(null)
    }

    @Test
    fun recipesDisplay() {
        val recipes = listOf(RecipeDataFactory.makeRecipe(),
            RecipeDataFactory.makeRecipe(), RecipeDataFactory.makeRecipe())
        stubRecipesRepositoryGetRecipes(Observable.just(recipes))
        activity.launchActivity(null)

        recipes.forEachIndexed { index, recipe ->
            onView(withId(R.id.recyclerRecipes))
                .perform(RecyclerViewActions.scrollToPosition<BrowseAdapter.ViewHolder>(index))

            onView(withId(R.id.recyclerRecipes))
                .check(matches(hasDescendant(withText(recipe.title))))
        }
    }

    private fun stubRecipesRepositoryGetRecipes(observable: Observable<List<Recipe>>) {
/*
        whenever(TestApplication.appComponent().recipesRepository().getRecipes())
            .thenReturn(observable)
*/
    }
}