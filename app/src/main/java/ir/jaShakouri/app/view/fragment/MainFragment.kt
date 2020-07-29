package ir.jaShakouri.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ir.jaShakouri.app.base.fragment.ObserverFragment
import ir.jaShakouri.app.databinding.FragmentMainBinding
import ir.jaShakouri.app.view.vm.FindViewModel
import javax.inject.Inject

class MainFragment : ObserverFragment(), FindViewModel.EndList{

    private lateinit var finderViewModel: FindViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun viewModel() {
        finderViewModel = factory.create(FindViewModel::class.java)
    }

    override fun init(inflater: LayoutInflater,
                      container: ViewGroup?,
                      savedInstanceState: Bundle?): ViewDataBinding {
        val fragmentMainBinding: FragmentMainBinding =
            FragmentMainBinding.inflate(inflater, container,false)
        finderViewModel.setEndList(this)
        fragmentMainBinding.viewModel = finderViewModel
        return fragmentMainBinding;
    }

    override fun attach() {
        finderViewModel.liveDataListFailure.observe(this, Observer {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun subscribe() {
        finderViewModel.getItems()
    }

    override fun isEndList() {
        finderViewModel.loadMore()
    }

}