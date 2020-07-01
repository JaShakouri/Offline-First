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
import ir.jashakouri.domain.model.FindResponse
import ir.jaShakouri.tuturial.utils.Utility
import ir.jaShakouri.tuturial.view.adapter.FindAdapter
import ir.jashakouri.domain.model.Item
import ir.jashakouri.domain.model.Location
import ir.jashakouri.domain.usecases.FinderDetails
import ir.jashakouri.presentation.api.ApiContainer
import ir.jashakouri.presentation.repositories.FinderCloudDataSource
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
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

    var progress = MutableLiveData<Int>()
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.progress)
        }

    private var companionObject = Companion

    companion object {

        private val TAG = "MVVM_UserViewModel"

        var findRepository =
            FinderDetails(FinderCloudDataSource(ApiContainer.getInstance()))
        var adapter: FindAdapter? = null

        var offset = 1
        var isLastPage = false
        var isLoading = false

        var endList: EndList? = null

        @JvmStatic
        @BindingAdapter("bind:recycler")
        fun recyclerViewBinder(
            rv: RecyclerView,
            listLiveData: MutableLiveData<FindResponse>
        ) {

            listLiveData.observe((rv.context as LifecycleOwner), Observer {

                if (adapter == null) {

                    adapter = FindAdapter(it.response!!.groups!![0].items!! as ArrayList<Item>)
                    rv.adapter =
                        ScaleInAnimationAdapter(adapter)

                    rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                            if (!rv.canScrollVertically(1)) {

                                isLastPage = adapter!!.itemCount >= it.response!!.totalResults!!

                                if (!isLoading && !isLastPage) {

                                    Log.i(TAG, "onScrolled: Can Not Scroll")

                                    if (endList != null)
                                        endList!!.isEndList()

                                } else {
                                    Log.i(
                                        TAG,
                                        "onScrolled: isLoading $isLoading isLastPage $isLastPage"
                                    )

                                    if (isLastPage)
                                        adapter!!.notifyItemRemoved(adapter!!.itemCount)

                                }

                            }

                        }
                    })

                } else {
                    adapter!!.addView(it.response!!.groups!![0].items!! as ArrayList<Item>)
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

    }

    fun getItems() {

        progress.postValue(View.VISIBLE)

        findRepository.getItems("35.7523, 51.4449", "", offset)!!
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

    fun loadMore() {

        isLoading = true
        offset++

        findRepository.getItems("35.7523, 51.4449", "", offset)!!
            .enqueue(object : Callback<FindResponse> {

                override fun onResponse(
                    call: Call<FindResponse>,
                    response: Response<FindResponse>
                ) {
                    liveDataListSuccessful.postValue(response.body())
                    isLoading = false
                }

                override fun onFailure(call: Call<FindResponse>, t: Throwable) {

                    isLoading = false
                    offset--

                    liveDataListFailure.postValue(t.message)

                }

            })

    }

    fun setEndList(endList: EndList) {
        companionObject.endList = endList
    }

    interface EndList {
        fun isEndList()
    }

}