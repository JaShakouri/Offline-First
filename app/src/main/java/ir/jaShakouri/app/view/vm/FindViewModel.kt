package ir.jaShakouri.app.view.vm

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import io.reactivex.schedulers.Schedulers
import ir.jaShakouri.app.BR
import ir.jaShakouri.app.utils.Utility
import ir.jaShakouri.app.view.recyclerView.adapter.FindAdapter
import ir.jaShakouri.data.usecases.FinderRepository
import ir.jaShakouri.domain.model.DataResponse
import ir.jaShakouri.domain.model.Item
import ir.jaShakouri.domain.model.Location

class FindViewModel : BaseObservable() {

    var liveDataListSuccessful = MutableLiveData<DataResponse>()
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.liveDataListSuccessful)
        }

    var liveDataListLoadMore = MutableLiveData<DataResponse>()
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.liveDataListLoadMore)
        }

    var liveDataListFailure = MutableLiveData<String>()
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.liveDataListFailure)
        }

    var liveDataListSize = MutableLiveData<String>().default("0")
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.liveDataListSize)
        }

    var progress = MutableLiveData<Int>()
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.progress)
        }

    var loadMoreProgress = MutableLiveData<Int>().default(View.GONE)
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.loadMoreProgress)
        }

    companion object {

        private const val TAG = "MVVM_FindViewModel"

        var adapter: FindAdapter? = null
        val findRepository = FinderRepository()

        var offset = 1
        var isLastPage = false
        var isLoading = false

        var endList: EndList? = null

        @JvmStatic
        @BindingAdapter("bind:recycler")
        fun recyclerViewBinder(
            rv: RecyclerView,
            listLiveData: MutableLiveData<DataResponse>
        ) {
            listLiveData.observe((rv.context as LifecycleOwner), Observer {
                adapter = FindAdapter(it.list as ArrayList<Item>, it.total)
                rv.setItemViewCacheSize(2)
                rv.adapter = adapter
                rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                        if (!rv.canScrollVertically(1)) {

                            isLastPage = adapter!!.itemCount >= it.total

                            if (!isLoading && !isLastPage) {

                                Log.i(TAG, "onScrolled: Can Not Scroll")

                                if (endList != null)
                                    endList!!.isEndList()

                            }

                        }

                    }
                })
            })
        }

        @JvmStatic
        @BindingAdapter("bind:loadMore")
        fun recyclerLoadMoreViewBinder(
            rv: RecyclerView,
            listLiveData: MutableLiveData<DataResponse>
        ) {

            listLiveData.observe((rv.context as LifecycleOwner),
                Observer {
                    if (adapter != null) {
                        adapter!!.addView(it.list as ArrayList<Item>)
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
                location.lat,
                location.lng,
                400,
                400,
                18
            )

            imageView.setImageURI(Uri.parse(image))

        }

    }

    fun getItems() {

        progress.postValue(View.VISIBLE)

        findRepository.getItems(
            "35.7523, 51.4449", "",
            offset
        )
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map {
                progress.postValue(View.GONE)
                liveDataListSuccessful.postValue(it)
                liveDataListSize.postValue(it.list.size.toString())
            }.onErrorReturn {
                Log.e(TAG, "getItems: $it")
                liveDataListFailure.postValue(it.message)
                progress.postValue(View.GONE)
            }.subscribe()

    }

    fun loadMore() {

        loadMoreProgress.postValue(View.VISIBLE)

        isLoading = true
        offset++

        findRepository.getItems(
            "35.7523, 51.4449", "",
            offset
        )
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map {
                liveDataListLoadMore.postValue(it)
                isLoading = false
                loadMoreProgress.postValue(View.GONE)
                liveDataListSize.postValue(adapter!!.itemCount.toString())

            }.onErrorReturn {

                isLoading = false
                offset--
                liveDataListFailure.postValue(it.message)
                loadMoreProgress.postValue(View.GONE)
                Log.e(TAG, "loadMore: $it")

            }.subscribe()

    }

    fun setEndList(endList: EndList) {
        Companion.endList = endList
    }

    interface EndList {
        fun isEndList()
    }

    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

}
