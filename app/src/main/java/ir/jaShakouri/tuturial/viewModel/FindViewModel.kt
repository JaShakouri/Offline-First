package ir.jaShakouri.tuturial.viewModel

import android.net.Uri
import android.util.Log
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import ir.jaShakouri.tuturial.BR
import ir.jaShakouri.tuturial.data.model.FindResponse
import ir.jaShakouri.tuturial.data.model.Location
import ir.jaShakouri.tuturial.data.remote.repo.find.FindRepository
import ir.jaShakouri.tuturial.utils.Utility
import ir.jaShakouri.tuturial.view.adapter.FinderAdapter
import ir.jaShakouri.tuturial.view.adapter.find.FindAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindViewModel : BaseObservable() {

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

    companion object {

        private val TAG = "MVVM_UserViewModel"

        var adapter: FindAdapter? = null

        var offset = 1
        var isLastPage = false
        var isLoading = false

        var findViewModel: FindRepository = FindRepository()

        @JvmStatic
        @BindingAdapter("bind:recycler")
        fun recyclerViewBinder(
            rv: RecyclerView,
            listLiveData: MutableLiveData<FindResponse>
        ) {

            listLiveData.observe((rv.context as LifecycleOwner), Observer {

                if (adapter == null) {

                    adapter = FindAdapter(it.response!!.groups!![0].items!!)
                    rv.adapter = adapter

                    /*
                    rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                            if (!rv.canScrollVertically(1)) {

                                isLastPage = adapter!!.itemCount >= it.response!!.totalResults!!

                                if (!isLoading && !isLastPage) {
                                    Log.i(TAG, "onScrolled: Can Not Scroll")
                                    onLoadMore()
                                } else {
                                    Log.i(
                                        TAG,
                                        "onScrolled: isLoading $isLoading isLastPage $isLastPage"
                                    )
                                }

                            }

                        }
                    })

                     */

                } else {
                    adapter!!.addView(it.response!!.groups!![0].items!!)
                }

            })

        }

        @JvmStatic
        @BindingAdapter("bind:visible")
        fun visibilityBinder(
            view: View, progressLiveData: MutableLiveData<Int>
        ) {
            progressLiveData.observe(view.context as LifecycleOwner, Observer {
                view.visibility = it
            })
        }

        @JvmStatic
        @BindingAdapter("bind:imageLoader")
        fun imageLoaderBinder(
            imageView: SimpleDraweeView,
            location: Location
        ) {

            val image = Utility.latLangConverter(
                location.lat!!,
                location.lng!!,
                400,
                400,
                18
            )

            imageView.setImageURI(Uri.parse(image))

        }

        fun onLoadMore() {

            isLoading = true
            offset++

            Log.i(TAG, "onLoadMore: $offset")

        }

    }

    fun getItems() {

        progress.postValue(View.VISIBLE)

        findViewModel.getItems("35.7523, 51.4449", "", offset)
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

    private fun loadMore() {

        findViewModel.getItems("35.7523, 51.4449", "", offset)
            .enqueue(object : Callback<FindResponse> {

                override fun onResponse(
                    call: Call<FindResponse>,
                    response: Response<FindResponse>
                ) {
                    isLoading = false
                    liveDataListSuccessful.postValue(response.body())
                }

                override fun onFailure(call: Call<FindResponse>, t: Throwable) {
                    isLoading = false
                    offset--
                    liveDataListFailure.postValue(t.message)
                }

            })

    }

}