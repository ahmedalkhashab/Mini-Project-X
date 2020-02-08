package io.android.projectx.presentation.features.bookmarked

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import io.android.projectx.presentation.R
import io.android.projectx.presentation.di.ViewModelFactory
import io.android.projectx.presentation.mapper.RecipeViewMapper
import io.android.projectx.presentation.model.RecipeView
import io.android.projectx.presentation.state.Resource
import io.android.projectx.presentation.state.ResourceState
import javax.inject.Inject

class BookmarkedActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: BookmarkedAdapter
    @Inject
    lateinit var mapper: RecipeViewMapper
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var browseViewModel: BrowseBookmarkedRecipesViewModel

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, BookmarkedActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarked)
        AndroidInjection.inject(this)

        browseViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BrowseBookmarkedRecipesViewModel::class.java)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getBookmarkedRecipes().observe(this,
            Observer<Resource<List<RecipeView>>> {
                it?.let {
                    handleDataState(it)
                }
            })
        browseViewModel.fetchBookmarkedRecipes()
    }

    private fun setupBrowseRecycler() {
        recycler_recipes.layoutManager = LinearLayoutManager(this)
        recycler_recipes.adapter = adapter
    }

    private fun handleDataState(resource: Resource<List<RecipeView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                progress.visibility = View.GONE
                recycler_recipes.visibility = View.VISIBLE
                resource.data?.let {
                    adapter.recipes = it
                    adapter.notifyDataSetChanged()
                }
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                recycler_recipes.visibility = View.GONE
            }
        }
    }
}