package ir.jaShakouri.tuturial.base

import android.content.Context

interface BaseView {

    fun showProgress()

    fun hideProgress()

    fun showError(errorMessage: String)

    fun setPorogressIndicator(shouldShow: Boolean)

    fun context(): Context
    
}