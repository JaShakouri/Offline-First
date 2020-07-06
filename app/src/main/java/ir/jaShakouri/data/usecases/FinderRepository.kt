package ir.jaShakouri.data.usecases

import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import ir.jaShakouri.data.api.ApiInterface
import ir.jaShakouri.data.api.config.RetryWithDelay
import ir.jaShakouri.data.local.dataBase.dao.ItemDao
import ir.jaShakouri.domain.AppKeys
import ir.jaShakouri.domain.dataSource.find.FindDataSource
import ir.jaShakouri.domain.model.DataResponse
import ir.jaShakouri.domain.model.Item
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FinderRepository @Inject constructor(
    private var apiInterface: ApiInterface, private var itemDao: ItemDao
) : FindDataSource {

    private val tag = "FinderRepository"

    private val sdf: SimpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    private var currentDate: String

    private var location = ""
    private var query = ""
    private var pageOffset = 1

    init {
        currentDate = sdf.format(Date())
    }

    override fun getItems(
        mLocation: String,
        mQuery: String?,
        mOffset: Int
    ): Observable<DataResponse>? {

        this.location = mLocation
        this.query = mQuery!!
        this.pageOffset = mOffset

        return Observable.concatArray(
            getItemFromDB(), getItemsFromApi()
        )
    }

    override fun getLoadMore(mOffset: Int): Observable<DataResponse>? {
        this.pageOffset = mOffset
        return getItemsFromApi()
    }

    override fun clearCache() {
        itemDao.deleteAll()
        getItemsFromApi()
    }

    private fun getItemFromDB(): Observable<DataResponse> {
        return itemDao.getItems().map {
            return@map DataResponse(it, it.size, false)
        }.toObservable().doOnNext {
            Log.i(tag, "getItemFromDB: ${it.list.size}")
        }
    }

    private fun getItemsFromApi(): Observable<DataResponse> {
        return apiInterface.onLoadVenues(
            AppKeys.client_id, AppKeys.client_secret,
            this.currentDate, this.location, this.query,
            AppKeys.limit, this.pageOffset, AppKeys.meters
        ).map {
            return@map DataResponse(
                it.response!!.groups!![0].items!!,
                it.response!!.totalResults!!.toInt(), true
            )
        }.doOnNext {
            storeListItem(it.list)
        }.retryWhen(RetryWithDelay(3, 3000))
    }

    private fun storeListItem(list: List<Item>) {
        Observable.fromCallable { itemDao.insertAll(list) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }

}