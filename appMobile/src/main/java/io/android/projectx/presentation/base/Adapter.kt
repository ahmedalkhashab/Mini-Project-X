package io.android.projectx.presentation.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import io.android.projectx.androidextensions.inflate
import javax.inject.Inject
import kotlin.properties.Delegates

class Adapter<T> @Inject constructor(
    @LayoutRes private val layoutRes: Int,
    private val onClick: (Int, T) -> Unit,
    private val onBind: (Int, T, View) -> Unit
) : RecyclerView.Adapter<Adapter<T>.ViewHolder>() {

    var items: List<T?> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // This keeps track of the currently selected position
    var selectedPosition by Delegates.observable(-1) { _, oldPos, newPos ->
        if (newPos in items.indices) {
            notifyItemChanged(oldPos)
            notifyItemChanged(newPos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(layoutRes))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position]?.let { item ->
            holder.bind(position, item)
            holder.itemView.setOnClickListener {
                selectedPosition = position
                onClick(position, item)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int, item: T) = onBind(position, item, itemView)
    }

}