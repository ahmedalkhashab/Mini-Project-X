package io.android.projectx.presentation.features.restaurants

import android.os.Bundle
import android.view.View
import android.widget.Filter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.android.projectx.androidextensions.inflate
import io.android.projectx.androidextensions.initVerticalRecycler
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.Adapter
import io.android.projectx.presentation.base.BaseFragment
import io.android.projectx.presentation.base.model.RestaurantView
import io.android.projectx.presentation.base.state.Resource
import io.android.projectx.presentation.base.state.Resource.Status
import io.android.projectx.presentation.extensions.updateVisibility
import kotlinx.android.synthetic.main.restaurants_adapter_item.view.*
import kotlinx.android.synthetic.main.restaurants_fragment.*

class RestaurantsFragment : BaseFragment(R.layout.restaurants_fragment) {

    private lateinit var adapter: Adapter<RestaurantView>
    private val viewModel: RestaurantsViewModel by appViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
        viewModel.getRestaurants().observe(viewLifecycleOwner, { it?.let { handleDataState(it) } })
        viewModel.fetchRestaurants()
    }

    private fun initUi() {
        adapter = Adapter(
            onCreate = { parent, _ -> Adapter.ViewHolder(parent.inflate(R.layout.restaurants_adapter_item)) },
            onClick = { _, item -> onClick(item) },
            onBind = { _, item, view, _ -> onBind(item, view) },
            filter = object : Filter() {
                override fun performFiltering(constraint: CharSequence): FilterResults {
                    val sequence = constraint.toString()
                    if (sequence.isEmpty()) adapter.filteredList = adapter.items
                    else {
                        val fList: MutableList<RestaurantView> = ArrayList()
                        for (name in adapter.items) {
                            if (name.title.toLowerCase().contains(sequence.toLowerCase()))
                                fList.add(name)
                            adapter.filteredList = fList
                        }
                    }
                    val results = FilterResults()
                    results.values = adapter.filteredList
                    return results
                }

                override fun publishResults(constraint: CharSequence, results: FilterResults) {
                    adapter.filteredList = results.values as MutableList<RestaurantView>
                    adapter.notifyDataSetChanged()
                }
            }
        )
        recyclerRestaurants.initVerticalRecycler(adapter)
    }

    private fun <T> onClick(item: T) {
        val restaurant = item as RestaurantView
    }

    private fun <T> onBind(item: T, view: View) {
        val restaurant = item as RestaurantView
        view.tvRestaurantName.text = restaurant.title
        view.tvDescription.text = restaurant.description
        Glide.with(requireContext())
            .load(restaurant.urlToImage)
            .apply(RequestOptions.circleCropTransform())
            .into(view.restaurantImage)
    }

    private fun handleDataState(resource: Resource<List<RestaurantView>?>) {
        progressbar.updateVisibility(resource.status)
        when (resource.status) {
            Status.SUCCESS -> resource.data?.let { adapter.items = it.toMutableList() }
            Status.LOADING -> {
            }
            Status.ERROR -> {
            }
        }
    }

}