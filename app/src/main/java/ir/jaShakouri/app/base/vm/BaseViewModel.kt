package ir.jaShakouri.app.base.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private var onShowProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun showProgress(): MutableLiveData<Boolean> {
        return onShowProgress
    }

}
