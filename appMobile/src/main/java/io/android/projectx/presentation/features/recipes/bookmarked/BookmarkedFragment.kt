package io.android.projectx.presentation.features.recipes.bookmarked

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
import io.android.projectx.presentation.base.model.RecipeView
import io.android.projectx.presentation.base.state.Resource
import io.android.projectx.presentation.base.state.Resource.Status
import io.android.projectx.presentation.extensions.updateVisibility
import kotlinx.android.synthetic.main.bookmarked_adapter_item_bookmarked_recipe.view.*
import kotlinx.android.synthetic.main.bookmarked_fragment.*

class BookmarkedFragment : BaseFragment(R.layout.bookmarked_fragment) {

    private val viewModel: BookmarkedRecipesViewModel by appViewModels()
    private lateinit var adapter: Adapter<RecipeView>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupBrowseRecycler()
        viewModel.getBookmarkedRecipes()
            .observe(viewLifecycleOwner, { it?.let { handleDataState(it) } })
        viewModel.fetchBookmarkedRecipes()
    }

    private fun setupBrowseRecycler() {
        adapter = Adapter(
            onCreate = { parent, _ -> Adapter.ViewHolder(parent.inflate(R.layout.bookmarked_adapter_item_bookmarked_recipe)) },
            onClick = { _, item -> onClick(item) },
            onBind = { _, item, view, _ -> onBind(item, view) },
            filter = object : Filter() {
                override fun performFiltering(constraint: CharSequence): FilterResults {
                    val sequence = constraint.toString()
                    if (sequence.isEmpty()) adapter.filteredList = adapter.items
                    else {
                        val fList: MutableList<RecipeView> = ArrayList()
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
                    adapter.filteredList = results.values as MutableList<RecipeView>
                    adapter.notifyDataSetChanged()
                }
            }
        )
        recyclerRecipes.initVerticalRecycler(adapter)
    }

    private fun <T> onClick(item: T) {
        val recipe = item as RecipeView
    }

    private fun <T> onBind(item: T, view: View) {
        val recipe = item as RecipeView
        view.tvRecipeName.text = recipe.title
        Glide.with(requireContext())
            .load(recipe.urlToImage)
            .apply(RequestOptions.circleCropTransform())
            .into(view.recipeImage)
    }


    private fun handleDataState(resource: Resource<List<RecipeView>?>) {
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