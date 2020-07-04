package ir.jaShakouri.app.dataBinding.appBinding

import androidx.databinding.DataBindingComponent
import ir.jaShakouri.app.dataBinding.RecyclerViewDataBinding


class AppDataBindingComponent : DataBindingComponent {

    override fun getRecyclerViewDataBinding(): RecyclerViewDataBinding {
        return RecyclerViewDataBinding()
    }

}