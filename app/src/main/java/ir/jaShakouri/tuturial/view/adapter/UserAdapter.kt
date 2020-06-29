package ir.jaShakouri.tuturial.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ir.jaShakouri.tuturial.R
import ir.jaShakouri.tuturial.databinding.ItemUserBinding
import ir.jaShakouri.tuturial.viewModel.UserViewModel

class UserAdapter(val list: List<UserViewModel>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var inflator: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        if (inflator == null)
            inflator = LayoutInflater.from(parent.context)

        val binding: ItemUserBinding =
            DataBindingUtil.inflate(inflator!!, R.layout.row_user, parent, false)

        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    class UserViewHolder(private val itemBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(model: UserViewModel) {
            this.itemBinding.user = model
            this.itemBinding.executePendingBindings()
        }

    }

}