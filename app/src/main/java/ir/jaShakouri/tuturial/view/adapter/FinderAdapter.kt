package ir.jaShakouri.tuturial.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ir.jaShakouri.tuturial.R
import ir.jaShakouri.tuturial.data.model.Item
import ir.jaShakouri.tuturial.databinding.ItemUserBinding
import ir.jaShakouri.tuturial.viewModel.FindViewModel

class FinderAdapter(val list: List<Item>) :
    RecyclerView.Adapter<FinderAdapter.FilderViewHolder>() {

    private var inflator: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilderViewHolder {

        if (inflator == null)
            inflator = LayoutInflater.from(parent.context)

        val binding: ItemUserBinding =
            DataBindingUtil.inflate(inflator!!, R.layout.row_user, parent, false)

        return FilderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FilderViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    class FilderViewHolder(private val itemBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(model: Item) {
            this.itemBinding.item = model
            this.itemBinding.executePendingBindings()
        }

    }

}