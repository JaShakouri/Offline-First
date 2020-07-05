package ir.jaShakouri.app.view.recyclerView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ir.jaShakouri.app.R
import ir.jaShakouri.app.base.recyclerView.RvAdapter
import ir.jaShakouri.app.base.recyclerView.RvViewHolder
import ir.jaShakouri.app.databinding.ItemUserBinding
import ir.jaShakouri.app.view.recyclerView.vh.ItemViewHolder
import ir.jaShakouri.domain.model.Item

class FindAdapter(items: ArrayList<Item>, totalSize: Int) :
    RvAdapter<Item, RvViewHolder<Item>>(items, totalSize) {

    private var inflator: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder<Item> {

        if (inflator == null)
            inflator = LayoutInflater.from(parent.context)

        val itemBinding: ItemUserBinding = DataBindingUtil
            .inflate(inflator!!, R.layout.row_item, parent, false)

        return ItemViewHolder(itemBinding)

    }

}