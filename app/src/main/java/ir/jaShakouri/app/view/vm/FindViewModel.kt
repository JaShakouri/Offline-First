package ir.jaShakouri.app.view.vm

import android.net.Uri
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.jaShakouri.app.R
import ir.jaShakouri.app.utils.Utility
import ir.jaShakouri.app.view.recyclerView.adapter.FindAdapter
import ir.jaShakouri.data.usecases.FinderRepository
import ir.jaShakouri.domain.model.DataResponse
import ir.jaShakouri.domain.model.Item
import ir.jaShakouri.domain.model.Location
import javax.inject.Inject
import kotlin.system.exitProcess

class FindViewModel @Inject constructor(private var findRepository: FinderRepository) :
    ViewModel() {

    private var disposable = CompositeDisposable()

    var liveDataListSuccessful = MutableLiveData<DataResponse>()

    var liveDataListLoadMore = MutableLiveData<DataResponse>()

    var liveDataListFailure = MutableLiveData<Throwable>()

    var liveDataListSize = MutableLiveData<String>()

    var liveDataState = MutableLiveData<String>()

    var progress = MutableLiveData<Int>()

    var loadMoreProgress = MutableLiveData<Int>().default(View.GONE)

    companion object {

        private const val TAG = "MVVM_FindViewModel"

        var adapter: FindAdapter? = null

        var offset = 1
        var isLastPage = false
        var isLoading = false

        var endList: EndList? = null

        @JvmStatic
        @BindingAdapter("recycler")
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
        @BindingAdapter("loadMore")
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
        @BindingAdapter("visible")
        fun visibilityBinder(
            view: View, progressLiveData: MutableLiveData<Int>
        ) {
            progressLiveData.observe(view.context as LifecycleOwner, Observer {
                view.visibility = it
            })
        }

        @JvmStatic
        @BindingAdapter(value = ["textListener", "animation"], requireAll = true)
        fun changeTextBinder(
            view: TextView, liveData: MutableLiveData<String>, animation: Boolean
        ) {
            liveData.observe(view.context as LifecycleOwner, Observer {

                if (animation) {
                    val anim = AlphaAnimation(1.0f, 0.0f)
                    anim.duration = 200
                    anim.repeatCount = 1
                    anim.repeatMode = Animation.REVERSE
                    view.startAnimation(anim)
                }

                view.text = it
            })
        }

        @JvmStatic
        @BindingAdapter("imageLoader")
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

            imageView.setImageURI(Uri.parse(image), imageView.context)

        }

    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun getItems() {

        progress.postValue(View.VISIBLE)

        disposable.add(findRepository.items(
            "35.7523, 51.4449", "",
            offset
        )!!
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map {
                progress.postValue(View.GONE)
                liveDataListSuccessful.postValue(it)
                liveDataListSize.postValue(it.list.size.toString() + " / " + it.total)
                liveDataState.postValue(if (it.isOnline) "online" else "offline")
            }.onErrorReturn {
                Log.e(TAG, "getItems: $it")
                liveDataListFailure.postValue(it)
                progress.postValue(View.GONE)
            }.subscribe()
        )

    }

    fun loadMore() {

        loadMoreProgress.postValue(View.VISIBLE)

        isLoading = true
        offset++

        disposable.add(findRepository.loadMore(offset)!!
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map {

                liveDataState.postValue(if (it.isOnline) "online" else "offline")
                liveDataListSize.postValue((adapter!!.itemCount + it.list.size).toString() + " / " + it.total)

                liveDataListLoadMore.postValue(it)
                isLoading = false
                loadMoreProgress.postValue(View.GONE)
            }.onErrorReturn {

                isLoading = false
                offset--
                liveDataListFailure.postValue(it)
                loadMoreProgress.postValue(View.GONE)
                Log.e(TAG, "loadMore: $it")

            }.subscribe()
        )

    }

    fun setEndList(endList: EndList) {
        Companion.endList = endList
    }

    interface EndList {
        fun isEndList()
    }

    private fun <T : Any?> MutableLiveData<T>.default(initialValue: T) =
        apply { setValue(initialValue) }

    fun menuClickBinder(view: View) {

        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {

            when (it.itemId) {

                R.id.clear -> {
                    findRepository.clearCache()
                    return@setOnMenuItemClickListener true
                }

                R.id.exit -> {
                    exitProcess(0)
                }
            }

            return@setOnMenuItemClickListener false

        }
        popupMenu.show()

    }

}
