package ir.jaShakouri.app.dataBinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.jaShakouri.app.view.adapter.FindAdapter
import ir.jaShakouri.domain.model.Item

class RecyclerViewDataBinding {

    @BindingAdapter("app:adapter", "app:data")
    fun bind(
        recyclerView: RecyclerView,
        adapter: FindAdapter,
        data: ArrayList<Item>
    ) {
        recyclerView.adapter = adapter
    }

}