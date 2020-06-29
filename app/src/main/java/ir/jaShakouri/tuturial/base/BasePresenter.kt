package ir.jaShakouri.tuturial.base

interface BasePresenter {
    fun attachView(view: BaseView)
    fun detachView()
}