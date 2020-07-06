package ir.jaShakouri.app.view.activity

import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ir.jaShakouri.app.R
import ir.jaShakouri.app.base.activity.ObserverActivity
import ir.jaShakouri.app.databinding.ActivityMainBinding
import ir.jaShakouri.app.view.vm.FindViewModel
import ir.jaShakouri.data.local.sharedPref.Pref
import javax.inject.Inject

class MainActivity : ObserverActivity(), FindViewModel.EndList {

    @Inject
    lateinit var finderViewModel: FindViewModel

    override fun viewModel() {

    }

    override fun init() {

        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        finderViewModel.setEndList(this)
        activityMainBinding.viewModel = finderViewModel

    }

    override fun attach() {

        finderViewModel.liveDataListFailure.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }

    override fun subscribe() {
        finderViewModel.getItems()
    }


    override fun isEndList() {
        finderViewModel.loadMore()
    }


}