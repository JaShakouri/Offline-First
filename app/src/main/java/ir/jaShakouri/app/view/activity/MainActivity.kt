package ir.jaShakouri.app.view.activity

import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ir.jaShakouri.app.R
import ir.jaShakouri.app.base.activity.ObserverActivity
import ir.jaShakouri.app.databinding.ActivityMainBinding
import ir.jaShakouri.app.view.vm.FindViewModel
import javax.inject.Inject

class MainActivity : ObserverActivity(), FindViewModel.EndList {

    private lateinit var finderViewModel: FindViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun viewModel() {
        finderViewModel = factory.create(FindViewModel::class.java)
    }

    override fun init() {

        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        finderViewModel.setEndList(this)
        activityMainBinding.viewModel = finderViewModel

    }

    override fun attach() {
        finderViewModel.liveDataListFailure.observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun subscribe() {
        finderViewModel.getItems()
    }

    override fun isEndList() {
        finderViewModel.loadMore()
    }

}