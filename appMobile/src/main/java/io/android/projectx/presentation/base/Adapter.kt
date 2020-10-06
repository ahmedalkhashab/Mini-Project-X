package io.android.projectx.presentation.base

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject
import kotlin.properties.Delegates

class Adapter<T> @Inject constructor(
    private val onCreate: (ViewGroup, Int) -> ViewHolder,
    private val onType: ((Int, T) -> Int)? = null,
    private val onClick: (Int, T) -> Unit,
    private val onBind: (Int, T, View, Int) -> Unit,
    private val filter: Filter? = null
) : RecyclerView.Adapter<Adapter.ViewHolder>(), Filterable {

    var items: MutableList<T> = arrayListOf()
        set(value) {
            field = value
            filteredList = items
            notifyDataSetChanged()
        }
    var filteredList: MutableList<T> = items

    fun addAll(list: MutableList<T>) {
        items.addAll(list)
        filteredList = items
        notifyDataSetChanged()
    }

    // This keeps track of the currently selected position
    var selectedPosition by Delegates.observable(-1) { _, oldPos, newPos ->
        if (newPos in filteredList.indices) {
            notifyItemChanged(oldPos)
            notifyItemChanged(newPos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        onCreate(parent, viewType)

    override fun getItemViewType(position: Int): Int =
        onType?.invoke(position, items[position]) ?: super.getItemViewType(position)

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBind(position, filteredList[position], holder.itemView, holder.itemViewType)
        holder.itemView.setOnClickListener {
            selectedPosition = position
            onClick(position, filteredList[position])
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /**
     * <p>Returns a filter that can be used to constrain data with a filtering
     * pattern.</p>
     *
     * <p>This method is usually implemented by {@link Adapter}
     * classes.</p>
     *
     * @return a filter used to constrain data
     */
    override fun getFilter(): Filter? = filter

}