package io.android.projectx.presentation.features.restaurants

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.model.RestaurantView
import io.android.projectx.presentation.di.ViewModelProviderFactory
import io.android.projectx.presentation.features.bookmarked.BookmarkedActivity
import io.android.projectx.presentation.base.state.Resource
import io.android.projectx.presentation.base.state.ResourceState
import kotlinx.android.synthetic.main.restaurants_activity.*
import javax.inject.Inject

class RestaurantsActivity : AppCompatActivity() {

    @Inject
    lateinit var restaurantsAdapter: RestaurantsAdapter
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var restaurantsViewModel: RestaurantsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurants_activity)
        AndroidInjection.inject(this)
        restaurantsViewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(RestaurantsViewModel::class.java)
        setupRestaurantsRecycler()
    }

    override fun onStart() {
        super.onStart()
        restaurantsViewModel.getRestaurants().observe(this,
            Observer<Resource<List<RestaurantView>>> { it?.let { handleDataState(it) } })
        restaurantsViewModel.fetchRestaurants()
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

    private fun setupRestaurantsRecycler() {
        restaurantsAdapter.restaurantListener = restaurantListener
        recyclerRestaurants.layoutManager = LinearLayoutManager(this)
        recyclerRestaurants.adapter = restaurantsAdapter
    }

    private fun handleDataState(resource: Resource<List<RestaurantView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                setupScreenForSuccess(resource.data)
            }
            ResourceState.LOADING -> {
                progressView.visibility = View.VISIBLE
                recyclerRestaurants.visibility = View.GONE
            }
            ResourceState.ERROR -> {
            }
        }
    }

    private fun setupScreenForSuccess(restaurants: List<RestaurantView>?) {
        progressView.visibility = View.GONE
        restaurants?.let {
            restaurantsAdapter.restaurants = it
            restaurantsAdapter.notifyDataSetChanged()
            recyclerRestaurants.visibility = View.VISIBLE
        } ?: run {

        }
    }

    private val restaurantListener = object : RestaurantListener {
        override fun onRestaurantClicked(restaurantId: Long) {
        }
    }

}