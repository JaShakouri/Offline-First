package ir.jaShakouri.app.base.recyclerView

import androidx.recyclerview.widget.RecyclerView

abstract class RvAdapter<T, E : RvViewHolder<T>>(private var items: ArrayList<T>) :
    RecyclerView.Adapter<E>() {

    private val TYPE_Loading = 0
    val TYPE_Item = 1

    override fun getItemViewType(position: Int): Int {

        if (position == items.size)
            return TYPE_Loading

        return TYPE_Item
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    override fun onBindViewHolder(holder: E, position: Int) {
        if (position < items.size)
            holder.bind(items[position])
    }

    fun addView(item: ArrayList<T>) {
        val startPosition = items.size
        this.items.addAll(item)
        notifyItemRangeInserted(startPosition, item.size)
    }

}
