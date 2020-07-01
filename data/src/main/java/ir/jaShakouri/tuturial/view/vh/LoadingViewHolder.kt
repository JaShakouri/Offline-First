package ir.jaShakouri.tuturial.view.vh

import ir.jaShakouri.tuturial.base.recyclerView.RvViewHolder
import ir.jaShakouri.tuturial.data.model.Item
import ir.jaShakouri.tuturial.databinding.ItemUserBinding
import ir.jaShakouri.tuturial.databinding.LoadingDataBinder

class LoadingViewHolder(private val itemBinding: LoadingDataBinder) :
    RvViewHolder<Item>(itemBinding) {

    override fun bind(item: Item) {
        this.itemBinding.loading = item
        this.itemBinding.executePendingBindings()
    }

}