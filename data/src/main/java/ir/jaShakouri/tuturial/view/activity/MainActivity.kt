package ir.jaShakouri.tuturial.view.activity

import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import ir.jaShakouri.tuturial.R
import ir.jaShakouri.tuturial.base.activity.ObserverActivity
import ir.jaShakouri.tuturial.databinding.ActivityMainBinding
import ir.jaShakouri.tuturial.viewModel.FindViewModel

class MainActivity : ObserverActivity(), FindViewModel.EndList {

    private val TAG = "MVVM_MainActivity"

    var finderViewModel = FindViewModel()

    override fun attach() {

        finderViewModel.liveDataListFailure.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }

    override fun init() {

        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        finderViewModel.setEndList(this)
        activityMainBinding.viewModel = finderViewModel

    }

    override fun subscribe() {
        finderViewModel.getItems()
    }

    override fun isEndList() {
        finderViewModel.loadMore()
    }


}