package ir.jaShakouri.tuturial.base.recyclerView

import androidx.recyclerview.widget.RecyclerView

abstract class RvAdapter<T, E : RvViewHolder<T>>(var items: List<T>) :
    RecyclerView.Adapter<E>() {

    companion object {
        const val TYPE_Loading = 0
        const val TYPE_Item = 1
    }

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

    fun addView(items: List<T>) {
        val position = this.items.size
        notifyItemRangeInserted(position, items.size)
        this.items.toMutableList().addAll(items)
    }
}
