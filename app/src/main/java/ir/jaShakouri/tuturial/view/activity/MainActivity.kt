package ir.jaShakouri.tuturial.view.activity

import android.content.Context
import android.util.Log
import ir.jaShakouri.tuturial.R
import ir.jaShakouri.tuturial.base.BaseActivity
import ir.jaShakouri.tuturial.base.BasePresenter
import ir.jaShakouri.tuturial.base.BaseView
import ir.jaShakouri.tuturial.mvp.main.MainContract
import ir.jaShakouri.tuturial.mvp.main.MainPresenter

class MainActivity : BaseActivity(), MainContract.View {

    private val TAG = "MVP_MainActivity"
    private val presenter = MainPresenter()

    override fun layout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
    }

    override fun view(): BaseView {
        return this
    }

    override fun presenter(): BasePresenter {
        return presenter
    }

    override fun showNews(newsList: List<Any>) {

    }

    override fun showProgress() {
        Log.i(TAG, "showProgress")
    }

    override fun hideProgress() {
        Log.i(TAG, "hideProgress")
    }

    override fun showError(errorMessage: String) {
        Log.i(TAG, "showError $errorMessage")
    }

    override fun setPorogressIndicator(shouldShow: Boolean) {
        Log.i(TAG, "setPorogressIndicator $shouldShow")
    }

    override fun context(): Context {
        return context()
    }

}