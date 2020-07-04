package ir.jaShakouri.app.view.vh

import ir.jaShakouri.app.base.recyclerView.RvViewHolder
import ir.jaShakouri.app.databinding.LoadingDataBinder
import ir.jaShakouri.domain.model.Item

class LoadingViewHolder(private val itemBinding: LoadingDataBinder) :
    RvViewHolder<Item>(itemBinding) {

    override fun bind(item: Item) {
        this.itemBinding.loading = item
        this.itemBinding.executePendingBindings()
    }

}