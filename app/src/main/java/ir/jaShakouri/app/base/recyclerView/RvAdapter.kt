package ir.jaShakouri.app.base.recyclerView

import androidx.recyclerview.widget.RecyclerView

abstract class RvAdapter<T, E : RvViewHolder<T>>(
    private var items: ArrayList<T>,
    private val totalSize: Int
) :
    RecyclerView.Adapter<E>() {

    private val tag = "RvAdapter"

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: E, position: Int) {
        holder.setViewModel(items[position])
    }

    override fun onViewAttachedToWindow(holder: E) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: E) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

    fun addView(item: ArrayList<T>) {
        val startPosition = items.size
        this.items.addAll(item)
        notifyItemRangeInserted(startPosition, item.size)
    }

}
