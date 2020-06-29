package ir.jaShakouri.tuturial.mvp.main

import ir.jaShakouri.tuturial.base.BasePresenter
import ir.jaShakouri.tuturial.base.BaseView

interface MainContract {

    interface View : BaseView {
        fun showNews(newsList: List<Any>)
    }

    interface Presenter : BasePresenter {
        fun getNewsList()
    }

}