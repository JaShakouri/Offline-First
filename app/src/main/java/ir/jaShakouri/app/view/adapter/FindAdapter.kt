package ir.jaShakouri.app.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ir.jaShakouri.app.R
import ir.jaShakouri.app.base.recyclerView.RvAdapter
import ir.jaShakouri.app.base.recyclerView.RvViewHolder
import ir.jaShakouri.app.databinding.ItemUserBinding
import ir.jaShakouri.app.databinding.LoadingDataBinder
import ir.jaShakouri.app.view.vh.ItemViewHolder
import ir.jaShakouri.app.view.vh.LoadingViewHolder
import ir.jaShakouri.domain.model.Item

class FindAdapter(items: ArrayList<Item>) : RvAdapter<Item, RvViewHolder<Item>>(items) {

    private var inflator: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder<Item> {

        if (inflator == null)
            inflator = LayoutInflater.from(parent.context)

        when (viewType) {

            TYPE_Item -> {

                val itemBinding: ItemUserBinding = DataBindingUtil
                    .inflate(inflator!!, R.layout.row_user, parent, false)

                return ItemViewHolder(itemBinding)

            }

            else -> {

                val loadingBinding: LoadingDataBinder = DataBindingUtil
                    .inflate(inflator!!, R.layout.row_loading, parent, false)

                return LoadingViewHolder(loadingBinding)

            }

        }

    }

}