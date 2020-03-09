package io.android.projectx.presentation.features.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.model.RestaurantView
import javax.inject.Inject

class RestaurantsAdapter @Inject constructor() :
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    var restaurants: List<RestaurantView> = arrayListOf()
    var restaurantListener: RestaurantListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.restaurants_adapter_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return restaurants.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.restaurantNameText.text = restaurant.title
        holder.restaurantDescriptionText.text = restaurant.description
        Glide.with(holder.itemView.context)
            .load(restaurant.urlToImage)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.restaurantImage)
        holder.itemView.setOnClickListener {
            restaurantListener?.onRestaurantClicked(restaurant.id)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var restaurantImage: ImageView = view.findViewById(R.id.image_restaurant)
        var restaurantNameText: TextView = view.findViewById(R.id.text_restaurant_name)
        var restaurantDescriptionText: TextView = view.findViewById(R.id.text_description)
    }

}