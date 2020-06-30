package ir.jaShakouri.tuturial.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ir.jaShakouri.tuturial.R
import ir.jaShakouri.tuturial.data.model.Item
import ir.jaShakouri.tuturial.databinding.ItemUserBinding

class FinderAdapter(val list: List<Item>) :
    RecyclerView.Adapter<FinderAdapter.FinderViewHolder>() {

    private var inflator: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinderViewHolder {

        if (inflator == null)
            inflator = LayoutInflater.from(parent.context)

        val binding: ItemUserBinding =
            DataBindingUtil.inflate(inflator!!, R.layout.row_user, parent, false)

        return FinderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FinderViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun addItems(list: List<Item>) {
        val position = this.list.size
        this.list.toMutableList().addAll(list)
        notifyItemMoved(position, this.list.size)
    }

    class FinderViewHolder(private val itemBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(model: Item) {
            this.itemBinding.item = model
            this.itemBinding.executePendingBindings()
        }

    }

}