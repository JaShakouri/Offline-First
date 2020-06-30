package ir.jaShakouri.tuturial.view.vh

import ir.jaShakouri.tuturial.base.recyclerView.RvViewHolder
import ir.jaShakouri.tuturial.data.model.Item
import ir.jaShakouri.tuturial.databinding.ItemUserBinding

class ItemViewHolder(private val itemBinding: ItemUserBinding) :
    RvViewHolder<Item>(itemBinding) {

    override fun bind(item: Item) {
        this.itemBinding.item = item
        this.itemBinding.executePendingBindings()
    }

}