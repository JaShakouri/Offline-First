package ir.jaShakouri.app.view.recyclerView.vh

import ir.jaShakouri.app.base.recyclerView.RvViewHolder
import ir.jaShakouri.app.databinding.ItemUserBinding
import ir.jaShakouri.domain.model.Item

class ItemViewHolder(private val itemBinding: ItemUserBinding) :
    RvViewHolder<Item>(itemBinding) {

    override fun unbind() {
        itemBinding.unbind()
    }

    override fun bind() {
        this.itemBinding.executePendingBindings()
    }

    override fun setViewModel(item: Item) {
        this.itemBinding.item = item
    }

}