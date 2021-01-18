package io.android.projectx.presentation.features.recipes.browse

import android.os.Bundle
import android.view.*
import android.widget.Filter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.android.projectx.androidextensions.*
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.Adapter
import io.android.projectx.presentation.base.BaseFragment
import io.android.projectx.presentation.base.model.RecipeView
import io.android.projectx.presentation.base.state.Resource
import io.android.projectx.presentation.base.state.Resource.Status
import io.android.projectx.presentation.extensions.getError
import io.android.projectx.presentation.extensions.updateVisibility
import kotlinx.android.synthetic.main.browse_adapter_item_recipe.view.*
import kotlinx.android.synthetic.main.browse_fragment.*
import java.util.*
import kotlin.collections.ArrayList


class BrowseFragment : BaseFragment(R.layout.browse_fragment) {

    private lateinit var adapter: Adapter<RecipeView>
    private val viewModel: BrowseRecipesViewModel by appViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        subscribeObservers()
        initUi()
        viewModel.fetchRecipes()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_menu -> {
                navigator.toMenuScreen(view?.findNavController())
                true
            }
            R.id.action_bookmarked -> {
                navigator.toBookmarkedRecipesScreen(view?.findNavController(),
                    requireContext().isRTL(R.bool.is_rtl))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeObservers() {
        viewModel.getRecipes()
            .observe(viewLifecycleOwner, { it?.let { handleDataState(it) } })
    }

    private fun initUi() {
        adapter = Adapter(
            onCreate = { parent, _ -> Adapter.ViewHolder(parent.inflate(R.layout.browse_adapter_item_recipe)) },
            onClick = { _, item -> onClick(item) },
            onBind = { _, item, view, _ -> onBind(item, view) },
            filter = object : Filter() {
                override fun performFiltering(constraint: CharSequence): FilterResults {
                    val sequence = constraint.toString()
                    if (sequence.isEmpty()) adapter.filteredList = adapter.items
                    else {
                        val fList: MutableList<RecipeView> = ArrayList()
                        for (name in adapter.items) {
                            if (name.title.toLowerCase(Locale.getDefault())
                                    .contains(sequence.toLowerCase(Locale.getDefault()))
                            ) {
                                fList.add(name)
                            }
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

    private fun handleDataState(resource: Resource<List<RecipeView>?>) {
        progressbar.updateVisibility(resource.status)
        when (resource.status) {
            Status.SUCCESS ->
                if (resource.data.isNullOrEmpty()) {
                    emptyView.show(onClick={ viewModel.fetchRecipes() })
                    recyclerRecipes.hide(true)
                } else {
                    adapter.items = resource.data.toMutableList()
                    emptyView.hide()
                    recyclerRecipes.show()
                }
            Status.LOADING -> emptyView.hide()
            Status.ERROR ->  {
                emptyView.show(resource.throwable.getError(requireContext()), onClick={ viewModel.fetchRecipes() })
                handleError(resource.throwable)
            }
        }
    }

    private fun <T> onClick(item: T) {
        val recipe = item as RecipeView
        if (recipe.isBookmarked) viewModel.unBookmarkRecipe(recipe.id)
        else viewModel.bookmarkRecipe(recipe.id)
    }

    private fun <T> onBind(item: T, view: View) {
        val recipe = item as RecipeView
        view.tvRecipeName.text = recipe.title
        Glide.with(requireContext())
            .load(recipe.urlToImage)
            .apply(RequestOptions.circleCropTransform())
            .into(view.recipeImage)
        val starResource = if (recipe.isBookmarked) R.drawable.ic_star_black_24dp
        else R.drawable.ic_star_border_black_24dp
        view.bookmarkedImage.setImageResource(starResource)
    }

}