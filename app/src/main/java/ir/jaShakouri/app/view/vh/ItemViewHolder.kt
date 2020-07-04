package ir.jaShakouri.app.view.vh

import ir.jaShakouri.app.base.recyclerView.RvViewHolder
import ir.jaShakouri.app.databinding.ItemUserBinding
import ir.jaShakouri.domain.model.Item

class ItemViewHolder(private val itemBinding: ItemUserBinding) :
    RvViewHolder<Item>(itemBinding) {

    override fun bind(item: Item) {
        this.itemBinding.item = item
        this.itemBinding.executePendingBindings()
    }

}