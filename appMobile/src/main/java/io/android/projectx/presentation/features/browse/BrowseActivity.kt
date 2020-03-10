package io.android.projectx.presentation.features.browse

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.model.RecipeView
import io.android.projectx.presentation.base.state.Resource
import io.android.projectx.presentation.base.state.Resource.ResourceState
import io.android.projectx.presentation.di.ViewModelProviderFactory
import io.android.projectx.presentation.features.bookmarked.BookmarkedActivity
import kotlinx.android.synthetic.main.browse_activity.*
import timber.log.Timber
import javax.inject.Inject

class BrowseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var browseAdapter: BrowseAdapter

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var browseViewModel: BrowseRecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.browse_activity)
        browseViewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(BrowseRecipesViewModel::class.java)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getRecipes().observe(this, Observer { it?.let { handleDataState(it) } })
        browseViewModel.fetchRecipes()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_bookmarked -> {
                startActivity(BookmarkedActivity.getStartIntent(this))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupBrowseRecycler() {
        browseAdapter.recipeListener = recipeListener
        recyclerRecipes.layoutManager = LinearLayoutManager(this)
        recyclerRecipes.adapter = browseAdapter
    }

    private fun handleDataState(resource: Resource<List<RecipeView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                setupScreenForSuccess(resource.data)
            }
            ResourceState.LOADING -> {
                progressView.visibility = View.VISIBLE
                recyclerRecipes.visibility = View.GONE
            }
            ResourceState.ERROR -> {
            }
        }
    }

    private fun setupScreenForSuccess(recipes: List<RecipeView>?) {
        progressView.visibility = View.GONE
        recipes?.let {
            browseAdapter.recipes = it
            browseAdapter.notifyDataSetChanged()
            recyclerRecipes.visibility = View.VISIBLE
        } ?: run {

        }
    }

    private val recipeListener = object : RecipeListener {
        override fun onBookmarkedRecipeClicked(recipeId: Long) {
            browseViewModel.unBookmarkRecipe(recipeId)
        }

        override fun onRecipeClicked(recipeId: Long) {
            browseViewModel.bookmarkRecipe(recipeId)
        }
    }

}