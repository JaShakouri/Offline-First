package ir.jaShakouri.app.base.recyclerView

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class RvViewHolder<A>(itemView: ViewDataBinding) :
    RecyclerView.ViewHolder(itemView.root) {

    abstract fun bind(item: A)
}
