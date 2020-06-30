package ir.jaShakouri.tuturial.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ir.jaShakouri.tuturial.R
import ir.jaShakouri.tuturial.base.recyclerView.RvAdapter
import ir.jaShakouri.tuturial.base.recyclerView.RvViewHolder
import ir.jaShakouri.tuturial.data.model.Item
import ir.jaShakouri.tuturial.databinding.ItemUserBinding
import ir.jaShakouri.tuturial.databinding.LoadingDataBinder
import ir.jaShakouri.tuturial.view.vh.ItemViewHolder
import ir.jaShakouri.tuturial.view.vh.LoadingViewHolder

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