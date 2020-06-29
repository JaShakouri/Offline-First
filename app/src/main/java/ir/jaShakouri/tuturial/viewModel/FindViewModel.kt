package ir.jaShakouri.tuturial.viewModel

import android.app.Application
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import ir.jaShakouri.tuturial.BR
import ir.jaShakouri.tuturial.data.model.FindResponse
import ir.jaShakouri.tuturial.data.remote.repo.find.FindRepository
import ir.jaShakouri.tuturial.view.adapter.FinderAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindViewModel(app: Application) : BaseObservable() {

    private val TAG = "MVVM_UserViewModel"


    var liveDataListSuccessful = MutableLiveData<FindResponse>()
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.liveDataListSuccessful)
        }

    var liveDataListFailure = MutableLiveData<String>()
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.liveDataListFailure)
        }

    @Bindable
    var progress = MutableLiveData<Int>()
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.progress)
        }

    private var findViewModel: FindRepository? = null

    init {
        findViewModel = FindRepository(app)
    }

    fun getItems(
        location: String, query: String, offset: Int
    ) {

        progress.postValue(View.VISIBLE)

        findViewModel!!.getItems(location, query, offset)
            .enqueue(object : Callback<FindResponse> {

                override fun onResponse(
                    call: Call<FindResponse>,
                    response: Response<FindResponse>
                ) {
                    progress.postValue(View.GONE)
                    liveDataListSuccessful.postValue(response.body())
                }

                override fun onFailure(call: Call<FindResponse>, t: Throwable) {
                    liveDataListFailure.postValue(t.message)
                    progress.postValue(View.GONE)
                }

            })

    }

    companion object {

        var adapter: FinderAdapter? = null

        @JvmStatic
        @BindingAdapter("bind:recycler")
        fun recyclerViewBinder(
            rv: RecyclerView,
            listLiveData: MutableLiveData<FindResponse>
        ) {

            listLiveData.observe((rv.context as LifecycleOwner), Observer {

                if (adapter == null) {
                    adapter = FinderAdapter(it.response!!.groups!![0].items!!)
                    rv.adapter = adapter
                } else
                    adapter!!.notifyDataSetChanged()

            })

        }

        @JvmStatic
        @BindingAdapter("bind:visible")
        fun visibilityBinder(
            view: View,
            progressLiveData: MutableLiveData<Int>
        ) {

            progressLiveData.observe(view.context as LifecycleOwner, Observer {
                view.visibility = it
            })

        }

    }

}