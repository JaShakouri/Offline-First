package ir.jaShakouri.tuturial.mvp.main

import android.util.Log
import ir.jaShakouri.tuturial.base.BaseView

class MainPresenter : MainContract.Presenter {

    private val TAG = "MVP_MainPresenter"

    var view: MainContract.View? = null

    override fun getNewsList() {
        view!!.setPorogressIndicator(true)
        view!!.showProgress()
        Log.i(TAG, "getNewsList")
        view!!.setPorogressIndicator(false)
        view!!.hideProgress()
    }

    override fun attachView(view: BaseView) {
        this.view = view as MainContract.View
        Log.i(TAG, "attachView")
        getNewsList()
    }

    override fun detachView() {
        this.view = null
        Log.i(TAG, "detachView")
    }

}