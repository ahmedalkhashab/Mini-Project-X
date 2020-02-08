package io.android.projectx.presentation.features.browse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.android.projectx.presentation.R
import io.android.projectx.presentation.model.RecipeView
import javax.inject.Inject

class BrowseAdapter @Inject constructor(): RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var recipes: List<RecipeView> = arrayListOf()
    var recipeListener: RecipeListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.browse_adapter_item_recipe, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recipes.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.ownerNameText.text = recipe.author
        holder.recipeNameText.text = recipe.title

        Glide.with(holder.itemView.context)
            .load(recipe.urlToImage)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.recipeImage)

        val starResource = if (recipe.isBookmarked) {
            R.drawable.ic_star_black_24dp
        } else {
            R.drawable.ic_star_border_black_24dp
        }
        holder.bookmarkedImage.setImageResource(starResource)

        holder.itemView.setOnClickListener {
            if (recipe.isBookmarked) {
                recipeListener?.onBookmarkedRecipeClicked(recipe.id)
            } else {
                recipeListener?.onRecipeClicked(recipe.id)
            }
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var recipeImage: ImageView = view.findViewById(R.id.image_recipe)
        var ownerNameText: TextView = view.findViewById(R.id.text_owner_name)
        var recipeNameText: TextView = view.findViewById(R.id.text_recipe_name)
        var bookmarkedImage: ImageView = view.findViewById(R.id.image_bookmarked)

    }

}