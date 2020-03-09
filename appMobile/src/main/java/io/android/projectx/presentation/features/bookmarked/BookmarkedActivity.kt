package io.android.projectx.presentation.features.bookmarked

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import io.android.projectx.presentation.R
import io.android.projectx.presentation.di.ViewModelProviderFactory
import io.android.projectx.presentation.base.model.RecipeView
import io.android.projectx.presentation.base.state.Resource
import io.android.projectx.presentation.base.state.ResourceState
import kotlinx.android.synthetic.main.bookmarked_activity.*
import javax.inject.Inject

class BookmarkedActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: BookmarkedAdapter
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewModel: BookmarkedRecipesViewModel

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, BookmarkedActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bookmarked_activity)
        AndroidInjection.inject(this)

        viewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(BookmarkedRecipesViewModel::class.java)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getBookmarkedRecipes().observe(this,
            Observer<Resource<List<RecipeView>>> {
                it?.let {
                    handleDataState(it)
                }
            })
        viewModel.fetchBookmarkedRecipes()
    }

    private fun setupBrowseRecycler() {
        recyclerRecipes.layoutManager = LinearLayoutManager(this)
        recyclerRecipes.adapter = adapter
    }

    private fun handleDataState(resource: Resource<List<RecipeView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                progressView.visibility = View.GONE
                recyclerRecipes.visibility = View.VISIBLE
                resource.data?.let {
                    adapter.recipes = it
                    adapter.notifyDataSetChanged()
                }
            }
            ResourceState.LOADING -> {
                progressView.visibility = View.VISIBLE
                recyclerRecipes.visibility = View.GONE
            }
            ResourceState.ERROR -> {
            }
        }
    }
}